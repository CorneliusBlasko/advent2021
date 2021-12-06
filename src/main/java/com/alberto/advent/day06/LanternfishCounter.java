package com.alberto.advent.day06;

import com.alberto.advent.utils.DaySixUtils;
import lombok.Getter;

@Getter
public class LanternfishCounter {

  private long numberOfLanternfishes = 0L;

  private static final String PART_ONE = "first";
  private static final String PART_TWO = "second";

  /**
   * Constructor.
   *
   * @param isTest Whether to use test data
   */
  public LanternfishCounter(boolean isTest, boolean isPartOne) {
    String startData = DaySixUtils.getFishes(isTest);
    DaySixUtils.createLanternfishes(startData, isTest);
    if (isPartOne) {
      this.numberOfLanternfishes = DaySixUtils.breed(80);
    } else {
      this.numberOfLanternfishes = DaySixUtils.breed(256);
    }
    if (!isTest) {
      String part = isPartOne
          ? PART_ONE
          : PART_TWO;
      System.out.println(
          "Day five - The " + part + " number of lanternfish is: " + numberOfLanternfishes);
    }
  }
}
