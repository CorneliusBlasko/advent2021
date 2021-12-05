package com.alberto.advent.day05;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vent {

  private Point origin;
  private Point destination;

  @Override public String toString() {
    return "Vent{" +
        "origin=" + origin.toString() +
        ", destination=" + destination.toString() +
        '}';
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Point {
    private int x;
    private int y;

    @Override public String toString() {
      return "Point{" +
          "x=" + x +
          ", y=" + y +
          '}';
    }
  }
}
