package com.alberto.advent.day04;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Card {

  private List<List<String>> rows;
  private List<List<String>> columns;
  private List<String> numbers;

  /**
   * Constructor for the class.
   */
  public Card() {
    this.rows = new ArrayList<>();
    this.columns = new ArrayList<>();
    this.numbers = new ArrayList<>();
  }

  public void addRow(List<String> row) {
    this.rows.add(row);
  }

  public void addNumbers(List<String> list) {
    this.numbers.addAll(list);
  }

}
