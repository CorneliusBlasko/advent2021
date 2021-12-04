package com.alberto.advent.day04;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Bingo {

  /**
   * Starts the game.
   */
  public void start() {
    long number = draw();
    System.out.println("The winning number is: " + number);
  }

  /**
   * Starts drawing until someone wins.
   *
   * @return The winning number
   */
  public long draw() {
    List<Board> boards = generateBoards();
    List<String> numbers = getBingoNumbers();

    //Vamos sacando números
    for (String number : numbers) {
      //Pasamos por cada tarjeta
      for (Board board : boards) {
        //Si el número está en una fila se quita de ahí y de la lista de números
        for (int i = 0; i <= 4; i++) {
          //board.getRows().get(i).remove(number);
          board.getRows().get(i).set(i, "X");
          board.getNumbers().remove(number);
          //Se comprueba que no haya ninguna fila vacía. Si la hay, hemos ganado
          if (amIEmpty(board.getRows()) || amIEmpty(board.getColumns())) {
            List<Long> numberList = board.getNumbers().stream().map(Long::parseLong)
                .collect(Collectors.toList());
            long numberSum = numberList.stream().mapToLong(Long::longValue).sum();
            return numberSum * Long.parseLong(number);
          }
        }
      }
    }
    return 0;
  }

  /**
   * Stores all the bingo ball numbers in a list.
   *
   * @return The list containing all the bingo numbers
   */
  public List<String> getBingoNumbers() {
    try {
      String listOfNumbers = Files.readString(Path.of("src/main/resources/files"
          + "/bingo_numbers.txt"));
      return Arrays.asList(listOfNumbers.split(","));
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Generates the bingo boards by reading the bingo_boards text file and parsing the numbers.
   *
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
          List<String> stringRow = createMutableList(Arrays.asList(splitStr));
          newBoard.addRow(stringRow);
          //Add the numbers
          newBoard.addNumbers(stringRow);
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

  /**
   * Turns an immutable list into a mutable one.
   *
   * @param oldList The immutable list
   * @return The mutable list
   */
  private List<String> createMutableList(List<String> oldList) {

    return new ArrayList<>(oldList);
  }

  private boolean amIEmpty(List<List<String>> rows) {
    for (List<String> row : rows) {
      if (row.stream().allMatch(n -> n.equals("X"))) {
        return true;
      }
    }
    return false;
  }

}
