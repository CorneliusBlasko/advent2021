package com.alberto.advent.dayone;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class CounterTest{

    @Test
    public void test_00() throws IOException{
        Counter counter = new Counter();
        List<Long> measurements = counter.createMeasurements();
        Assertions.assertEquals(1722, counter.measure(measurements));
    }

    @Test
    public void test_01() throws IOException{
        Counter counter = new Counter();
        List<Long> measurements = counter.createMeasurements();
        List<Long> windows = counter.createWindows(measurements);
        Assertions.assertEquals(1748, counter.measure(windows));
    }
}
