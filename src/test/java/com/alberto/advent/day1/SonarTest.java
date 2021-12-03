package com.alberto.advent.day1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class SonarTest{

    @Test
    public void test_00() throws IOException{
        Sonar sonar = new Sonar();
        List<Long> measurements = sonar.retrieveMeasurements();
        Assertions.assertEquals(1722, sonar.sweep(measurements));
    }

    @Test
    public void test_01() throws IOException{
        Sonar sonar = new Sonar();
        List<Long> measurements = sonar.retrieveMeasurements();
        List<Long> windows = sonar.createWindows(measurements);
        Assertions.assertEquals(1748, sonar.sweep(windows));
    }
}
