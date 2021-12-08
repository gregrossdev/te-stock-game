package com.techelevator.model.game;

import java.sql.Timestamp;
// import java.time.LocalDateTime;

public class Game {

    private Long gameId;
    private Long gameOrganizer;
    private Long gameWinner;
    private Timestamp startDatetime;
    private Timestamp endDatetime;
    private String gameStatus;

    public Game() {

    }

    public Game(Long gameId, Long gameOrganizer, Long gameWinner, Timestamp startDatetime, Timestamp endDatetime, String gameStatus) {
        this.gameId = gameId;
        this.gameOrganizer = gameOrganizer;
        this.gameWinner = gameWinner;
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
        this.gameStatus = gameStatus;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getGameOrganizer() {
        return gameOrganizer;
    }

    public void setGameOrganizer(Long gameOrganizer) {
        this.gameOrganizer = gameOrganizer;
    }

    public Long getGameWinner() {
        return gameWinner;
    }

    public void setGameWinner(Long gameWinner) {
        this.gameWinner = gameWinner;
    }

    public Timestamp getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(Timestamp startDatetime) {
        this.startDatetime = startDatetime;
    }

    public Timestamp getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(Timestamp endDatetime) {
        this.endDatetime = endDatetime;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }
}
