package eu.uvarov.aoc22.util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class InputUtil {

    public static final String LS = System.getProperty("line.separator");

    public static Path path(String fileName) {
        try {
            return Paths.get(InputUtil.class.getClassLoader().getResource("inputs/" + fileName).toURI());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
