package com.alberto.advent.day05;

import com.alberto.advent.utils.DayFiveUtils;
import java.util.List;

public class Hydrothermal {

  private final List<Vent> vents;
  private final int[][] map;
  private final boolean isTest;

  public Hydrothermal(boolean isTest) {
    this.vents = DayFiveUtils.createVents(isTest);
    this.map = DayFiveUtils.createMap(isTest);
    this.isTest = isTest;
  }

  public int processMap() {
    for (Vent vent : vents) {
      if (null != vent.getRoute()) {
        for (Vent.Point point : vent.getRoute()) {
          map[point.getX()][point.getY()]++;
        }
      }
    }

    int numberOfIntersections = calculateNumberOfIntersections(isTest, map);
    System.out.println("Day five - The first number of intersetctions is: " + numberOfIntersections);
//    DayFiveUtils.printMap(map);
    return numberOfIntersections;
  }

  private int calculateNumberOfIntersections(boolean isTest, int[][] map) {
    int rows = 1000;
    int numberOfIntersections = 0;

    if(isTest){
      rows = 10;
    }
    for (int x = 0; x <rows; x++) {
      for (int y = 0; y <rows; y++) {
        if (map[x][y]>1) {numberOfIntersections++;}
      }
    }
    return numberOfIntersections;
  }

}
