package com.sport.betting.domain.dto;

public class BetStatisticsByPeriod {
    private int number;
    private long startDate;
    private long endDate;
    private float profit;

    public BetStatisticsByPeriod(int number, long startDate, long endDate, float profit) {
        this.number = number;
        this.startDate = startDate;
        this.endDate = endDate;
        this.profit = profit;
    }

    public int getNumber() {
        return number;
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
}
