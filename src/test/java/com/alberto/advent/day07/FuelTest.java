package com.alberto.advent.day07;

import com.alberto.advent.utils.DaySevenUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FuelTest {

  @Test
  @DisplayName("Calculate fuel consumption using test data")
  public void test_00() {
    DaySevenUtils.setShipsPosition(true);
    DaySevenUtils.calculateMedian();
    Assertions.assertEquals(37, DaySevenUtils.getFuelConsumption());
  }

  @Test
  @DisplayName("Calculate fuel consumption using real data")
  public void test_01() {
    DaySevenUtils.setShipsPosition(false);
    DaySevenUtils.calculateMedian();
    Assertions.assertEquals(364898, DaySevenUtils.getFuelConsumption());
  }

  @Test
  @DisplayName("Calculate double fuel consumption using test data")
  public void test_02() {
    DaySevenUtils.setShipsPosition(true);
    DaySevenUtils.calculateMean(true);
    Assertions.assertEquals(168, DaySevenUtils.getDoubleFuelConsumption());
  }

  @Test
  @DisplayName("Calculate double fuel consumption using real data")
  public void test_03() {
    DaySevenUtils.setShipsPosition(false);
    DaySevenUtils.calculateMean(false);
    Assertions.assertEquals(104149091, DaySevenUtils.getDoubleFuelConsumption());
  }

  @Test
  @DisplayName("Calculate both values")
  public void test_04() {
    Fuel.calculateFuelConsumptions();
  }

}
