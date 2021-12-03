package com.alberto.advent.day3;

import com.alberto.advent.utils.DayThreeUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class DayThreeTest{

    Diagnostic diagnostic = new Diagnostic();
    LifeSupport lifeSupport = new LifeSupport();


    @Test
    public void test_00(){
        Assertions.assertEquals(1000,Objects.requireNonNull(DayThreeUtils.getNumbers()).size());
    }

    @Test
    public void test_01(){
        Assertions.assertEquals("1", this.diagnostic.parseHigherBitByPosition(0));
    }

    @Test
    public void test_02(){
        Assertions.assertEquals(2520, this.diagnostic.calculate("gamma"));
        Assertions.assertEquals(1575, this.diagnostic.calculate("epsilon"));
    }

    @Test
    public void test_03(){
        Assertions.assertEquals(3969000, this.diagnostic.calculatePowerConsumption());
    }

    @Test
    public void test_04(){
        Assertions.assertEquals(2509, this.lifeSupport.getOxygenRating(DayThreeUtils.getNumbers()));
    }

    @Test
    public void test_05(){
        Assertions.assertEquals(1701, this.lifeSupport.getCO2Rating(DayThreeUtils.getNumbers()));
    }

    @Test
    public void test_06(){
        Assertions.assertEquals(4267809, this.lifeSupport.calculateLifeSupportRating());
    }
}
