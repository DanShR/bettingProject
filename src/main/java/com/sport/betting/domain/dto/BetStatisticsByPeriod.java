package com.sport.betting.domain.dto;

public class BetStatisticsByPeriod {
    private String key;
    private long startDate;
    private long endDate;
    private float profit;
    private int winQuantity;
    private int loseQuantity;

    public BetStatisticsByPeriod(String key, long startDate, long endDate, float profit, int winQuantity, int loseQuantity) {
        this.key = key;
        this.startDate = startDate;
        this.endDate = endDate;
        this.profit = profit;
        this.winQuantity = winQuantity;
        this.loseQuantity = loseQuantity;
    }

    public String getKey() {
        return key;
    }

    public long getStartDate() {
        return startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public float getProfit() {
        return profit;
    }

    public int getWinQuantity() {
        return winQuantity;
    }

    public int getLoseQuantity() {
        return loseQuantity;
    }
}
