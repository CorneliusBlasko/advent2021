package com.alberto.advent.day04;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BingoTest {

  private Bingo bingo;

  @BeforeEach
  public void setUp(){
    this.bingo = new Bingo();
  }

  @AfterEach
  public void cleanUp(){
    this.bingo = null;
  }

  @Test
  @DisplayName("Sanity test")
  public void test_00(){
    Assertions.assertNotNull(this.bingo);
  }

  @Test
  @DisplayName("Parsing test")
  public void test_01(){
    Assertions.assertEquals(100, this.bingo.getBingoNumbers().size());
    Assertions.assertEquals(598, this.bingo.generateBoards().size());
  }


}
