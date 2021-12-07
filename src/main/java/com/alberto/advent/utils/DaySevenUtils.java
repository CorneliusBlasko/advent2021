package com.alberto.advent.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DaySevenUtils {

  private static final String TEST = "_test";

  /**
   * Gets all the crab ships' positions.
   *
   * @param isTest Whether to use test data
   * @return A list of all the ships positions
   */
  public static List<Integer> getShipsPosition(boolean isTest) {
    final String path = isTest
        ? TEST
        : "";
    String allShips = "";
    try {
      allShips = Files.readString(
          Path.of("src/main/resources/files/crabships" + path + ".txt"));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Arrays.stream(allShips.split(",")).map(Integer::parseInt).sorted().collect(
        Collectors.toList());
  }

  /**
   * Calculates the median.
   * @param shipsPositions The list of positions
   * @return The median
   */
  public int calculateMedian(List<Integer> shipsPositions) {
    int median;
    if (shipsPositions.size() % 2 == 0) {
      median = (shipsPositions.get(shipsPositions.size() / 2) + shipsPositions.get(
          shipsPositions.size() / 2 - 1)) / 2;
    } else {
      median = shipsPositions.get(shipsPositions.size() / 2);
    }

    return median;
  }

}
