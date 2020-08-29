package com.oocl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokerService {

    public static Map<Character, Integer> pokerMap = new HashMap<>();
    static Map<Character, String> mapToFullName = new HashMap<>();
    private static char[] pokerArr = {'2','3','4','5','6','7','8','9','T','J','Q','K','A'};
    private static String[] fullNames = {"2","3","4","5","6","7","8","9","Ten","Jack","Queen","King","Ace"};

    static {
        for (int i = 0; i < pokerArr.length; i++) {
            pokerMap.put(pokerArr[i], i);
            mapToFullName.put(pokerArr[i], fullNames[i]);
        }
    }

    public Result compare(Player player1, Player player2) {
        List<String> pokers1 = player1.getPokers();
        List<String> pokers2 = player2.getPokers();

        for (int pokerIndex = pokers1.size() - 1; pokerIndex >= 0; pokerIndex--) {
            char maxPoker1 = getPokerByIndex(pokers1, pokerIndex);
            char maxPoker2 = getPokerByIndex(pokers2, pokerIndex);

            if (!isTwoPokerEquals(maxPoker1, maxPoker2)){
                if (isPoker1BiggerThanPoker2(maxPoker1, maxPoker2)) {
                    return new Result(player1, "high card", mapToFullName.get(maxPoker1));
                } else {
                    return new Result(player2, "high card", mapToFullName.get(maxPoker2));
                }
            }
        }

        return new Result(true);
    }

    private boolean isPoker1BiggerThanPoker2(char poker1, char poker2) {
        return pokerMap.get(poker1) > pokerMap.get(poker2);
    }

    private boolean isTwoPokerEquals(char poker1, char poker2) {
        return pokerMap.get(poker1).equals(pokerMap.get(poker2));
    }

    private char getPokerByIndex(List<String> pokers, int pokersIndex) {
        return pokers.get(pokersIndex).charAt(0);
    }
}
