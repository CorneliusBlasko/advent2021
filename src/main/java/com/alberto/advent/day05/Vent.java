package com.alberto.advent.day05;

import java.util.List;
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
  private List<Point> route;

  @Override
  public String toString() {
    return "Vent{"
        + "origin=" + origin
        + ", destination=" + destination
        + ", ventSize=" + route
        + '}';
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Point {

    private int abscissa;
    private int ordinate;

    @Override
    public String toString() {
      return "Point{"
          + "x=" + abscissa
          + ", y=" + ordinate
          + '}';
    }
  }
}
