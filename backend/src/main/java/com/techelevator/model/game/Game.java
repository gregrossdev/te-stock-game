package com.techelevator.model.game;

import java.sql.Timestamp;
// import java.time.LocalDateTime;

public class Game {

    private Long gameId;
    private Long gameOrganizer;
    private Long gameWinner;
    private Timestamp startTimestamp;
    private Timestamp endTimestamp;
    private String gameStatus;

    public Game() {

    }

    public Game(Long gameId, Long gameOrganizer, Long gameWinner, Timestamp startTimestamp, Timestamp endTimestamp, String gameStatus) {
        this.gameId = gameId;
        this.gameOrganizer = gameOrganizer;
        this.gameWinner = gameWinner;
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
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

    public Timestamp getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Timestamp startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public Timestamp getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(Timestamp endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }
}
