package com.techelevator.model.portfolioStock;

import java.math.BigDecimal;

public class PortfolioStock {

    private Long portfolioId;
    private String stockSymbol;
    private BigDecimal totalShares;

    public PortfolioStock() {

    }

    public PortfolioStock(Long portfolioId, String stockSymbol, BigDecimal totalShares) {
        this.portfolioId = portfolioId;
        this.stockSymbol = stockSymbol;
        this.totalShares = totalShares;
    }

    public Long getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(Long portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public BigDecimal getTotalShares() {
        return totalShares;
    }

    public void setTotalShares(BigDecimal totalShares) {
        this.totalShares = totalShares;
    }
}
