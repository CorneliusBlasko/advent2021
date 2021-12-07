package com.alberto.advent.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DaySevenUtils {

  private static final String TEST = "_test";
  private static List<Integer> positions = new ArrayList<>();
  private static int median;

  /**
   * Gets all the crab ships' positions.
   * @param isTest Whether to use test data
   * @return A list of all the ships positions
   */
  public static void setShipsPosition(boolean isTest) {
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
    positions = Arrays.stream(allShips.split(",")).map(Integer::parseInt).sorted().collect(
        Collectors.toList());
  }

  public static void calculateMedian() {
    int number;
    if (positions.size() % 2 == 0) {
      number = (positions.get(positions.size() / 2) + positions.get(
          positions.size() / 2 - 1)) / 2;
    } else {
      number = positions.get(positions.size() / 2);
    }
    median = number;
  }

  public static long getFuelConsumption() {
    int fuelConsumption = 0;
    //1. Calculate the distance of every ship to the median
    for (Integer position : positions) {
      if (position < median) {
        fuelConsumption += median - position;
      }
      if (position > median) {
        fuelConsumption += position - median;
      }
    }

    return fuelConsumption;
  }

  public static long getDoubleFuelConsumption() {
    int doubleFuelConsumption = 0;

    return doubleFuelConsumption;
  }

}
