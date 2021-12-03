package com.alberto.advent.day3;

import com.alberto.advent.utils.DayThreeUtils;

import java.util.List;

public class LifeSupport{

    public long calculateLifeSupportRating() {
        return (long) this.getCO2Rating(DayThreeUtils.getNumbers()) * this.getOxygenRating(DayThreeUtils.getNumbers());
    }

    public int getOxygenRating(List<String> list) {
        List<String> oxygenRating = this.iterateList(list,DayThreeUtils.HIGHEST);
        return Integer.parseInt(oxygenRating.get(0),2);
    }

    public int getCO2Rating(List<String> list) {
        List<String> Co2Rating = this.iterateList(list,DayThreeUtils.LOWEST);
        return Integer.parseInt(Co2Rating.get(0),2);
    }

    public List<String> iterateList(List<String> list, String criteria) {
        for(int i = 0; i < 12 && list.size() > 1; i++) {
            int finalI = i;
            String character = "";
            if(criteria.equals(DayThreeUtils.LOWEST)) {
                character = this.getLowestFinalBit(i,list);
            }
            if(criteria.equals(DayThreeUtils.HIGHEST)) {
                character = this.getHighestFinalBit(i,list);
            }
            String finalCharacter = character;
            list.removeIf(line -> !String.valueOf(line.charAt(finalI)).equals(finalCharacter));
        }
        return list;
    }

    public String getHighestFinalBit(int position, List<String> list){
        long zero = 0L;
        long one = 0L;

        for(String line : list) {
            if(Character.toString(line.charAt(position)).equals("0")){
                zero++;
            } else {
                one++;
            }
        }

        return zero > one ? "0" : "1";
    }
    public String getLowestFinalBit(int position,List<String> list){
        long zero = 0L;
        long one = 0L;

        for(String line : list) {
            if(Character.toString(line.charAt(position)).equals("0")){
                zero++;
            } else {
                one++;
            }
        }

        return zero > one ? "1" : "0";
    }
}
