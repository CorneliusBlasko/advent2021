package com.alberto.advent.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class DaySixUtils {

  private static final String TEST = "_test";
  private static final Integer MATURITY_RATE = 8;

  private static Deque<Long> newborns = new ArrayDeque<>();
  private static Long totalLanternfish = 0L;
  private static Map<UUID, Integer> currentFishes = new HashMap<>();

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
   * Creates the lanternfish data.
   *
   * @param isTest Whether the test data is used
   */

  public static void createSchool(boolean isTest) {
    newborns = new ArrayDeque<>();
    totalLanternfish = 0L;
    Map<Long, Long> lanternFishes = Arrays.stream(getFishes(isTest).split(","))
        .map(Integer::parseInt)
        .sorted()
        .collect(Collectors.groupingBy(Integer::longValue, Collectors.counting()));

    for (Long i = 0L; i <= MATURITY_RATE; i++) {
      Long fishCount = lanternFishes.getOrDefault(i, 0L);
      newborns.add(fishCount);
      totalLanternfish += fishCount;
    }
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

  /**
   * Old breed logic.
   *
   * @param isTest Whether it's test data
   * @param days   Days to breed
   */
  public static void breedOld(boolean isTest, int days) {
    setUp(isTest);
    for (int i = 1; i <= days; i++) {
      HashMap<UUID, Integer> todaysFishes = new HashMap<>();
      for (Map.Entry<UUID, Integer> entry : currentFishes.entrySet()) {
        if (entry.getValue() == 0) {
          todaysFishes.put(entry.getKey(), 6);
          todaysFishes.put(UUID.randomUUID(), 8);
        } else {
          todaysFishes.put(entry.getKey(), entry.getValue() - 1);
        }
      }
      currentFishes = todaysFishes;
      System.out.println("size: " + (long) currentFishes.size());
    }
    totalLanternfish = (long) currentFishes.size();
  }


}
