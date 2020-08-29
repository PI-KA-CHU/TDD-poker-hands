package com.oocl;

import org.junit.Assert;
import org.junit.Test;

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

        Assert.assertEquals("jim wins. - with high card: Ace",result.printResult());
    }

    @Test
    public void should_return_player1_when_compare_given_player1_5D8C9SACJS_player2_2C5C7D8SQH() {
        List<String> poker1 = Arrays.asList("5D", "8C", "9S", "AC", "JS");
        List<String> poker2 = Arrays.asList("2C", "5C", "7D", "8S", "QH");
        Player player1 = new Player("jim",poker1);
        Player player2 = new Player("ben",poker2);

        PokerService pokerService = new PokerService();
        Result result = pokerService.compare(player1,player2);

        Assert.assertEquals("jim wins. - with high card: Ace",result.printResult());
    }

    @Test
    public void should_return_player1_when_compare_given_player1_5D8C9SACJS_player2_2C5CAH8SQH() {
        List<String> poker1 = Arrays.asList("5D", "8C", "9S", "AC", "JS");
        List<String> poker2 = Arrays.asList("2C", "5C", "AH", "8S", "QH");
        Player player1 = new Player("jim",poker1);
        Player player2 = new Player("ben",poker2);

        PokerService pokerService = new PokerService();
        Result result = pokerService.compare(player1,player2);

        Assert.assertEquals("ben wins. - with high card: Queen",result.printResult());
    }

    @Test
    public void should_return_tie_when_compare_given_player1_5D8C9SACJS_player2_5C8D9CAHJH() {
        List<String> poker1 = Arrays.asList("5D", "8C", "9S", "AC", "JS");
        List<String> poker2 = Arrays.asList("5C", "8D", "9C", "AH", "JH");
        Player player1 = new Player("jim",poker1);
        Player player2 = new Player("ben",poker2);

        PokerService pokerService = new PokerService();
        Result result = pokerService.compare(player1,player2);

        Assert.assertEquals("Tie.",result.printResult());
    }
}
