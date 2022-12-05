package eu.uvarov.aoc22.util;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public abstract class GenericDay {

    public static final String LS = System.getProperty("line.separator");

    public static final Function<CsvSchema, CsvSchema> SPACE_SEPARATED = columns -> columns.withoutHeader().withLineSeparator(LS).withColumnSeparator(' ');

    public final String day;
    public boolean test;

    public GenericDay(String day) {
        this(day, false);
    }

    public GenericDay(String day, boolean test) {
        this.day = day;
        this.test = test;
    }

    public void solve() {
        System.out.println("Day" + day + " Part 1: " + part1Solution());
        System.out.println("Day" + day + " Part 2: " + part2Solution());
    }

    public void solveTest() {
        this.test = true;
        solve();
    }

    private Path getInputPath() {
        return path("inputs/day" + day + ".txt");
    }

    protected String inputString() {
        try {
            return Files.readString(getInputPath());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    protected Stream<String> inputStream(String delimiter) {
        return Arrays.stream(inputString().split(delimiter));
    }

    protected Stream<String> inputStream() {
        return Arrays.stream(inputString().split(LS));
    }

    protected <T> Stream<T> objectStream(Pattern regexp, Class<T> clazz) {
        return inputStream().map(s -> regexp(s, regexp, clazz));
    }

    protected String[] inputArray() {
        return inputStream().map(String::trim).toArray(String[]::new);
    }

    protected List<String> inputList() {
        return inputStream().map(String::trim).toList();
    }

    protected <T> List<T> inputClass(Class<T> clazz, Function<CsvSchema, CsvSchema> schemaMapper) {
        try {
            CsvMapper csvMapper = new CsvMapper();
            CsvSchema schema = schemaMapper.apply(csvMapper.schemaFor(clazz));
            return (List<T>) csvMapper.reader(clazz).with(schema).readValues(inputString()).readAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T regexp(String input, Pattern pattern, Class<T> clazz) {
        try {
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                int groupCount = matcher.groupCount();
                Object[] objects = IntStream.range(1, groupCount+1).mapToObj(matcher::group).toArray();
                return (T) Arrays.stream(clazz.getConstructors()).filter(constructor -> constructor.getParameterCount() == groupCount).findFirst().get().newInstance(objects);
            } else {
                throw new RuntimeException("String " + input + " does not match pattern " + pattern);
            }
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private Path path(String fileName) {
        try {
            return Paths.get(GenericDay.class.getClassLoader().getResource(fileName).toURI());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public abstract Object part1Solution();

    public abstract Object part2Solution();
}
