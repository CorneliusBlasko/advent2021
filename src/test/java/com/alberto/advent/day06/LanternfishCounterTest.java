package com.alberto.advent.day06;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LanternfishCounterTest {

  @Test
  @DisplayName("Test part one with test data")
  public void test_00() {
    LanternfishCounter counter1 = new LanternfishCounter(true, true);
    Assertions.assertEquals(5934, counter1.getNumberOfLanternfishes());
  }

  @Test
  @DisplayName("Test part one with real data")
  public void test_01() {
    LanternfishCounter counter2 = new LanternfishCounter(false, true);
    Assertions.assertEquals(350605, counter2.getNumberOfLanternfishes());
  }

  @Test
  @Disabled
  @DisplayName("Test part two with test data")
  public void test_02() {
    LanternfishCounter counter3 = new LanternfishCounter(true, false);
    Assertions.assertEquals(26984457539L, counter3.getNumberOfLanternfishes());
  }

  @Test
  @Disabled
  @DisplayName("Test part two with real data")
  public void test_03() {
    LanternfishCounter counter4 = new LanternfishCounter(false, false);
    Assertions.assertEquals(1592778185024L, counter4.getNumberOfLanternfishes());
  }

}
