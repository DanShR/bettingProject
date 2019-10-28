package com.sport.betting.domain.dto;

import com.sport.betting.domain.Bookmaker;
import com.sport.betting.domain.Game;
import com.sport.betting.domain.UserBet;

import java.io.Serializable;
import java.util.Date;

public class BetDto implements Serializable {
        private int id;
        private Game game;
        private Bookmaker bookmaker;
        private int event;
        private  float odd;
        private Date addTime;
        private float ratio;
        private int result;
        private UserBet userBet;

    public BetDto(int id) {
        this.id = id;
    }

    public BetDto(int id, Game game, Bookmaker bookmaker, int event, float odd, Date addTime, float ratio, int result, UserBet userBet) {
        this.id = id;
        this.game = game;
        this.bookmaker = bookmaker;
        this.event = event;
        this.odd = odd;
        this.addTime = addTime;
        this.ratio = ratio;
        this.result = result;
        this.userBet = userBet;
    }

    public int getId() {
        return id;
    }

    public Game getGame() {
        return game;
    }

    public Bookmaker getBookmaker() {
        return bookmaker;
    }

    public int getEvent() {
        return event;
    }

    public float getOdd() {
        return odd;
    }

    public Date getAddTime() {
        return addTime;
    }

    public float getRatio() {
        return ratio;
    }

    public int getResult() {
        return result;
    }

    public UserBet getUserBet() {
        return userBet;
    }
}
