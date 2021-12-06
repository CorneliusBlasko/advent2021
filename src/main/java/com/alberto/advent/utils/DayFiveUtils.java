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

        Vent vent = new Vent(origin, destination, generateRoutesWithoutDiagonals(origin, destination));
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

  public static List<Vent.Point> generateRoutesWithoutDiagonals(Vent.Point origin,
      Vent.Point destination) {
    List<Vent.Point> points = new ArrayList<>();
    //A coordinate is valid if the origin and destination draw a straight line
    if (isStraightRoute(origin, destination)) {
      if (areXTheSame(origin, destination)) {
        if (origin.getY() > destination.getY()) { //Example: (0,9), (0,5)
          for (int i = destination.getY(); i <= origin.getY(); i++) {
            points.add(new Vent.Point(origin.getX(), i));
          }
        } else { //Example: (0,5), (0,9)
          for (int i = origin.getY(); i <= destination.getY(); i++) {
            points.add(new Vent.Point((origin.getX()), i));
          }
        }
      } else { //Example: (5,9), (1,9)
        if (origin.getX() > destination.getX()) {
          for (int i = destination.getX(); i <= origin.getX(); i++) {
            points.add(new Vent.Point(i, origin.getY()));
          }
        } else { //Example: (1,9), (5,9)
          for (int i = origin.getX(); i <= destination.getX(); i++) {
            points.add(new Vent.Point((i), origin.getY()));
          }
        }
      }
      return points;
    }
    return null;
  }

  public static boolean isStraightRoute(Vent.Point origin, Vent.Point destination) {
    return origin.getX() == destination.getX() || origin.getY() == destination.getY();
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
}
