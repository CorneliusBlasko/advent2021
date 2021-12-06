package com.alberto.advent.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Map;
import java.util.stream.Collectors;

public class DaySixUtils {

  private static final String TEST = "_test";
  private static final Integer MATURITY_RATE = 8;

  private static Deque<Long> newborns = new ArrayDeque<>();
  private static Long totalLanternfishes = 0L;

  /**
   * Retrieves the start data for lanternfish.
   *
   * @param isTest Whether the test data is used
   * @return A string with all the fishes
   */
  public static String getFishes(boolean isTest) {
    final String path = isTest
        ? TEST
        : "";
    String allRows = "";
    try {
      allRows = Files.readString(
          Path.of("src/main/resources/files" + "/lanternfish" + path + ".txt"));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return allRows;
  }

  /**
   * Creates the lanternfishes data.
   *
   * @param isTest Whether the test data is used
   */
  public static void createLanternfishes(String startData, boolean isTest) {
    newborns = new ArrayDeque<>();
    totalLanternfishes = 0L;
    Map<Long, Long> lanternFishes = Arrays.stream(startData.split(","))
        .map(Integer::parseInt)
        .sorted()
        .collect(Collectors.groupingBy(Integer::longValue, Collectors.counting()));

    for (Long i = 0L; i <= MATURITY_RATE; i++) {
      Long fishCount = lanternFishes.getOrDefault(i, 0L);
      newborns.add(fishCount);
      totalLanternfishes += fishCount;
    }
  }

  /** Makes the lanterenfishes breed.
   * @param days The total amount of days to be calculated
   * @return The total amount of lanternfishes by that day
   */
  public static Long breed(int days) {
    for (int day = 0; day < days; day++) {

      Long newBornFishes = newborns.poll();
      Long fishesCdMinusOne = newborns.pollLast();
      Long fishesCdMinusTwo = newborns.pollLast();

      newborns.addLast(fishesCdMinusTwo + newBornFishes);
      newborns.addLast(fishesCdMinusOne);
      newborns.addLast(newBornFishes);

      totalLanternfishes += newBornFishes;

    }

    return totalLanternfishes;
  }
}
