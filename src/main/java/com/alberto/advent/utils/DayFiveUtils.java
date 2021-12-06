package com.alberto.advent.utils;

import com.alberto.advent.day05.Vent;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DayFiveUtils {

  private static final String TEST = "_test";

  public static List<Vent> createVents(boolean isTest) {
    List<Vent.Point> route;
    final String path = isTest
        ? TEST
        : "";
    try {
      List<String> allRows =
          Files.readAllLines(
              Path.of("src/main/resources/files"
                  + "/vents" + path + ".txt"));

      List<Vent> vents = new ArrayList<>();
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

        if(isStraightRoute(origin, destination)) {
          route = generateRouteWithoutDiagonals(origin, destination);
        } else {
          route = generateRouteWithDiagonals(origin, destination);
        }
        Vent vent =
            new Vent(origin, destination, route);
        vents.add(vent);
      }
      return vents;

    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private static String[] cleanUpCoordinates(String dirtyLine) {
    return Arrays.toString(dirtyLine.trim().split("\\s+")).replaceAll("\\[", "")
        .replaceAll("]", "").split(",");
  }

  public static List<Vent.Point> generateRouteWithoutDiagonals(Vent.Point origin,
      Vent.Point destination) {
    List<Vent.Point> route = new ArrayList<>();
    //A coordinate is valid if the origin and destination draw a straight line
    if (isStraightRoute(origin, destination)) {
      if (areXTheSame(origin, destination)) {
        if (origin.getY() > destination.getY()) { //Example: (0,9), (0,5)
          for (int i = destination.getY(); i <= origin.getY(); i++) {
            route.add(new Vent.Point(origin.getX(), i));
          }
        } else { //Example: (0,5), (0,9)
          for (int i = origin.getY(); i <= destination.getY(); i++) {
            route.add(new Vent.Point((origin.getX()), i));
          }
        }
      } else { //Example: (5,9), (1,9)
        if (origin.getX() > destination.getX()) {
          for (int i = destination.getX(); i <= origin.getX(); i++) {
            route.add(new Vent.Point(i, origin.getY()));
          }
        } else { //Example: (1,9), (5,9)
          for (int i = origin.getX(); i <= destination.getX(); i++) {
            route.add(new Vent.Point((i), origin.getY()));
          }
        }
      }
      return route;
    }
    return null;
  }

  public static boolean isStraightRoute(Vent.Point origin, Vent.Point destination) {
    return origin.getX() == destination.getX() || origin.getY() == destination.getY();
  }

  public static boolean isValidDiagonal(Vent.Point origin, Vent.Point destination) {
    return (arrowIsAscendingToTheRight(origin, destination)
        && Math.abs(destination.getX() - origin.getX()) ==
        Math.abs(destination.getY() - origin.getY()))
        || (arrowIsAscendingToTheLeft(origin, destination)
        && Math.abs(origin.getX() - destination.getX()) ==
        Math.abs(destination.getY() - origin.getY()))
        || (arrowIsDescendingToTheRight(origin, destination)
        && Math.abs(origin.getY() - destination.getY()) ==
        Math.abs(destination.getX() - origin.getX()))
        || (arrowIsDescendingToTheLeft(origin, destination)
        && Math.abs(origin.getX() - destination.getX()) ==
        Math.abs(destination.getY() - origin.getY()));
  }

  public static List<Vent.Point> generateRouteWithDiagonals(Vent.Point origin, Vent.Point destination) {
    //A perfect diagonal increases or decreases in the same amount for all the coordinates
    List<Vent.Point> diagonalRoute = new ArrayList<>();
    int steps;
    if(isValidDiagonal(origin, destination)) {
      // Black arrow: (3,4), (8,9)
      if (arrowIsAscendingToTheRight(origin, destination)) {
        steps = destination.getX() - origin.getX();
        for (int i = 0; i <= steps; i++) {
          diagonalRoute.add(new Vent.Point(origin.getX() + i, origin.getY() + i));
        }
      }
      // Blue arrow: (21,5), (16,10)
      if (arrowIsAscendingToTheLeft(origin, destination)) {
        steps = origin.getX() - destination.getX();
        for (int i = 0; i <= steps; i++) {
          diagonalRoute.add(new Vent.Point(origin.getX() - i, origin.getY() + i));
        }
      }
      // Pink arrow: (9,11), (15,5)
      if (arrowIsDescendingToTheRight(origin, destination)) {
        steps = origin.getY() - destination.getY();
        for (int i = 0; i <= steps; i++) {
          diagonalRoute.add(new Vent.Point(origin.getX() + i, origin.getY() - i));
        }
      }
      // Green arrow: (12,9), (7,4)
      if (arrowIsDescendingToTheLeft(origin, destination)) {
        steps = origin.getX() - destination.getX();
        for (int i = 0; i <= steps; i++) {
          diagonalRoute.add(new Vent.Point(origin.getX() - i, origin.getY() - i));
        }
      }
    }
    return diagonalRoute;
  }

  // Black arrow: (3,4), (8,9)
  public static boolean arrowIsAscendingToTheRight(Vent.Point origin, Vent.Point destination) {
    return origin.getX() < destination.getX() && origin.getY() < destination.getY();
  }

  // Blue arrow: (21,5), (16,10)
  public static boolean arrowIsAscendingToTheLeft(Vent.Point origin, Vent.Point destination) {
    return origin.getX() > destination.getX() && origin.getY() < destination.getY();
  }

  // Pink arrow: (9,11), (15,5)
  public static boolean arrowIsDescendingToTheRight(Vent.Point origin, Vent.Point destination) {
    return origin.getX() < destination.getX() && origin.getY() > destination.getY();
  }

  // Green arrow: (12,9), (7,4)
  public static boolean arrowIsDescendingToTheLeft(Vent.Point origin, Vent.Point destination) {
    return origin.getX() > destination.getX() && origin.getY() > destination.getY();
  }

  public static boolean areXTheSame(Vent.Point origin, Vent.Point destination) {
    return origin.getX() == destination.getX();
  }

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

  public static void printMap(int [][] map) {
    for (int[] position : map) {
      System.out.println(Arrays.toString(position));
    }
  }
}
