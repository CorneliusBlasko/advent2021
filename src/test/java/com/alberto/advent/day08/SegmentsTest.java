package com.alberto.advent.day08;

import com.alberto.advent.utils.DayEightUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SegmentsTest {

  @Test
  @DisplayName("Calculate fuel consumption using test data")
  public void test_00() {
    Assertions.assertEquals(456, DayEightUtils.processOutput(false));
  }

}
