package com.alberto.advent.day05;

import com.alberto.advent.utils.DayFiveUtils;
import java.util.List;

public class Hydrothermal {

  private final List<Vent> vents;
  private final int[][] map;
  private final boolean isTest;

  private static final String PART_ONE = "first";
  private static final String PART_TWO = "second";

  /**
   * Starts the vent count.
   *
   * @param isTest Whether the data is test or real
   */
  public Hydrothermal(boolean isTest, boolean isPartOne) {
    this.vents = DayFiveUtils.createVents(isTest, isPartOne);
    this.map = DayFiveUtils.createMap(isTest);
    this.isTest = isTest;
  }

  /**
   * Marks all the vents points in the map.
   *
   * @return The final number of intersections where amount is greater than 2
   */
  public int processMap(boolean isPartOne) {
    for (Vent vent : vents) {
      if (null != vent.getRoute()) {
        for (Vent.Point point : vent.getRoute()) {
          map[point.getAbscissa()][point.getOrdinate()]++;
        }
      }
    }

    int numberOfIntersections = calculateNumberOfIntersections(isTest, map);
    if (!isTest) {
      String part = isPartOne
          ? PART_ONE
          : PART_TWO;
      System.out.println(
          "Day five - The " + part + " number of vent intersections is: " + numberOfIntersections);
    }
    //DayFiveUtils.printMap(map);
    return numberOfIntersections;
  }

  private int calculateNumberOfIntersections(boolean isTest, int[][] map) {
    int rows = 1000;
    int numberOfIntersections = 0;

    if (isTest) {
      rows = 10;
    }
    for (int x = 0; x < rows; x++) {
      for (int y = 0; y < rows; y++) {
        if (map[x][y] > 1) {
          numberOfIntersections++;
        }
      }
    }
    return numberOfIntersections;
  }

}
