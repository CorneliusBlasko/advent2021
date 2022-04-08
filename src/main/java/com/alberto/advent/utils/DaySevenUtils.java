package com.alberto.advent.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DaySevenUtils extends InputParser {

  private static final String FILENAME = "crabships";
  private static List<Integer> positions = new ArrayList<>();
  private static long median;
  private static double averageFloor;
  private static double averageCeil;

  /**
   * Gets all the crab ships' positions.
   *
   * @param isTest Whether to use test data
   */
  public static void setShipsPosition(boolean isTest) {

    String allShips = getInputAsString(isTest, FILENAME);

    assert allShips != null;
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
  public static void calculateAverage() {
    BigDecimal total = new BigDecimal(positions.size());
    BigDecimal sum = new BigDecimal(positions.stream().mapToInt(Integer::intValue).sum());
    BigDecimal bigDecimalAverage = sum.divide(total, 3, RoundingMode.HALF_UP);
    MathContext context = new MathContext(4);
    BigDecimal roundedBigAverage = bigDecimalAverage.round(context);
    double doubleAverage = roundedBigAverage.doubleValue();

    // Both values are used to calculate the fuel consumption, then the lesser one is taken.
    // This is caused by the event of the average being a decimal value. In some cases, it should be
    // rounded up. In others, rounded down. To solve it, the lesser value of both is considered.
    averageCeil = Math.ceil(doubleAverage);
    averageFloor = Math.floor(doubleAverage);
  }

  /**
   * Calculate the total fuel consumption.
   *
   * @return The total fuel consumption
   */
  public static long getFuelConsumption() {
    int fuelConsumption = 0;
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
    double floor = compute(averageFloor);
    double ceil = compute(averageCeil);
    return (long) Math.min(floor, ceil);
  }

  /**
   * Calculates the fuel consumption for the average value passed by parameter.
   *
   * @return The total consumed fuel
   */
  public static double compute(double average) {
    int fuelConsumption = 0;
    for (Integer position : positions) {
      if (position < average) {
        double distance = average - position;
        for (int i = 1; i <= distance; i++) {
          fuelConsumption += i;
        }

      }
      if (position > average) {
        double distance = position - average;
        for (int i = 1; i <= distance; i++) {
          fuelConsumption += i;
        }

      }
    }
    return fuelConsumption;
  }

}
