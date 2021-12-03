package com.alberto.advent.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DayThreeUtils{

    public static final String MOST_FREQUENT = "most_frequent";
    public static final String LEAST_FREQUENT = "least_frequent";

    /**
     * Parses the content of the bits.txt file and returns a list of all the rows.
     * @return A list of all the rows
     */
    public static List<String> getNumbers(){
        try{
            return Files.readAllLines(Path.of("src/main/resources/bits.txt"));
        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Given two values, the amount of zeroes and the amount of ones, returns the highest or the lowest of them depending
     * on the criteria.
     * @param zero The amount of zeroes
     * @param one The amount of ones
     * @param criteria The criteria to check the amounts
     * @return Depending on the criteria, the lowest or the highest of the amounts
     */
    public static String returnValue(long zero,long one,String criteria) {
        if(criteria.equals(MOST_FREQUENT)) {
            if(zero > one) {
                return "0";
            }
            return "1";
        }

        if(criteria.equals(LEAST_FREQUENT)) {
            if(zero > one) {
                return "1";
            }
            return "0";
        }

        return "";
    }

}
