package com.alberto.advent.day1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SonarTest {

  List<Long> measurements = new ArrayList<>();
  private Sonar sonar = new Sonar();

  @BeforeEach
  public void setUp() throws IOException {
    this.sonar = new Sonar();
    this.measurements = this.sonar.retrieveMeasurements();
  }

  @AfterEach
  public void clean() {
    this.sonar = null;
    this.measurements = null;
  }

  @Test
  @DisplayName("Sanity test")
  public void test_00() {
    Assertions.assertNotNull(this.sonar);
    Assertions.assertNotNull(this.measurements);
    Assertions.assertNotEquals(0, this.measurements.size());
  }

  @Test
  @DisplayName("Check the number of measurements that are higher than the previous")
  public void test_01() throws IOException {
    Assertions.assertEquals(1722, sonar.getMeasurements());
  }

  @Test
  @DisplayName("Check the number of measurements that are higher than the previous in sliding "
      + "window form")
  public void test_02() throws IOException {
    Assertions.assertEquals(1748, sonar.getMeasurementsWithSlidingWindow());
  }
}
