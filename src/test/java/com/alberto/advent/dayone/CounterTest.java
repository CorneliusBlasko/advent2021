package com.alberto.advent.dayone;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class CounterTest{

    @Test
    public void test_00() throws IOException{
        Counter counter = new Counter();
        Assertions.assertEquals(1722, counter.measure());
    }
}
