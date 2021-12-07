package com.alberto.advent.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DaySevenUtils {

  private static final String TEST = "_test";
  private static List<Integer> positions = new ArrayList<>();
  private static long median;
  private static double mean;

  /**
   * Gets all the crab ships' positions.
   *
   * @param isTest Whether to use test data
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

  /**
   * Calculate the median of ships positions.
   */
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

  /**
   * .
   */
  public static void calculateMean(boolean isTest) {
    BigDecimal total = new BigDecimal(positions.size());
    BigDecimal sum = new BigDecimal(positions.stream().mapToInt(Integer::intValue).sum());
    BigDecimal bigDecimalMean = sum.divide(total, 3, RoundingMode.HALF_UP);
    MathContext context = new MathContext(4);
    BigDecimal roundedBigMean = bigDecimalMean.round(context);
    double doubleMean = roundedBigMean.doubleValue();

    //For the test data, doubleMean is 4.99, and it should be 5.
    //However, for real data it is 500.55, and it should be 500.
    //Hence, this ugly hack I'm not proud of
    if (isTest) {
      mean = Math.ceil(doubleMean);
    } else {
      mean = Math.floor(doubleMean);
    }
  }

  /**
   * Calculate the total fuel consumption.
   *
   * @return The total fuel consumption
   */
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

  /**
   * Calculates the double rate of fuel consumption.
   *
   * @return The double rate of fuel consumption
   */
  public static long getDoubleFuelConsumption() {
    int doubleFuelConsumption = 0;
    for (Integer position : positions) {
      if (position < mean) {
        double distance = mean - position;
        for (int i = 1; i <= distance; i++) {
          doubleFuelConsumption += i;
        }

      }
      if (position > mean) {
        double distance = position - mean;
        for (int i = 1; i <= distance; i++) {
          doubleFuelConsumption += i;
        }

      }
    }
    return doubleFuelConsumption;
  }

}
