package com.techelevator.model.portfolio;

import java.math.BigDecimal;

public class Portfolio {

    private Long portfolioId;
    private Long userId;
    private Long gameId;
    private BigDecimal portfolioBalance;
    private BigDecimal portfolioValue;
    private String portfolioStatus;

    public Portfolio() {

    }

    public Portfolio(Long portfolioId, Long userId, Long gameId, BigDecimal portfolioBalance, BigDecimal portfolioValue, String portfolioStatus) {
        this.portfolioId = portfolioId;
        this.userId = userId;
        this.gameId = gameId;
        this.portfolioBalance = portfolioBalance;
        this.portfolioValue = portfolioValue;
        this.portfolioStatus = portfolioStatus;
    }

    public Long getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(Long portfolioId) {
        this.portfolioId = portfolioId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public BigDecimal getPortfolioBalance() {
        return portfolioBalance;
    }

    public void setPortfolioBalance(BigDecimal portfolioBalance) {
        this.portfolioBalance = portfolioBalance;
    }

    public BigDecimal getPortfolioValue() {
        return portfolioValue;
    }

    public void setPortfolioValue(BigDecimal portfolioValue) {
        this.portfolioValue = portfolioValue;
    }

    public String getPortfolioStatus() {
        return portfolioStatus;
    }

    public void setPortfolioStatus(String portfolioStatus) {
        this.portfolioStatus = portfolioStatus;
    }
}


