package com.alberto.advent.day03;

import com.alberto.advent.utils.DayThreeUtils;
import java.util.List;

public class LifeSupport {

  /**
   * Calculates the life support rating.
   *
   * @return The CO2 rating times the O2 rating
   */
  public long calculateLifeSupportRating() {
    long lifeSupportRating = (long)
        this.getCO2Rating(DayThreeUtils.getNumbers())
        * this.getOxygenRating(DayThreeUtils.getNumbers());
    System.out.println("Day three - The life support rating is: " + lifeSupportRating);
    return lifeSupportRating;
  }

  /**
   * Returns the oxygen rating. To find oxygen generator rating, determine the most common value (0
   * or 1) in the current bit position, and keep only numbers with that bit in that position. If 0
   * and 1 are equally common, keep values with a 1 in the position being considered.
   *
   * @param list The list of numbers
   * @return The decimal representation of the new binary generated
   */
  public int getOxygenRating(List<String> list) {
    List<String> oxygenRating = this.iterateList(list, DayThreeUtils.MOST_FREQUENT);
    return Integer.parseInt(oxygenRating.get(0), 2);
  }

  /**
   * Returns the CO2 rating. To find CO2 scrubber rating, determine the least common value (0 or 1)
   * in the current bit position, and keep only numbers with that bit in that position. If 0 and 1
   * are equally common, keep values with a 0 in the position being considered.
   *
   * @param list The list of numbers
   * @return The decimal representation of the new binary generated
   */
  public int getCO2Rating(List<String> list) {
    List<String> co2Rating = this.iterateList(list, DayThreeUtils.LEAST_FREQUENT);
    return Integer.parseInt(co2Rating.get(0), 2);
  }

  /**
   * Iterates through the number list and, depending on the criteria, removes a string from the
   * list.
   *
   * @param list     The list of numbers
   * @param criteria The criteria by which the strings will be removed
   * @return A list with only one string
   */
  public List<String> iterateList(List<String> list, String criteria) {
    for (int i = 0; i < 12 && list.size() > 1; i++) {
      int finalI = i;
      String character = this.getFinalBitByCriteria(i, criteria, list);
      list.removeIf(line -> !String.valueOf(line.charAt(finalI)).equals(character));
    }
    return list;
  }

  /**
   * Returns the final bit of the list depending on the criteria.
   *
   * @param position The position in which the bits must be checked
   * @param criteria The criteria, lowest or highest
   * @param list     The list of numbers to check
   * @return The most or least frequent number in that position
   */
  public String getFinalBitByCriteria(int position, String criteria, List<String> list) {
    long zero = 0L;
    long one = 0L;

    for (String line : list) {
      if (Character.toString(line.charAt(position)).equals("0")) {
        zero++;
      } else {
        one++;
      }
    }

    return DayThreeUtils.returnValue(zero, one, criteria);
  }
}
