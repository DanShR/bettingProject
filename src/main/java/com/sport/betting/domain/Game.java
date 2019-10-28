package com.sport.betting.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "game")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Game {

    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "league_id")
    private  League league;

    @ManyToOne
    @JoinColumn(name = "team_home_id")
    private Team teamHome;

    @ManyToOne
    @JoinColumn(name = "team_away_id")
    private Team teamAway;

    private int status;

    @Column(name = "scores_home")
    private int scoresHome;

    @Column(name = "scores_away")
    private int scoresAway;

    //JsonFormat(pattern="dd-MM-yyy HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @JsonIgnore
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<EventOdd> eventOdds;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Team getTeamHome() {
        return teamHome;
    }

    public void setTeamHome(Team teamHome) {
        this.teamHome = teamHome;
    }

    public Team getTeamAway() {
        return teamAway;
    }

    public void setTeamAway(Team teamAway) {
        this.teamAway = teamAway;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getScoresHome() {
        return scoresHome;
    }

    public void setScoresHome(int scoresHome) {
        this.scoresHome = scoresHome;
    }

    public int getScoresAway() {
        return scoresAway;
    }

    public void setScoresAway(int scoresAway) {
        this.scoresAway = scoresAway;
    }

    public long getDate() {
        return date.getTime();
    }

    public void setDate(int date) {
        this.date = new Date((long)date*1000);
    }

    public List<EventOdd> getEventOdds() {
        return eventOdds;
    }

    public void setEventOdds(List<EventOdd> eventOdds) {
        this.eventOdds = eventOdds;
    }
}
