package com.alberto.advent.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class DaySixUtils {

  private static final String TEST = "_test";
  private static final Integer MATURITY_RATE = 8;

  private static Long totalLanternfishes = 0L;
  private static Map<UUID, Integer> currentFishes = new HashMap<>();

  /**
   * .
   *
   * @param isTest d
   */
  public static void setUp(boolean isTest) {
    List<String> fishesStringList = Arrays.asList(getFishes(isTest).split(","));
    List<Integer> fishesIntegerList = fishesStringList.stream().map(Integer::parseInt)
        .collect(Collectors.toList());
    for (int i = 0; i < fishesIntegerList.size(); i++) {
      currentFishes.put(UUID.randomUUID(), fishesIntegerList.get(i));
    }
  }

  /**
   * .
   *
   * @param days d
   */
  public static void breed(int days) {
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
    totalLanternfishes = (long) currentFishes.size();
  }

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

  public static Long getTotalLanternfishes() {
    return totalLanternfishes;
  }

}
