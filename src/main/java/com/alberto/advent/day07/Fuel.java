package com.alberto.advent.day07;

import com.alberto.advent.utils.DaySevenUtils;

public class Fuel {

  /**
   * Calculate both fuel consumptions.
   */
  public static void calculateFuelConsumptions() {
    DaySevenUtils.setShipsPosition(false);
    DaySevenUtils.calculateMedian();
    System.out.println(
        "Day seven - Part one fuel consumption is: " + DaySevenUtils.getFuelConsumption());
    DaySevenUtils.calculateMean();
    System.out.println(
        "Day seven - Part two fuel consumption is: " + DaySevenUtils.getDoubleFuelConsumption());
  }
}
