package com.alberto.advent.day05;

import com.alberto.advent.utils.DayFiveUtils;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class VentMapperTest {

  @Test
  public void test_00() {
    DayFiveUtils.coordinates();
    DayFiveUtils.createMap();
  }

  @Test
  @DisplayName("Test route creation when both point's Y coordinate is the same")
  public void test_01() {
    Vent.Point origin = new Vent.Point(1, 9);
    Vent.Point destination = new Vent.Point(5, 9);
    List<Vent.Point> route = DayFiveUtils.generateRoute(origin, destination);
    Assertions.assertNotNull(route);
    Assertions.assertEquals(5, route.size());
    Assertions.assertEquals(4, route.get(3).getX());
    Assertions.assertEquals(9, route.get(3).getY());

    Vent.Point origin2 = new Vent.Point(0, 9);
    Vent.Point destination2 = new Vent.Point(5, 9);
    List<Vent.Point> route2 = DayFiveUtils.generateRoute(origin2, destination2);
    Assertions.assertNotNull(route2);
    Assertions.assertEquals(6, route2.size());
    Assertions.assertEquals(3, route2.get(3).getX());
    Assertions.assertEquals(9, route.get(3).getY());

    Vent.Point origin3 = new Vent.Point(5, 9);
    Vent.Point destination3 = new Vent.Point(1, 9);
    List<Vent.Point> route3 = DayFiveUtils.generateRoute(origin3, destination3);
    Assertions.assertNotNull(route3);
    Assertions.assertEquals(5, route3.size());
    Assertions.assertEquals(4, route3.get(3).getX());
    Assertions.assertEquals(9, route3.get(3).getY());
  }

  @Test
  @DisplayName("Test route creation when both point's X coordinate is the same")
  public void test_02() {

    //Coordinate X starting at a non-zero value
    Vent.Point origin = new Vent.Point(0, 9);
    Vent.Point destination = new Vent.Point(0, 5);
    List<Vent.Point> route = DayFiveUtils.generateRoute(origin, destination);
    Assertions.assertNotNull(route);
    Assertions.assertEquals(5, route.size());
    Assertions.assertEquals(0, route.get(3).getX());
    Assertions.assertEquals(8, route.get(3).getY());

    //Coordinate X starting at a zero value
    Vent.Point origin2 = new Vent.Point(0, 9);
    Vent.Point destination2 = new Vent.Point(0, 0);
    List<Vent.Point> route2 = DayFiveUtils.generateRoute(origin2, destination2);
    Assertions.assertNotNull(route2);
    Assertions.assertEquals(10, route2.size());
    Assertions.assertEquals(0, route2.get(3).getX());
    Assertions.assertEquals(3, route2.get(3).getY());

    //Inverting the coordinates
    Vent.Point origin3 = new Vent.Point(0, 5);
    Vent.Point destination3 = new Vent.Point(0, 9);
    List<Vent.Point> route3 = DayFiveUtils.generateRoute(origin3, destination3);
    Assertions.assertNotNull(route3);
    Assertions.assertEquals(5, route3.size());
    Assertions.assertEquals(0, route3.get(3).getX());
    Assertions.assertEquals(8, route3.get(3).getY());
  }

}
