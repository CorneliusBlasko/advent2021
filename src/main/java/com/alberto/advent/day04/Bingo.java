package com.alberto.advent.day04;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bingo {

  /**
   * Stores all the bingo ball numbers in a list.
   * @return The list containing all the bingo numbers
   */
  public List<String> getBingoNumbers() {
    try {
      String listOfNumbers = Files.readString(Path.of("src/main/resources/files" +
          "/bingo_numbers.txt"));
      String[] values = listOfNumbers.split(",");
      return Arrays.asList(values);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

  /**
   * Generates the bingo boards by reading the bingo_boards text file and parsing the numbers.
   * @return A list of Boards
   */
  public List<Board> generateBoards() {
    try {
      List<String> allRows =
      Files.readAllLines(Path.of("src/main/resources/files/bingo_boards.txt"));
      // Adding an empty line at the end to allow the creation of the last board since the criteria
      // for adding a new board is that the next line after the last is empty
      allRows.add("");

      //Boards creation
      Board newBoard = new Board();
      List<Board> boards = new ArrayList<>();

      for (String row : allRows) {
        //Does the row have numbers? Then it's a valid row, and it must be inserted
        if (!row.isEmpty()) {
          //Add a new row, splitting it by one or more empty spaces
          String[] splitStr = row.split("\\s+");
          newBoard.addRow(Arrays.asList(splitStr));
          //Add the numbers
          newBoard.addNumbers(Arrays.asList(splitStr));
          //If this is the last line, since there's no empty space after it, we must put it in the
          // board list too
        } else { //If the new row does not have any number it means we've reached the last row of
          // the board
          boards.add(newBoard);
          newBoard = new Board();
        }
      }

      //Add the columns
      for (Board board : boards) {
        List<List<String>> columns = new ArrayList<>();
        List<String> column = new ArrayList<>();
        //All the rows in the board
        for (int i = 0; i <= 4; i++) {
          for (List<String> row : board.getRows()) {
            //For each row, insert the "i" index in the row
            column.add(row.get(i));
          }
          columns.add(column);
          column = new ArrayList<>();
        }
        board.setColumns(columns);
      }

      return boards;

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

}
