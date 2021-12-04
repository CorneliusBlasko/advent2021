package com.alberto.advent.day04;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {

  private List<List<String>> rows;
  private List<String> columns;
  private List<String> numbers;

  public Board() {
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
