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

    try {
      return Files.readAllLines(
          Path.of(createRoute(ROUTE, fileName, getPath(isTest), EXTENSION))
      );

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

    try {
      return Files.readString(
          Path.of(createRoute(ROUTE, fileName, getPath(isTest), EXTENSION))
      );

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Creates the path depending on if it's the test data.
   *
   * @param isTest Whether to use test data
   * @return The path
   */
  public static String getPath(boolean isTest) {
    return isTest
        ? TEST
        : "";
  }

  /**
   * Creates the route for the file to parse.
   *
   * @param route     The route top the input folder
   * @param fileName  The file name
   * @param path      The path
   * @param extension The extension
   * @return The full route
   */
  public static String createRoute(String route, String fileName, String path, String extension) {
    return route
        + fileName
        + path
        + extension;
  }

}
