package com.alberto.advent.day08;

import com.alberto.advent.utils.DayEightUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SegmentsTest {

  @Test
  @DisplayName("Calculate unique output signals")
  public void test_00() {
    Assertions.assertEquals(456, DayEightUtils.processOutput(false));
  }

  @Test
  @DisplayName("Calculate unique output signals")
  public void test_10() {
    Segments.calculateSegments();
  }

}
