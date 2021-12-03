package com.alberto.advent.day3;

import com.alberto.advent.utils.DayThreeUtils;
import org.junit.jupiter.api.*;

import java.util.Objects;

public class DayThreeTest{

    Diagnostic diagnostic;
    LifeSupport lifeSupport;

    @BeforeEach
    public void setUp() {
        this.diagnostic = new Diagnostic();
        this.lifeSupport = new LifeSupport();
    }

    @AfterEach
    public void clean() {
        this.diagnostic = null;
        this.lifeSupport = null;
    }

    @Test
    @DisplayName("Sanity test")
    public void test_00() {
        Assertions.assertNotNull(this.diagnostic);
        Assertions.assertNotNull(this.lifeSupport);
    }

    @Test
    @DisplayName("Checks the correct parsing of the file")
    public void test_01(){
        Assertions.assertEquals(1000,Objects.requireNonNull(DayThreeUtils.getNumbers()).size());
    }

    @Test
    @DisplayName("Checks the diagnostic")
    public void test_02(){
        Assertions.assertEquals("1", this.diagnostic.getMostFrequentBitByPosition(0));
        Assertions.assertEquals(2520, this.diagnostic.calculate("gamma"));
        Assertions.assertEquals(1575, this.diagnostic.calculate("epsilon"));
        Assertions.assertEquals(3969000, this.diagnostic.calculatePowerConsumption());
    }

    @Test
    @DisplayName("Checks the life support")
    public void test_03(){
        Assertions.assertEquals(2509, this.lifeSupport.getOxygenRating(DayThreeUtils.getNumbers()));
        Assertions.assertEquals(1701, this.lifeSupport.getCO2Rating(DayThreeUtils.getNumbers()));
        Assertions.assertEquals(4267809, this.lifeSupport.calculateLifeSupportRating());
    }
}
