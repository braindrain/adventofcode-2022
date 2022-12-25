package eu.uvarov.aoc22.day25;

import eu.uvarov.aoc22.util.GenericDay;

public class Day25 extends GenericDay {

    public Day25() {
        super("25");
    }

    public static void main(String[] args) {
        Day25 day25 = new Day25();
        day25.solve();
    }

    @Override
    public Object part1Solution() {
        String[] strings = inputArray();
        long sum = 0L;
        for (String string : strings) {
            sum += convertSnafuToDecimal(string);
        }

        return convertDecimalToSnafu(sum);
    }

    @Override
    public Object part2Solution() {
        return null;
    }

    public String convertDecimalToSnafu(long decimal) {
        String str = "";
        while (decimal > 0) {
            long v = decimal % 5L;
            if (v == 3) {
                str = "=" + str;
                decimal = decimal + 2;
            }
            if (v == 4) {
                str = "-" + str;
                decimal = decimal + 1;
            }
            if (v == 0 || v == 1 || v == 2) {
                str = v + str;
            }
            decimal /= 5;
        }
        return str;
    }

    public long convertSnafuToDecimal(String str) {
        String number = new StringBuilder(str).reverse().toString();
        long num = 0;
        for (int i = 0; i < number.length(); i++) {
            double place = Math.pow(5, i);
            char c = number.charAt(i);
            if (c == '=') {
                num += -2L * place;
            } else if (c == '-') {
                num += -1L * place;
            } else {
                num += Integer.parseInt(c + "") * place;
            }
        }
        return num;
    }
}
