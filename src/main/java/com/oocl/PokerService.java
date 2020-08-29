package com.oocl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PokerService {

    public static Map<Character, Integer> pokerMap = new HashMap<>();
    static Map<Character, String> mapToFullName = new HashMap<>();
    static Map<String, Integer> mapToTypeLevel = new HashMap<>();
    private static char[] pokerArr = {'2','3','4','5','6','7','8','9','T','J','Q','K','A'};
    private static String[] fullNames = {"2","3","4","5","6","7","8","9","Ten","Jack","Queen","King","Ace"};
    private static String[] cardTypes = {"High Card", "Pair", "Two Pairs", "Three of a Kind", "Straight", "Flush", "Full House", "Four of a Kind", "Straight Flush"};

    static {
        for (int i = 0; i < pokerArr.length; i++) {
            pokerMap.put(pokerArr[i], i);
            mapToFullName.put(pokerArr[i], fullNames[i]);
        }
        for (int i = 0; i < cardTypes.length; i++) {
            mapToTypeLevel.put(cardTypes[i], i);
        }
    }

    public Result compare(Player player1, Player player2) {
        String cardType1 = getCardType(player1);
        String cardType2 = getCardType(player2);
        int level1 = mapToTypeLevel.get(cardType1);
        int level2 = mapToTypeLevel.get(cardType2);
        if (level1 > level2) {
            String result = getPokersString(player1);
            return new Result(player1, cardType1, result);
        } else if (level1 < level2) {
            String result = getPokersString(player2);
            return new Result(player2, cardType2, result);
        } else {
            switch (cardType1){
                case PokersType.HIGH_CARD:
                    return compareInHighCard(player1, player2);
                case PokersType.STRAIGHT_FLUSH:
                    return compareInStraightFlush(player1, player2);
            }

            return null;
        }
    }

    private String getPokersString(Player player2) {
        return player2.getPokers().stream().collect(Collectors.joining(""));
    }

    private Result compareInHighCard(Player player1, Player player2) {
        List<String> pokers1 = player1.getPokers();
        List<String> pokers2 = player2.getPokers();

        for (int pokerIndex = pokers1.size() - 1; pokerIndex >= 0; pokerIndex--) {
            char maxPoker1 = getPokerByIndex(pokers1, pokerIndex);
            char maxPoker2 = getPokerByIndex(pokers2, pokerIndex);

            if (!isTwoPokerEquals(maxPoker1, maxPoker2)){
                if (isPoker1BiggerThanPoker2(maxPoker1, maxPoker2)) {
                    return new Result(player1, PokersType.HIGH_CARD, mapToFullName.get(maxPoker1));
                } else {
                    return new Result(player2, PokersType.HIGH_CARD, mapToFullName.get(maxPoker2));
                }
            }
        }

        return new Result(true);
    }

    private Result compareInStraightFlush(Player player1, Player player2){
        char lastPokerNumber1 = getLastPokerNumber(player1);
        char lastPokerNumber2 = getLastPokerNumber(player2);

        if (isPoker1BiggerThanPoker2(lastPokerNumber1, lastPokerNumber2)) {
            return new Result(player1, PokersType.STRAIGHT_FLUSH, getPokersString(player1));
        }else if (isPoker1BiggerThanPoker2(lastPokerNumber2, lastPokerNumber1)){
            return new Result(player2, PokersType.STRAIGHT_FLUSH, getPokersString(player2));
        }

        return new Result(true);
    }

    private char getLastPokerNumber(Player player2) {
        return player2.getPokers().get(player2.getPokers().size() - 1).charAt(0);
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

    private String getCardType(Player player) {
        List<String> pokers = player.getPokers();

        char flush = pokers.get(0).charAt(1);
        for (int i = 0; i < pokers.size(); i++) {
            if (flush != pokers.get(i).charAt(1)) {
                return PokersType.HIGH_CARD;
            }
        }

        int straight = pokerMap.get(pokers.get(0).charAt(0));
        for (int i = 1; i < pokers.size(); i++) {
            if (straight != pokerMap.get(pokers.get(i).charAt(0)) - 1) {
                return PokersType.HIGH_CARD;
            }
            straight = pokerMap.get(pokers.get(i).charAt(0));
        }

        return PokersType.STRAIGHT_FLUSH;
    }
}
