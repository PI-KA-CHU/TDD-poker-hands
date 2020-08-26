package com.oocl;

public class Result {

    private Player winner;
    private String wintype;
    private String winResult;

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public String getWintype() {
        return wintype;
    }

    public void setWintype(String wintype) {
        this.wintype = wintype;
    }

    public String getWinResult() {
        return winResult;
    }

    public void setWinResult(String winResult) {
        this.winResult = winResult;
    }

    public String printResult(){

        return String.format("%s wins. - with %s: %s", winner.getName(), wintype, winResult);
    }
}
