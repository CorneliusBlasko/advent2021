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
import javax.print.attribute.standard.Destination;

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
          Vent.Point origin = new Vent.Point(Integer.parseInt(originPoints[0]),
              Integer.parseInt(originPoints[1]));
          Vent.Point destination = new Vent.Point(Integer.parseInt(destinationPoints[0]),
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

  public static List<Vent.Point> generateRoute(Vent.Point origin,
      Vent.Point destination) {
    List<Vent.Point> points = new ArrayList<>();
    if (isCoordinateValid(origin, destination)) {
      if (areXTheSame(origin, destination)) {
        if(origin.getY() > destination.getY()) { //Example: (0,9), (0,5)
          for(int i = destination.getY() ; i <= origin.getY(); i++) {
            points.add(new Vent.Point(origin.getX(), i)); // IT WORKS!
          }
        } else { //Example: (0,5), (0,9)
          for(int i = origin.getY(); i <= destination.getY(); i++) {
            points.add(new Vent.Point((origin.getX()), i)); // IT WORKS!
          }
        }
      } else { //Example: (5,9), (1,9)
        if(origin.getX() > destination.getX()) {
          for(int i = destination.getX(); i<= origin.getX(); i++) {
            points.add(new Vent.Point(i, origin.getY())); // FIX ME
          }
        } else { //Example: (1,9), (5,9)
          for(int i = origin.getX(); i <= destination.getX(); i++) {
            points.add(new Vent.Point((i), origin.getY())); // IT WORKS!
          }
        }
      }

      return points;

    } else {
      return null;
    }
  }

  public static boolean isCoordinateValid(Vent.Point origin, Vent.Point destination) {
    return origin.getX() == destination.getX() || origin.getY() == destination.getY();
  }

  public static boolean areXTheSame(Vent.Point origin, Vent.Point destination) {
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
