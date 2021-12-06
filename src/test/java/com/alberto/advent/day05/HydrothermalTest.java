package com.alberto.advent.day05;

import com.alberto.advent.utils.DayFiveUtils;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HydrothermalTest {

  @Test
  @DisplayName("Test map and vents creation")
  public void test_00() {
    DayFiveUtils.createVents(true, true);
    DayFiveUtils.createMap(true);
  }

  @Test
  @DisplayName("Test route creation when both point's Y coordinate is the same")
  public void test_01() {
    Vent.Point origin = new Vent.Point(1, 9);
    Vent.Point destination = new Vent.Point(5, 9);
    List<Vent.Point> route = DayFiveUtils.generateStraightRoute(origin, destination);
    Assertions.assertNotNull(route);
    Assertions.assertEquals(5, route.size());
    Assertions.assertEquals(4, route.get(3).getAbscissa());
    Assertions.assertEquals(9, route.get(3).getOrdinate());

    Vent.Point origin2 = new Vent.Point(0, 9);
    Vent.Point destination2 = new Vent.Point(5, 9);
    List<Vent.Point> route2 = DayFiveUtils.generateStraightRoute(origin2, destination2);
    Assertions.assertNotNull(route2);
    Assertions.assertEquals(6, route2.size());
    Assertions.assertEquals(3, route2.get(3).getAbscissa());
    Assertions.assertEquals(9, route.get(3).getOrdinate());

    Vent.Point origin3 = new Vent.Point(5, 9);
    Vent.Point destination3 = new Vent.Point(1, 9);
    List<Vent.Point> route3 = DayFiveUtils.generateStraightRoute(origin3, destination3);
    Assertions.assertNotNull(route3);
    Assertions.assertEquals(5, route3.size());
    Assertions.assertEquals(4, route3.get(3).getAbscissa());
    Assertions.assertEquals(9, route3.get(3).getOrdinate());
  }

  @Test
  @DisplayName("Test route creation when both point's X coordinate is the same")
  public void test_02() {

    //Coordinate X starting at a non-zero value
    Vent.Point origin = new Vent.Point(0, 9);
    Vent.Point destination = new Vent.Point(0, 5);
    List<Vent.Point> route = DayFiveUtils.generateStraightRoute(origin, destination);
    Assertions.assertNotNull(route);
    Assertions.assertEquals(5, route.size());
    Assertions.assertEquals(0, route.get(3).getAbscissa());
    Assertions.assertEquals(8, route.get(3).getOrdinate());

    //Coordinate X starting at a zero value
    Vent.Point origin2 = new Vent.Point(0, 9);
    Vent.Point destination2 = new Vent.Point(0, 0);
    List<Vent.Point> route2 = DayFiveUtils.generateStraightRoute(origin2, destination2);
    Assertions.assertNotNull(route2);
    Assertions.assertEquals(10, route2.size());
    Assertions.assertEquals(0, route2.get(3).getAbscissa());
    Assertions.assertEquals(3, route2.get(3).getOrdinate());

    //Inverting the coordinates
    Vent.Point origin3 = new Vent.Point(0, 5);
    Vent.Point destination3 = new Vent.Point(0, 9);
    List<Vent.Point> route3 = DayFiveUtils.generateStraightRoute(origin3, destination3);
    Assertions.assertNotNull(route3);
    Assertions.assertEquals(5, route3.size());
    Assertions.assertEquals(0, route3.get(3).getAbscissa());
    Assertions.assertEquals(8, route3.get(3).getOrdinate());
  }

  @Test
  @DisplayName("Calculate part one number with test data")
  public void test_03() {
    Hydrothermal hydrothermal = new Hydrothermal(true, true);
    Assertions.assertEquals(5, hydrothermal.processMap(true));
  }


  @Test
  @DisplayName("Calculate part one number with real data")
  public void test_04() {
    Hydrothermal hydrothermal = new Hydrothermal(false, true);
    Assertions.assertEquals(4993, hydrothermal.processMap(true));
  }

  @Test
  @DisplayName("Calculate part two number with test data")
  public void test_05() {
    Hydrothermal hydrothermal = new Hydrothermal(true, false);
    Assertions.assertEquals(12, hydrothermal.processMap(false));
  }

  @Test
  @DisplayName("Calculate part two number with real data")
  public void test_06() {
    Hydrothermal hydrothermal = new Hydrothermal(false, false);
    Assertions.assertEquals(21101, hydrothermal.processMap(false));
  }

  @Test
  @DisplayName("Test the creation of an ascending right route")
  public void test_07() {
    List<Vent.Point> points = new ArrayList<>();
    Vent.Point origin = new Vent.Point(0, 0);
    Vent.Point destination = new Vent.Point(8, 8);
    points.add(origin);
    points.add(new Vent.Point(1, 1));
    points.add(new Vent.Point(2, 2));
    points.add(new Vent.Point(3, 3));
    points.add(new Vent.Point(4, 4));
    points.add(new Vent.Point(5, 5));
    points.add(new Vent.Point(6, 6));
    points.add(new Vent.Point(7, 7));
    points.add(destination);

    List<Vent.Point> resultRoutes = DayFiveUtils.generateDiagonalRoute(origin, destination);

    for (int i = 0; i < points.size(); i++) {
      Assertions.assertEquals(points.get(i).getAbscissa(), resultRoutes.get(i).getAbscissa());
      Assertions.assertEquals(points.get(i).getOrdinate(), resultRoutes.get(i).getOrdinate());
    }
  }

  @Test
  @DisplayName("Test the creation of an descending left route")
  public void test_08() {
    List<Vent.Point> points = new ArrayList<>();
    Vent.Point origin = new Vent.Point(10, 5);
    Vent.Point destination = new Vent.Point(7, 2);
    points.add(origin);
    points.add(new Vent.Point(9, 4));
    points.add(new Vent.Point(8, 3));
    points.add(destination);

    List<Vent.Point> resultRoutes = DayFiveUtils.generateDiagonalRoute(origin, destination);

    for (int i = 0; i < points.size(); i++) {
      Assertions.assertEquals(points.get(i).getAbscissa(), resultRoutes.get(i).getAbscissa());
      Assertions.assertEquals(points.get(i).getOrdinate(), resultRoutes.get(i).getOrdinate());
    }
  }

  @Test
  @DisplayName("Test the creation of a descending right route")
  public void test_09() {

    List<Vent.Point> points = new ArrayList<>();
    Vent.Point origin = new Vent.Point(9, 11);
    Vent.Point destination = new Vent.Point(15, 5);
    points.add(origin);
    points.add(new Vent.Point(10, 10));
    points.add(new Vent.Point(11, 9));
    points.add(new Vent.Point(12, 8));
    points.add(new Vent.Point(13, 7));
    points.add(new Vent.Point(14, 6));
    points.add(destination);

    List<Vent.Point> resultRoutes = DayFiveUtils.generateDiagonalRoute(origin, destination);

    for (int i = 0; i < points.size(); i++) {
      Assertions.assertEquals(points.get(i).getAbscissa(), resultRoutes.get(i).getAbscissa());
      Assertions.assertEquals(points.get(i).getOrdinate(), resultRoutes.get(i).getOrdinate());
    }
  }

  @Test
  @DisplayName("Test the creation of a descending left route")
  public void test_10() {
    List<Vent.Point> points = new ArrayList<>();
    Vent.Point origin = new Vent.Point(12, 9);
    Vent.Point destination = new Vent.Point(7, 4);
    points.add(origin);
    points.add(new Vent.Point(11, 8));
    points.add(new Vent.Point(10, 7));
    points.add(new Vent.Point(9, 6));
    points.add(new Vent.Point(8, 5));
    points.add(destination);

    List<Vent.Point> resultRoutes = DayFiveUtils.generateDiagonalRoute(origin, destination);

    for (int i = 0; i < points.size(); i++) {
      Assertions.assertEquals(points.get(i).getAbscissa(), resultRoutes.get(i).getAbscissa());
      Assertions.assertEquals(points.get(i).getOrdinate(), resultRoutes.get(i).getOrdinate());
    }
  }

}
