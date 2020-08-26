package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class PokerTest {

    @Test
    public void should_return_player1_when_compare_given_player1_5D8C9SJSAC_player2_2C5C7D8SQH() {
        List<String> poker1 = Arrays.asList("5D", "8C", "9S", "JS", "AC");
        List<String> poker2 = Arrays.asList("2C", "5C", "7D", "8S", "QH");
        Player player1 = new Player("jim",poker1);
        Player player2 = new Player("ben",poker2);

        PokerService pokerService = new PokerService();
        Result result = pokerService.compare(player1,player2);

        Assertions.assertEquals("jim wins. - with high card: Ace",result.printResult());
    }
}
