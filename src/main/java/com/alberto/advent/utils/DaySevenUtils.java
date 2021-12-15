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
  private static double meanFloor;
  private static double meanCeil;

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
  public static void calculateMean() {
    BigDecimal total = new BigDecimal(positions.size());
    BigDecimal sum = new BigDecimal(positions.stream().mapToInt(Integer::intValue).sum());
    BigDecimal bigDecimalMean = sum.divide(total, 3, RoundingMode.HALF_UP);
    MathContext context = new MathContext(4);
    BigDecimal roundedBigMean = bigDecimalMean.round(context);
    double doubleMean = roundedBigMean.doubleValue();

    //Both values are used to calculate the fuel consumption, then the lesser one is taken.
    //This is caused by the event of having a decimal value as mean. In some cases, it should be
    // rounded up. In others, rounded down. To solve it, the lesser value of both is considered.
    meanCeil = Math.ceil(doubleMean);
    meanFloor = Math.floor(doubleMean);
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
    double floor = compute(meanFloor);
    double ceil = compute(meanCeil);
    return (long) Math.min(floor, ceil);
  }

  /**
   * Calculates the fuel consumption for the mean value passed by parameter.
   *
   * @return The total consumed fuel
   */
  public static double compute(double mean) {
    int fuelConsumption = 0;
    for (Integer position : positions) {
      if (position < mean) {
        double distance = mean - position;
        for (int i = 1; i <= distance; i++) {
          fuelConsumption += i;
        }

      }
      if (position > mean) {
        double distance = position - mean;
        for (int i = 1; i <= distance; i++) {
          fuelConsumption += i;
        }

      }
    }
    return fuelConsumption;
  }

}
