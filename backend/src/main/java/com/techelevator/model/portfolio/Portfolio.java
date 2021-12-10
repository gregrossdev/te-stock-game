package com.techelevator.model.portfolio;

import java.math.BigDecimal;

public class Portfolio {

    private Long portfolioId;
    private Long userId;
    private Long gameId;
    private BigDecimal portfolioCash;
    private BigDecimal portfolioStocksValue;
    private BigDecimal portfolioTotalValue;
    private String portfolioStatus;

    public Portfolio() {

    }

    public Portfolio(Long portfolioId, Long userId, Long gameId, BigDecimal portfolioCash, BigDecimal portfolioStocksValue, BigDecimal portfolioTotalValue, String portfolioStatus) {
        this.portfolioId = portfolioId;
        this.userId = userId;
        this.gameId = gameId;
        this.portfolioCash = portfolioCash;
        this.portfolioStocksValue = portfolioStocksValue;
        this.portfolioTotalValue = portfolioTotalValue;
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

    public BigDecimal getPortfolioCash() {
        return portfolioCash;
    }

    public void setPortfolioCash(BigDecimal portfolioCash) {
        this.portfolioCash = portfolioCash;
    }

    public BigDecimal getPortfolioStocksValue() {
        return portfolioStocksValue;
    }

    public void setPortfolioStocksValue(BigDecimal portfolioStocksValue) {
        this.portfolioStocksValue = portfolioStocksValue;
    }

    public BigDecimal getPortfolioTotalValue() {
        return portfolioTotalValue;
    }

    public void setPortfolioTotalValue(BigDecimal portfolioTotalValue) {
        this.portfolioTotalValue = portfolioTotalValue;
    }

    public String getPortfolioStatus() {
        return portfolioStatus;
    }

    public void setPortfolioStatus(String portfolioStatus) {
        this.portfolioStatus = portfolioStatus;
    }
}


