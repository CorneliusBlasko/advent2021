package com.alberto.advent.utils;

import com.alberto.advent.day05.Vent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DayFiveUtils extends InputParser {

  private static final String FILENAME = "vents";

  /**
   * Creates the list of Vents based on the input file.
   *
   * @param isTest Whether the data is for test purposes or real
   * @return A list of all the vents in the input data
   */
  public static List<Vent> createVents(boolean isTest, boolean isPartOne) {

    List<String> allRows = getInputAsStringList(isTest, FILENAME);

    List<Vent.Point> route;

    List<Vent> vents = new ArrayList<>();
    assert allRows != null;
    for (String row : allRows) {
      String[] lineWithoutArrows = row.split("->");
      String[] originCoordinates = cleanUpCoordinates(lineWithoutArrows[0]);
      String[] destinationCoordinates = cleanUpCoordinates(lineWithoutArrows[1]);

      Vent.Point origin = new Vent.Point(
          Integer.parseInt(originCoordinates[0]),
          Integer.parseInt(originCoordinates[1]));

      Vent.Point destination = new Vent.Point(
          Integer.parseInt(destinationCoordinates[0]),
          Integer.parseInt(destinationCoordinates[1]));

      if (routeIsStraight(origin, destination) || isPartOne) {
        route = generateStraightRoute(origin, destination);
      } else {
        route = generateDiagonalRoute(origin, destination);
      }
      Vent vent =
          new Vent(origin, destination, route);
      vents.add(vent);
    }
    return vents;

  }

  private static String[] cleanUpCoordinates(String dirtyLine) {
    return Arrays.toString(dirtyLine.trim().split("\\s+")).replaceAll("\\[", "")
        .replaceAll("]", "").split(",");
  }

  /**
   * Generates a route in which all the points form a straight vertical or horizontal line.
   *
   * @param origin      The point of origin
   * @param destination The end point
   * @return A straight route
   */
  public static List<Vent.Point> generateStraightRoute(Vent.Point origin,
      Vent.Point destination) {
    List<Vent.Point> route = new ArrayList<>();
    //A coordinate is valid if the origin and destination draw a straight line
    if (routeIsStraight(origin, destination)) {
      if (areXTheSame(origin, destination)) {
        if (origin.getOrdinate() > destination.getOrdinate()) { //Example: (0,9), (0,5)
          for (int i = destination.getOrdinate(); i <= origin.getOrdinate(); i++) {
            route.add(new Vent.Point(origin.getAbscissa(), i));
          }
        } else { //Example: (0,5), (0,9)
          for (int i = origin.getOrdinate(); i <= destination.getOrdinate(); i++) {
            route.add(new Vent.Point((origin.getAbscissa()), i));
          }
        }
      } else { //Example: (5,9), (1,9)
        if (origin.getAbscissa() > destination.getAbscissa()) {
          for (int i = destination.getAbscissa(); i <= origin.getAbscissa(); i++) {
            route.add(new Vent.Point(i, origin.getOrdinate()));
          }
        } else { //Example: (1,9), (5,9)
          for (int i = origin.getAbscissa(); i <= destination.getAbscissa(); i++) {
            route.add(new Vent.Point((i), origin.getOrdinate()));
          }
        }
      }
      return route;
    }
    return null;
  }

  public static boolean routeIsStraight(Vent.Point origin, Vent.Point destination) {
    return origin.getAbscissa() == destination.getAbscissa()
        || origin.getOrdinate() == destination.getOrdinate();
  }

  /**
   * Checks whether a diagonal is valid. A valid diagonal only increases or decreases by one unit.
   * For example, [(0,0), (8,8)] is a valid diagonal. However, [(0,8), (7,8)] is not.
   *
   * @param origin      The point of origin
   * @param destination The end point
   * @return True if the diagonal formed by the origin and destination is valid
   */
  public static boolean isValidDiagonal(Vent.Point origin, Vent.Point destination) {
    return (arrowIsAscendingToTheRight(origin, destination)
        && Math.abs(destination.getAbscissa() - origin.getAbscissa())
        == Math.abs(destination.getOrdinate() - origin.getOrdinate()))
        || (arrowIsAscendingToTheLeft(origin, destination)
        && Math.abs(origin.getAbscissa() - destination.getAbscissa())
        == Math.abs(destination.getOrdinate() - origin.getOrdinate()))
        || (arrowIsDescendingToTheRight(origin, destination)
        && Math.abs(origin.getOrdinate() - destination.getOrdinate())
        == Math.abs(destination.getAbscissa() - origin.getAbscissa()))
        || (arrowIsDescendingToTheLeft(origin, destination)
        && Math.abs(origin.getAbscissa() - destination.getAbscissa())
        == Math.abs(destination.getOrdinate() - origin.getOrdinate()));
  }

  /**
   * Generates a route in which all the points form a diagonal line.
   *
   * @param origin      The point of origin
   * @param destination The end point
   * @return A straight route
   */
  public static List<Vent.Point> generateDiagonalRoute(Vent.Point origin, Vent.Point destination) {
    //A perfect diagonal increases or decreases in the same amount for all the coordinates
    List<Vent.Point> diagonalRoute = new ArrayList<>();
    int steps;
    if (isValidDiagonal(origin, destination)) {
      // Black arrow: (3,4), (8,9)
      if (arrowIsAscendingToTheRight(origin, destination)) {
        steps = destination.getAbscissa() - origin.getAbscissa();
        for (int i = 0; i <= steps; i++) {
          diagonalRoute.add(new Vent.Point(origin.getAbscissa() + i, origin.getOrdinate() + i));
        }
      }
      // Blue arrow: (21,5), (16,10)
      if (arrowIsAscendingToTheLeft(origin, destination)) {
        steps = origin.getAbscissa() - destination.getAbscissa();
        for (int i = 0; i <= steps; i++) {
          diagonalRoute.add(new Vent.Point(origin.getAbscissa() - i, origin.getOrdinate() + i));
        }
      }
      // Pink arrow: (9,11), (15,5)
      if (arrowIsDescendingToTheRight(origin, destination)) {
        steps = origin.getOrdinate() - destination.getOrdinate();
        for (int i = 0; i <= steps; i++) {
          diagonalRoute.add(new Vent.Point(origin.getAbscissa() + i, origin.getOrdinate() - i));
        }
      }
      // Green arrow: (12,9), (7,4)
      if (arrowIsDescendingToTheLeft(origin, destination)) {
        steps = origin.getAbscissa() - destination.getAbscissa();
        for (int i = 0; i <= steps; i++) {
          diagonalRoute.add(new Vent.Point(origin.getAbscissa() - i, origin.getOrdinate() - i));
        }
      }
    }
    return diagonalRoute;
  }

  // Black arrow: (3,4), (8,9)
  public static boolean arrowIsAscendingToTheRight(Vent.Point origin, Vent.Point destination) {
    return origin.getAbscissa() < destination.getAbscissa()
        && origin.getOrdinate() < destination.getOrdinate();
  }

  // Blue arrow: (21,5), (16,10)
  public static boolean arrowIsAscendingToTheLeft(Vent.Point origin, Vent.Point destination) {
    return origin.getAbscissa() > destination.getAbscissa()
        && origin.getOrdinate() < destination.getOrdinate();
  }

  // Pink arrow: (9,11), (15,5)
  public static boolean arrowIsDescendingToTheRight(Vent.Point origin, Vent.Point destination) {
    return origin.getAbscissa() < destination.getAbscissa()
        && origin.getOrdinate() > destination.getOrdinate();
  }

  // Green arrow: (12,9), (7,4)
  public static boolean arrowIsDescendingToTheLeft(Vent.Point origin, Vent.Point destination) {
    return origin.getAbscissa() > destination.getAbscissa()
        && origin.getOrdinate() > destination.getOrdinate();
  }

  public static boolean areXTheSame(Vent.Point origin, Vent.Point destination) {
    return origin.getAbscissa() == destination.getAbscissa();
  }

  /**
   * Creates an empty map.
   *
   * @param isTest Whether the map must allocate test or real data
   * @return A map filled with 0s in each position
   */
  public static int[][] createMap(boolean isTest) {
    int rows = 1000;
    if (isTest) {
      rows = 10;
    }

    int[][] map = new int[rows][rows];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < rows; j++) {
        map[i][j] = 0;
      }
    }

    return map;
  }

  /**
   * Prints the map for testing purposes.
   *
   * @param map The map to be printed
   */
  public static void printMap(int[][] map) {
    for (int[] position : map) {
      System.out.println(Arrays.toString(position));
    }
  }
}
