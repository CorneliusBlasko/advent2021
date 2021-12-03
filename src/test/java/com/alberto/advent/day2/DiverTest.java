package com.alberto.advent.day2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class DiverTest{

    private final Diver diver = new Diver();

    @Test
    public void test_00() throws IOException{
        Assertions.assertEquals(1698735, this.diver.findFinalPosition());
    }

    @Test
    public void test_01() throws IOException{
        Assertions.assertEquals(1594785890, this.diver.findFinalPositionWithAim());
    }

    @Test
    public void test_03() throws IOException{
        Assertions.assertEquals(1000, this.diver.retrieveMovements().size());
    }
}
