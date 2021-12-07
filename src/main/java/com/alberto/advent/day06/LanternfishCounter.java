package com.alberto.advent.day06;

import com.alberto.advent.utils.DaySixUtils;
import lombok.Getter;

@Getter
public class LanternfishCounter {

  private final long lanternfish;

  private static final String PART_ONE = "first";
  private static final String PART_TWO = "second";

  /**
   * Constructor.
   *
   * @param isTest Whether to use test data
   */
  public LanternfishCounter(boolean isTest, boolean isPartOne) {
    if (isPartOne) {
      // DaySixUtils.breed(isTest, 80);
      DaySixUtils.breed(isTest, 80);
    } else {
      // DaySixUtils.breed(isTest, 256);
      DaySixUtils.breed(isTest, 256);
    }
    lanternfish = DaySixUtils.getTotalLanternfish();

    if (!isTest) {
      String part = isPartOne
          ? PART_ONE
          : PART_TWO;
      System.out.println(
          "Day six - The " + part + " number of lanternfish is: " + lanternfish);
    }
  }
}
