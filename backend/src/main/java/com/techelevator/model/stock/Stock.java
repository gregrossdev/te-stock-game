package com.techelevator.model.stock;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Stock {

    private String stockSymbol;
    private BigDecimal sharePrice;
    private LocalDateTime quoteDatetime;

    public Stock() {

    }

    public Stock(String stockSymbol, BigDecimal sharePrice, LocalDateTime quoteDatetime) {
        this.stockSymbol = stockSymbol;
        this.sharePrice = sharePrice;
        this.quoteDatetime = quoteDatetime;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public BigDecimal getSharePrice() {
        return sharePrice;
    }

    public void setSharePrice(BigDecimal sharePrice) {
        this.sharePrice = sharePrice;
    }

    public LocalDateTime getQuoteDatetime() {
        return quoteDatetime;
    }

    public void setQuoteDatetime(LocalDateTime quoteDatetime) {
        this.quoteDatetime = quoteDatetime;
    }
}
