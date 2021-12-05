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
          String[] originArray =
              Arrays.toString(lineWithoutArrows[0].trim().split("\\s+")).replaceAll("\\[", "").replaceAll("\\]","").split(",");
          String[] destinationArray =
              Arrays.toString(lineWithoutArrows[1].trim().split("\\s+")).replaceAll("\\[", "").replaceAll("\\]","").split(",");
          Vent.Point originPoint = new Vent.Point(Integer.parseInt(originArray[0]),
              Integer.parseInt(originArray[1]));
          Vent.Point destinationPoint = new Vent.Point(Integer.parseInt(destinationArray[0]),
              Integer.parseInt(destinationArray[1]));
          Vent vent = new Vent(originPoint, destinationPoint);
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
