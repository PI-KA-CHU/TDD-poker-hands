package com.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokerService {

    private static Map<Character, Integer> pokerMap = new HashMap<>();
    private static char[] pokerArr = {'2','3','4','5','6','7','8','9','T','J','Q','K','A'};

    static {
        for (int i = 0; i < pokerArr.length; i++) {
            pokerMap.put(pokerArr[i], i);
        }
    }

    public Result compare(Player player1, Player player2) {
        List<String> pokers1 = player1.getPokers();
        List<String> pokers2 = player2.getPokers();

        char maxPoker1 = pokers1.get(pokers1.size() - 1).charAt(0);
        char maxPoker2 = pokers2.get(pokers2.size() - 1).charAt(0);

        Result result = new Result();
        if (pokerMap.get(maxPoker1) > pokerMap.get(maxPoker2)) {
            result.setWinner(player1);
            result.setWintype("high card");
            result.setWinResult("Ace");
        }

        return result;
    }
}
