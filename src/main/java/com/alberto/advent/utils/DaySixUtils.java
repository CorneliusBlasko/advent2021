package com.alberto.advent.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DaySixUtils extends InputParser {

  private static final String FILENAME = "lanternfish";

  private static Long totalLanternfish = 0L;

  /**
   * Retrieves the start data for lanternfish.
   *
   * @param isTest Whether the test data is used
   * @return A string with all the fish
   */
  public static String getFishes(boolean isTest) {
    return getInputAsString(isTest, FILENAME);
  }


  /**
   * Starts the breeding.
   *
   * @param isTest Whether to use test data
   * @param days   The days to breed
   */
  public static void breed(boolean isTest, int days) {
    String schoolString = getFishes(isTest);
    List<Integer> school = Arrays.stream(schoolString.split(",")).map(Integer::parseInt).collect(
        Collectors.toList());
    long[] fish = new long[9];
    school.forEach(i -> fish[i]++);

    for (int day = 0; day < days; day++) {
      var newborn = fish[0];
      System.arraycopy(fish, 1, fish, 0, fish.length - 1);
      fish[6] += newborn;
      fish[8] = newborn;
    }

    totalLanternfish = Arrays.stream(fish).sum();
  }

  public static Long getTotalLanternfish() {
    return totalLanternfish;
  }

}
