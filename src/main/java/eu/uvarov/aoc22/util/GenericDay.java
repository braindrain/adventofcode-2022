package eu.uvarov.aoc22.util;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
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
