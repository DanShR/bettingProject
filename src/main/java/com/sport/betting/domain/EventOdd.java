package com.sport.betting.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "event_odd")
public class EventOdd {

    @Id
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bookmaker_id")
    private Bookmaker bookmaker;

    @Column(name = "home_od")
    private float homeOdd;

    @Column(name = "draw_od")
    private float darwOdd;

    @Column(name = "away_od")
    private float awayOdd;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "add_time")
    private Date addTime;

    @Column(name = "time_str")
    private int timeStr;

    public EventOdd() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bookmaker getBookmaker() {
        return bookmaker;
    }

    public void setBookmaker(Bookmaker bookmaker) {
        this.bookmaker = bookmaker;
    }

    public float getHomeOdd() {
        return homeOdd;
    }

    public void setHomeOdd(float homeOdd) {
        this.homeOdd = homeOdd;
    }

    public float getDarwOdd() {
        return darwOdd;
    }

    public void setDarwOdd(float darwOdd) {
        this.darwOdd = darwOdd;
    }

    public float getAwayOdd() {
        return awayOdd;
    }

    public void setAwayOdd(float awayOdd) {
        this.awayOdd = awayOdd;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(int addTime) {
        this.addTime = new Date((long) addTime*1000);
    }

    public int getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(int timeStr) {
        this.timeStr = timeStr;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
