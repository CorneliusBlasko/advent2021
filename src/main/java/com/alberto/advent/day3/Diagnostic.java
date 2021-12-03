package com.alberto.advent.day3;

import com.alberto.advent.utils.DayThreeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Diagnostic{

    private static final String GAMMA = "gamma";
    private static final String EPSILON = "epsilon";


    public long calculatePowerConsumption(){
        return (long) this.calculate(GAMMA) * this.calculate(EPSILON);
    }

    public int calculate(String value){
        List<String> bits = new ArrayList<>();

        for(int i = 0; i <= 11; i++) {
            if(value.equals(GAMMA)){
                bits.add(this.parseHigherBitByPosition(i));
            }

            if(value.equals(EPSILON)) {
                bits.add(this.parseLowerBitByPosition(i));
            }
        }

        StringBuilder builder = new StringBuilder();
        bits.forEach(builder::append);

        return Integer.parseInt(builder.toString(),2);
    }

    public String parseHigherBitByPosition(int position) {
        return this.getFinalBit(position,DayThreeUtils.HIGHEST);

    }
    public String parseLowerBitByPosition(int position) {
        return this.getFinalBit(position,DayThreeUtils.LOWEST);

    }

    public String getFinalBit(int position, String option){
        long zero = 0L;
        long one = 0L;

        for(String line : Objects.requireNonNull(DayThreeUtils.getNumbers())) {
            if(Character.toString(line.charAt(position)).equals("0")){
                zero++;
            } else {
                one++;
            }
        }

        if(option.equals(DayThreeUtils.HIGHEST)) {
            return DayThreeUtils.returnValue(zero,one,DayThreeUtils.HIGHEST);
        }

        if(option.equals(DayThreeUtils.LOWEST)){
            return DayThreeUtils.returnValue(zero,one,DayThreeUtils.LOWEST);
        }

        return "";
    }






}
