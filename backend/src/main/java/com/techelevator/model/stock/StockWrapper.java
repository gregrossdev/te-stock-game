package com.techelevator.model.stock;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class StockWrapper {

    private String stockSymbol;
    private BigDecimal sharePrice;
    private Timestamp quoteTimestamp;
    private String stockName;

    public StockWrapper() {

    }

    public StockWrapper(String stockSymbol, BigDecimal sharePrice, Timestamp quoteTimestamp) {
        this.stockSymbol = stockSymbol;
        this.sharePrice = sharePrice;
        this.quoteTimestamp = quoteTimestamp;
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

    public Timestamp getQuoteTimestamp() {
        return quoteTimestamp;
    }

    public void setQuoteTimestamp(Timestamp quoteTimestamp) {
        this.quoteTimestamp = quoteTimestamp;
    }
}
