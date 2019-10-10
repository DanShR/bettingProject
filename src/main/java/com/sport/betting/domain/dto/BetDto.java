package com.sport.betting.domain.dto;

import com.sport.betting.domain.Bookmaker;
import com.sport.betting.domain.Game;

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
        private int timeToGameStart;

    public BetDto(int id, Game game, Bookmaker bookmaker, int event, float odd, Date addTime, float ratio, int result) {
        //em.createQuery("select new com.sport.betting.domain.dto.BetDto(b.id, b.game, b.bookmaker, b.event, b.odd, max(b.addTime), b.ratio, b.result) from Bet b left join b.game g where b.odd<=2.2 and b.ratio between 1.05 and 1.4 group by g.id, b.bookmaker").getResultList()
        this.id = id;
        this.game = game;
        this.bookmaker = bookmaker;
        this.event = event;
        this.odd = odd;
        this.addTime = addTime;
        this.ratio = ratio;
        this.result = result;
        this.timeToGameStart = (int) (game.getDate().getTime() - addTime.getTime())/(60 *1000);
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

    public int getTimeToGameStart() {
        return timeToGameStart;
    }
}
