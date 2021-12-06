package com.alberto.advent.day05;

import com.alberto.advent.utils.DayFiveUtils;
import java.util.List;

public class Hydrotermal {

  private final List<Vent> vents;
  private final int[][] map;
  private boolean isTest;

  public Hydrotermal(boolean isTest) {
    this.vents = DayFiveUtils.createVents(isTest);
    this.map = DayFiveUtils.createMap(isTest);
    this.isTest = isTest;
  }

  public int getNumberOfIntersections() {
    int rows = 1000;
    int numberOfIntersections = 0;
    for (Vent vent : vents) {
      if (null != vent.getRoute()) {
        for (Vent.Point point : vent.getRoute()) {
          map[point.getX()][point.getY()]++;
        }
      }
    }

    if(isTest){
      rows = 10;
    }
    for (int x = 0; x <rows; x++) {
      for (int y = 0; y <rows; y++) {
        if (map[x][y]>1) {numberOfIntersections++;}
      }
    }
    System.out.println("Number of intersetctions: " + numberOfIntersections);
//    DayFiveUtils.printMap(map);
    return numberOfIntersections;
  }

}
