package eu.uvarov.aoc22.util;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;

public class InputUtil {

    public static final String LS = System.getProperty("line.separator");

    public static final Function<CsvSchema, CsvSchema> SPACE_SEPARATED = columns -> columns.withoutHeader().withLineSeparator(LS).withColumnSeparator(' ');

    public static Path path(String fileName) {
        try {
            return Paths.get(InputUtil.class.getClassLoader().getResource("inputs/" + fileName).toURI());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> read(String filename, Class<T> clazz, Function<CsvSchema, CsvSchema> schemaMapper) {
        try {
            CsvMapper csvMapper = new CsvMapper();
            CsvSchema schema = schemaMapper.apply(csvMapper.schemaFor(clazz));
            return (List<T>) csvMapper.reader(clazz).with(schema).readValues(Files.readString(path(filename))).readAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
