package com.alberto.advent.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DayEightUtils {

  /**
   * Process the data and return the output signals.
   *
   * @param isTest whether to use test data
   */
  public static List<String> getSignalOutput(boolean isTest) {
    List<String> outputValues = new ArrayList<>();
    List<String> signals = InputParser.getInputAsStringList(isTest, "digits");
    assert signals != null;
    for (String line : signals) {
      String[] outputArray = line.split("\\|");
      outputValues.add(outputArray[1].trim());
    }

    return outputValues;
  }


  /**
   * Return the number of unique output signals in the data.
   *
   * @return the number of unique signals
   */
  public static int processOutput(boolean isTest) {
    int uniqueOutputSignals = 0;
    List<String> separatedOutput = new ArrayList<>();
    List<String> outputValues = getSignalOutput(isTest);

    for (String line : outputValues) {
      String[] outputArray = line.split(" ");
      separatedOutput.addAll(Arrays.asList(outputArray));
    }

    for (String value : separatedOutput) {
      if ((value.length() == 2)
          || (value.length() == 3)
          || (value.length() == 4)
          || (value.length() == 7)) {
        uniqueOutputSignals++;
      }
    }
    return uniqueOutputSignals;
  }

}
