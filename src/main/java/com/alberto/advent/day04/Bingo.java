package com.alberto.advent.day04;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Bingo {

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

  public List<Board> generateBoards() {
    try{
      List<String> allRows = Files.readAllLines(Path.of("src/main/resources/files/bingo_boards.txt"));
//      System.out.println(allRows);
//      allRows.forEach(System.out::println);

      //Creo una board
      Board newBoard = new Board();
      List<Board> boards = new ArrayList<>();

      //Leo las filas
      for (String row: allRows) {
        //Tiene numeros? Es una fila. La inserto en la board
        if(!row.isEmpty()){
          //Add a new row, splitting it by empty spaces
          String[] splitStr = row.split("\\s+");
          newBoard.addRow(Arrays.asList(splitStr));
          //Añado los números
          newBoard.addNumbers(Arrays.asList(splitStr));
          //Si es la ultima linea, como despues no hay una linea vacia, se mete la board igualmente
          if(!row.equals(allRows.get(allRows.size()-1))) {
            boards.add(newBoard);
          }
        } else { //No los tiene? Empiezo otra board
          boards.add(newBoard);
          newBoard = new Board();
        }
      }

      //Add the columns

//      for (Board board: boards) {
//        for (String row : board.getRows()) {
//
//        }
//      }

      return boards;




    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

//    return null;
  }
}
