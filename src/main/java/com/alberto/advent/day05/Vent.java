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

  private Coordinate origin;
  private Coordinate destination;
  private List<Coordinate> route;

  public Vent(Coordinate origin, Coordinate destination) {
    this.origin = origin;
    this.destination = destination;
  }

  @Override public String toString() {
    return "Vent{" +
        "origin=" + origin +
        ", destination=" + destination +
        ", ventSize=" + route +
        '}';
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Coordinate {
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
