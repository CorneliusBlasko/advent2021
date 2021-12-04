package com.alberto.advent.day03;

import com.alberto.advent.utils.DayThreeUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Diagnostic {

  private static final String GAMMA = "gamma";
  private static final String EPSILON = "epsilon";


  /**
   * Calculates the power consumption of the submarine by multiplying gamma and epsilon.
   *
   * @return The power consumption
   */
  public long calculatePowerConsumption() {
    long powerConsumption = (long) this.calculate(GAMMA) * this.calculate(EPSILON);
    System.out.println("Day three - The power consumption is: " + powerConsumption);
    return powerConsumption;
  }

  /**
   * The gamma rate can be determined by finding the most common bit in the corresponding position
   * of all numbers in the diagnostic report. The epsilon rate is calculated in a similar way;
   * rather than use the most common bit, the least common bit from each position is used.
   *
   * @param rate The gamma or epsilon rate
   * @return The decimal representation of the binary created
   */
  public int calculate(String rate) {
    List<String> bits = new ArrayList<>();

    for (int i = 0; i <= 11; i++) {
      if (rate.equals(GAMMA)) {
        bits.add(this.getMostFrequentBitByPosition(i));
      }

      if (rate.equals(EPSILON)) {
        bits.add(this.getLeastFrequentBitByPosition(i));
      }
    }

    StringBuilder builder = new StringBuilder();
    bits.forEach(builder::append);

    return Integer.parseInt(builder.toString(), 2);
  }

  public String getMostFrequentBitByPosition(int position) {
    return this.getFinalBit(position, DayThreeUtils.MOST_FREQUENT);

  }

  public String getLeastFrequentBitByPosition(int position) {
    return this.getFinalBit(position, DayThreeUtils.LEAST_FREQUENT);

  }

  /**
   * Depending on the criteria, returns the number (1 or 0) that is repeated the most or the least
   * for the position in the list of numbers.
   *
   * @param position The position in the row, from 0 to 11
   * @param criteria The criteria by which the bit is calculated
   * @return The bit calculated depending on the criteria
   */
  public String getFinalBit(int position, String criteria) {
    long zero = 0L;
    long one = 0L;

    for (String line : Objects.requireNonNull(DayThreeUtils.getNumbers())) {
      if (Character.toString(line.charAt(position)).equals("0")) {
        zero++;
      } else {
        one++;
      }
    }

    if (criteria.equals(DayThreeUtils.MOST_FREQUENT)) {
      return DayThreeUtils.returnValue(zero, one, DayThreeUtils.MOST_FREQUENT);
    }

    if (criteria.equals(DayThreeUtils.LEAST_FREQUENT)) {
      return DayThreeUtils.returnValue(zero, one, DayThreeUtils.LEAST_FREQUENT);
    }

    return "";
  }

}
