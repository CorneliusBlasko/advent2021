package com.alberto.advent.day04;

import com.alberto.advent.utils.DayFourUtils;
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
    List<Board> boards = DayFourUtils.generateBoards();
    List<String> numbers = DayFourUtils.getBingoNumbers();

    //Vamos sacando números
    assert numbers != null;
    assert boards != null;
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


  private boolean amIEmpty(List<List<String>> rows) {
    for (List<String> row : rows) {
      if (row.stream().allMatch(n -> n.equals("X"))) {
        return true;
      }
    }
    return false;
  }

}
