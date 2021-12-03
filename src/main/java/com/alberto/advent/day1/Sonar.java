package com.alberto.advent.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Sonar {

  /**
   * Gets the final sweep value.
   *
   * @return The final sweep value
   * @throws IOException An exception parsing the list
   */
  public long getMeasurements() throws IOException {
    long sweep = this.sweep(this.retrieveMeasurements());
    System.out.println("Day one - Sweep: " + sweep);
    return sweep;
  }

  /**
   * Gets the final sweep value with sliding values.
   *
   * @return The final sweep value with aim
   * @throws IOException An exception parsing the list
   */
  public long getMeasurementsWithSlidingWindow() throws IOException {
    long sweep = this.sweep(this.createWindows(this.retrieveMeasurements()));
    System.out.println("Day one - Sweep with sliding window: " + sweep);
    return sweep;
  }

  /**
   * Counts the number of times a depth measurement increases from the previous measurement.
   *
   * @param measurements The list of measurements retrieved from measurements.txt file
   * @return The number of measurements that are larger than the previous measurement
   */
  public Long sweep(List<Long> measurements) {
    long larger = 0L;

    for (int i = 1; i < measurements.size(); i++) {
      Long previous = measurements.get(i - 1);
      Long next = measurements.get(i);
      boolean isLarger = next > previous;

      if (isLarger) {
        larger++;
      }
    }
    return larger;
  }

  /**
   * Counts the number of times the sum of measurements in this sliding window increases from the
   * previous sum.
   *
   * @param measurements The list of measurements retrieved from measurements.txt file
   * @return The number of sums are larger than the previous sum
   */
  public List<Long> createWindows(List<Long> measurements) {
    List<Long> windows = new ArrayList<>();

    for (int i = 0; i < measurements.size(); i++) {
      if (i < (measurements.size() - 2)) {
        windows.add(measurements.get(i) + measurements.get(i + 1) + measurements.get(i + 2));
      }
    }
    return windows;
  }

  /**
   * Parses the measurements.txt file to return the individual measurements.
   *
   * @return A list with all the measurements
   * @throws IOException An Exception while parsing the file
   */
  public List<Long> retrieveMeasurements() throws IOException {
    List<String> content = Files.readAllLines(Path.of("src/main/resources/measurements.txt"));
    return content.stream().map(Long::valueOf).collect(Collectors.toList());
  }
}
