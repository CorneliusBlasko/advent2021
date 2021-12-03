package com.alberto.advent.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DayThreeUtils{

    public static final String HIGHEST = "highest";
    public static final String LOWEST = "lowest";

    public static List<String> getNumbers(){
        try{
            return Files.readAllLines(Path.of("src/main/resources/bits.txt"));
        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String returnValue(long zero,long one,String option) {
        if(option.equals(HIGHEST)) {
            if(zero > one) {
                return "0";
            }

            return "1";
        }

        if(option.equals(LOWEST)) {
            if(zero > one) {
                return "1";
            }

            return "0";
        }

        return "";
    }

}
