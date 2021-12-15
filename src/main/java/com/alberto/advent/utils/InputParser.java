package com.alberto.advent.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class InputParser {

  private static final String TEST = "_test";
  private static final String ROUTE = "src/main/resources/files/";
  private static final String EXTENSION = ".txt";


  /**
   * Returns a String list representation of the input data.
   *
   * @param isTest   Whether it's test data
   * @param fileName The name of the file to parse
   * @return A String list representation of the input data
   */
  public static List<String> getInputAsStringList(boolean isTest, String fileName) {
    final String path = isTest
        ? TEST
        : "";

    try {
      return Files.readAllLines(
          Path.of(
              ROUTE
                  + fileName
                  + path
                  + EXTENSION));

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Returns a String representation of the input data.
   *
   * @param isTest   Whether it's test data
   * @param fileName The name of the file to parse
   * @return A String representation of the input data
   */
  public static String getInputAsString(boolean isTest, String fileName) {
    final String path = isTest
        ? TEST
        : "";

    try {
      return Files.readString(
          Path.of(
              ROUTE
                  + fileName
                  + path
                  + EXTENSION));

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

}
