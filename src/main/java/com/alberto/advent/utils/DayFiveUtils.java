package com.alberto.advent.utils;

import com.alberto.advent.day05.Vent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DayFiveUtils {

  public static List<Vent> coordinates() {
    try {
      List<String> allRows =
          Files.readAllLines(
              Path.of("src/main/resources/files"
                      + "/vents_test.txt"));


      Scanner sc = new Scanner(new BufferedReader(new FileReader("src/main/resources/files"
          + "/vents_test.txt")));
      List<Vent> vents = new ArrayList<>();
      for (String row : allRows) {
          String[] lineWithoutArrows = row.split("->");
          String[] originPoints =
              Arrays.toString(lineWithoutArrows[0].trim().split("\\s+")).replaceAll("\\[", "").replaceAll("\\]","").split(",");
          String[] destinationPoints =
              Arrays.toString(lineWithoutArrows[1].trim().split("\\s+")).replaceAll("\\[", "").replaceAll("\\]","").split(",");
          Vent.Coordinate origin = new Vent.Coordinate(Integer.parseInt(originPoints[0]),
              Integer.parseInt(originPoints[1]));
          Vent.Coordinate destination = new Vent.Coordinate(Integer.parseInt(destinationPoints[0]),
              Integer.parseInt(destinationPoints[1]));

          Vent vent = new Vent(origin, destination);
          vent.setRoute(generateRoute(origin, destination));
          vents.add(vent);

      }

      for (Vent vent : vents) {
        System.out.println(vent.toString());
      }
      return vents;

    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

  public static List<Vent.Coordinate> generateRoute(Vent.Coordinate origin,
      Vent.Coordinate destination) {
    List<Vent.Coordinate> coordinates = new ArrayList<>();
    if (isCoordinateValid(origin, destination)) {
      if (isXHigher(origin, destination)) {
        if(origin.getY() > destination.getY()) {
          for(int i = 0; i <= destination.getY(); i++) {
            coordinates.add(new Vent.Coordinate(origin.getX(), origin.getY() + 1)); // CHECK ME
          }
        } else {
          for(int i = 0; i <= origin.getY(); i++) {
            coordinates.add(new Vent.Coordinate((origin.getX() + 1), origin.getY())); // CHECK ME
          }
        }
      } else {
        if(origin.getX() > destination.getX()) {
          for(int i = 0; i<= origin.getX(); i++) {
            coordinates.add(new Vent.Coordinate(origin.getX() + i, origin.getX())); // FIX ME
          }
        } else {
          for(int i = 0; i <= destination.getX(); i++) {
            coordinates.add(new Vent.Coordinate((origin.getX() + i), origin.getY())); // IT WORKS!
          }
        }
      }

      return coordinates;

    } else {
      return null;
    }
  }

  public static boolean isCoordinateValid(Vent.Coordinate origin, Vent.Coordinate destination) {
    return origin.getX() == destination.getX() || origin.getY() == destination.getY();
  }

  public static boolean isXHigher(Vent.Coordinate origin, Vent.Coordinate destination) {
    return origin.getX() == destination.getX();
  }

  public static String[][] createMap() {
    int rows = 9;
    int columns = 9;
    String[][] map = new String[rows][columns];
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        map[i][j] = "0";
      }
    }

    for (String[] strings : map) {
      System.out.println(Arrays.toString(strings));
    }

    return map;

  }
}
