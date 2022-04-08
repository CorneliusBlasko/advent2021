package com.alberto.advent.utils;

import com.alberto.advent.day04.Card;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DayFourUtils extends InputParser {

  public static final String FILENAME_NUMBERS = "bingo_numbers";
  public static final String FILENAME_BOARDS = "bingo_boards";

  /**
   * Stores all the bingo ball numbers in a list.
   *
   * @return The list containing all the bingo numbers
   */
  public static List<String> getBingoNumbers(boolean isTest) {
    String numbers = getInputAsString(isTest, FILENAME_NUMBERS);
    assert numbers != null;
    return Arrays.asList(numbers.split(","));
  }

  /**
   * Generates the bingo boards by reading the boards text file and parsing the numbers.
   *
   * @return A list of Boards
   */
  public static List<Card> generateBoards(boolean isTest) {

    List<String> allRows = getInputAsStringList(isTest, FILENAME_BOARDS);
    // Adding an empty line at the end to allow the creation of the last board since the criteria
    // for adding a new board is that the next line after the last is empty
    assert allRows != null;
    allRows.add("");

    //Boards creation
    Card newCard = new Card();
    List<Card> cards = new ArrayList<>();

    for (String row : allRows) {
      String trimmedString = row.trim();
      //Does the row have numbers? Then it's a valid row, and it must be inserted
      if (!trimmedString.isEmpty()) {
        //Add a new row, splitting it by one or more empty spaces
        String[] splitStr = trimmedString.split("\\s+");
        //Since Arrays.asList returns a list in which we cannot remove any element, and it will
        //be necessary to do it, we create a new list which has the remove() method
        List<String> stringRow = new ArrayList<>(Arrays.asList(splitStr));
        newCard.addRow(stringRow);
        newCard.addNumbers(stringRow);
      } else {
        cards.add(newCard);
        newCard = new Card();
      }
    }

    //Add the columns
    for (Card card : cards) {
      List<List<String>> columns = new ArrayList<>();
      List<String> column = new ArrayList<>();
      //All the rows in the board
      for (int i = 0; i <= 4; i++) {
        for (List<String> row : card.getRows()) {
          //For each row, insert the "i" index in the row
          column.add(row.get(i));
        }
        columns.add(column);
        column = new ArrayList<>();
      }
      card.setColumns(columns);
    }

    return cards;
  }

}
