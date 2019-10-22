package com.sport.betting.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "bet")
public class Bet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookmaker_id")
    private Bookmaker bookmaker;

    @Column(name = "event")
    private int event;

    @Column(name = "odd")
    private  float odd;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "add_time")
    private Date addTime;

    @Column(name = "ratio")
    private float ratio;

    @Column(name = "result", nullable = false)
    private int result;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_odd_id")
    private EventOdd eventOdd;

    @OneToMany(mappedBy = "bet", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<UserBet> userBetList;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created")
    private Date created;

    public Bet() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Bookmaker getBookmaker() {
        return bookmaker;
    }

    public void setBookmaker(Bookmaker bookmaker) {
        this.bookmaker = bookmaker;
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public float getOdd() {
        return odd;
    }

    public void setOdd(float odd) {
        this.odd = odd;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public EventOdd getEventOdd() {
        return eventOdd;
    }

    public void setEventOdd(EventOdd eventOdd) {
        this.eventOdd = eventOdd;
    }

    public List<UserBet> getUserBetList() {
        return userBetList;
    }

    public void setUserBetList(List<UserBet> userBetList) {
        this.userBetList = userBetList;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
