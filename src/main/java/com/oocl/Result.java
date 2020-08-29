package com.oocl;

public class Result {

    private Player winner;
    private String winType;
    private String winResult;
    private boolean isTie;

    public Result(Player winner, String winType, String winResult) {
        this.winner = winner;
        this.winType = winType;
        this.winResult = winResult;
    }

    public Result(boolean isTie) {
        this.isTie = isTie;
    }

    public String printResult(){

        return isTie ? "Tie." : String.format("%s wins. - with %s: %s", winner.getName(), winType, winResult);
    }
}
