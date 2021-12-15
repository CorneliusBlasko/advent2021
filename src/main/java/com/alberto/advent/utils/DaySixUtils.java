package com.alberto.advent.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class DaySixUtils extends InputParser {

  private static final String FILENAME = "lanternfish";

  private static Long totalLanternfish = 0L;
  private static Map<UUID, Integer> currentFishes = new HashMap<>();

  /**
   * Retrieves the start data for lanternfish.
   *
   * @param isTest Whether the test data is used
   * @return A string with all the fishes
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

  /* OLD VERSION - NOT TIME EFFICIENT FOR THE SECOND PART */

  /**
   * Sets the data.
   *
   * @param isTest Whether it's test data
   */
  public static void setUp(boolean isTest) {
    List<String> fishesStringList = Arrays.asList(getFishes(isTest).split(","));
    List<Integer> fishesIntegerList = fishesStringList.stream().map(Integer::parseInt)
        .collect(Collectors.toList());
    for (Integer integer : fishesIntegerList) {
      currentFishes.put(UUID.randomUUID(), integer);
    }
  }


}
