package com.ubs.accumulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringAccumulator {

    public static final String EMPTY = "";
    public static final String DELIMITER_START = "//";

    public static int add(String number) {
        final int[] sumArray = {0};

        if (number != null && EMPTY.equals(number.trim())) {
            return sumArray[0];
        }

        String RegEx = getRegEx(number);
        String[] arrays = number.trim().split(RegEx);
        List<String> negativeNumbers = new ArrayList<>(arrays.length);

        System.err.println("Regex :" + RegEx);
        System.err.println(Arrays.toString(arrays));

        Arrays.stream(arrays).filter(str -> {
            try {
                int num = Integer.parseInt(str);
                if (num > 1000) {
                    return false;
                }
                if (num < 0) {
                    negativeNumbers.add(str);
                    return false;
                }
                return true;
            } catch (NumberFormatException ne) {
                return false;
            }

        }).map(s -> {
            int sum = sumArray[0];
            sum = sum + Integer.parseInt(s);
            sumArray[0] = sum;
            return sum;
        }).count();
        if (negativeNumbers.size() > 0) {
            throw new IllegalArgumentException("negatives not allowed : " + Arrays.toString(negativeNumbers.toArray()));
        }
        System.err.println("Sum = " + sumArray[0]);
        return sumArray[0];

    }

    private static String escapeDanglingMetaCharacter(String delimiter) {
        delimiter = delimiter.replaceAll("\\*", "\\\\*").replaceAll("\\+", "\\\\+").replaceAll("\\?", "\\\\?");

        return delimiter;
    }

    private static String getRegEx(String number) {
        String REGEX_PATTERN = ",|\\n|\\s";
        String delimiterString;
        if (number.startsWith(DELIMITER_START)) {
            delimiterString = number.substring(2, number.indexOf("\n"));
            String[] delimiterArray = delimiterString.split("\\|");
            for (String delimiter : delimiterArray) {

                REGEX_PATTERN = REGEX_PATTERN.concat("|").concat(escapeDanglingMetaCharacter(delimiter));
            }
        }
        return REGEX_PATTERN;
    }

}
