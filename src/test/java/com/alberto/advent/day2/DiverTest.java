package com.alberto.advent.day2;

import java.io.IOException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DiverTest {

  private Diver diver;

  @BeforeEach
  public void setUp() throws IOException {
    this.diver = new Diver();
  }

  @AfterEach
  public void clean() {
    this.diver = null;
  }

  @Test
  @DisplayName("Sanity test")
  public void test_00() {
    Assertions.assertNotNull(this.diver);
  }

  @Test
  @DisplayName("Checks the final position value without aim")
  public void test_01() {
    Assertions.assertEquals(1698735, this.diver.getFinalPosition());
  }

  @Test
  @DisplayName("Checks the final position value with aim")
  public void test_02() {
    Assertions.assertEquals(1594785890, this.diver.getFinalPositionAimed());
  }

  @Test
  @DisplayName("Checks the correct parsing of the file")
  public void test_03() throws IOException {
    Assertions.assertEquals(1000, this.diver.retrieveMovements().size());
  }
}
