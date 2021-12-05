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
    System.out.println("The first winning number is: " + firstWinner);
    System.out.println("The last winning number is: " + lastWinner);
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
        if (amIaWinner(card, number)) {
          List<Long> numberList = card.getNumbers().stream().map(Long::parseLong)
              .collect(Collectors.toList());
          long numberSum = numberList.stream().mapToLong(Long::longValue).sum();
          return numberSum * Long.parseLong(number);
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
    LinkedMap<String, Card> winners = new LinkedMap<>();

    //Start the draw
    assert numbers != null;
    assert cards != null;
    for (String number : numbers) {
      // Iterate through every card
      for (Card card : cards) {
        // If the card is a winner, put it in the list
        if (amIaWinner(card, number) //The card has won
            && !winners.containsValue(card) //The card is already in the winners map
            && winners.size() < cards.size()) { //There are no cards left
          winners.put(number, card);
        }
      }
      if (winners.size() == cards.size()) {
        break;
      }
    }
    return calculateWinner(winners.get(winners.lastKey()), winners.lastKey());
  }

  private long calculateWinner(Card card, String number) {
    //Parse row and columns values to long
    long numberSum = 0L;
    for (List<String> row : card.getRows()) {
      List<Long> rowLong = row.stream().map(Long::parseLong).collect(Collectors.toList());
      for (Long value : rowLong) {
        numberSum += value;
      }
    }

    String trimmedNumber = number.replaceAll("^\"|\"$", "");
    return numberSum * Long.parseLong(trimmedNumber);
  }


  private boolean didIWon(List<List<String>> rows) {
    for (List<String> row : rows) {
      if (row.isEmpty()) {
        return true;
      }
    }
    return false;
  }

  private boolean amIaWinner(Card card, String number) {
    // If the number exists in a row or a column, remove it from there and also from the list
    // of numbers
    // Check if there's an empty row or column. If it is, we won!
    for (int i = 0; i <= 4; i++) {
      card.getRows().get(i).remove(number);
      card.getColumns().get(i).remove(number);
      card.getNumbers().remove(number);
      // Check if there's an empty row or column. If it is, we won!
      if (didIWon(card.getRows()) || didIWon(card.getColumns())) {
        return true;
      }
    }
    return false;
  }

}
