package com.alberto.advent.day04;

import com.alberto.advent.utils.DayFourUtils;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.map.LinkedMap;

public class Bingo {

  /**
   * Starts the game.
   */
  public void start(boolean isTest) {
    long firstWinner = firstCardWins(isTest);
    long lastWinner = lastCardWins(isTest);
    System.out.println("Day four - The first winning number is: " + firstWinner);
    System.out.println("Day four - The last winning number is: " + lastWinner);
  }

  /**
   * First card to be completed wins.
   *
   * @param isTest Whether to use the test data or the real data
   * @return The winning card
   */
  public long firstCardWins(boolean isTest) {
    List<Card> cards = DayFourUtils.generateBoards(isTest);
    List<String> numbers = DayFourUtils.getBingoNumbers(isTest);

    //Start the draw
    assert numbers != null;
    assert cards != null;
    for (String number : numbers) {
      // Iterate through every card
      for (Card card : cards) {
        //Check if you've won!
        if (didIWon(card, number)) {
          return calculateWinningNumber(card, number);
        }
      }
    }
    return 0;
  }

  /**
   * The last card to be completed wins.
   *
   * @param isTest Whether to use the test data or the real data
   * @return The last board to win
   */
  public long lastCardWins(boolean isTest) {
    List<Card> cards = DayFourUtils.generateBoards(isTest);
    List<String> numbers = DayFourUtils.getBingoNumbers(isTest);
    LinkedMap<String, Card> winnerCards = new LinkedMap<>();

    //Start the draw
    assert numbers != null;
    assert cards != null;
    for (String number : numbers) {
      // Iterate through every card
      for (Card card : cards) {
        //Skip the card if it has an empty row or column, meaning it has already won,
        //so the game won't remove any more values thus tampering with the final number calculation
        if (areRowsEmpty(card.getRows()) || areRowsEmpty(card.getColumns())) {
          continue;
        }
        if (didIWon(card, number)) {
          winnerCards.put(number, card);
        }
      }
    }
    return calculateWinningNumber(winnerCards.get(winnerCards.lastKey()), winnerCards.lastKey());
  }

  private long calculateWinningNumber(Card card, String number) {
    List<Long> numberList = card.getNumbers().stream().map(Long::parseLong)
        .collect(Collectors.toList());
    long numberSum = numberList.stream().mapToLong(Long::longValue).sum();

    String trimmedNumber = number.replaceAll("^\"|\"$", "");
    return numberSum * Long.parseLong(trimmedNumber);
  }


  private boolean didIWon(Card card, String number) {
    // If the number exists in a row or a column, remove it from there and also from the list
    // of numbers
    for (int i = 0; i <= 4; i++) {
      card.getRows().get(i).remove(number);
      card.getColumns().get(i).remove(number);
      card.getNumbers().remove(number);
      // Check if there's an empty row or column after this. If there is, we won!
      if (areRowsEmpty(card.getRows()) || areRowsEmpty(card.getColumns())) {
        return true;
      }
    }
    return false;
  }

  private boolean areRowsEmpty(List<List<String>> rows) {
    for (List<String> row : rows) {
      if (row.isEmpty()) {
        return true;
      }
    }
    return false;
  }
}
