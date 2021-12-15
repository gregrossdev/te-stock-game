package com.techelevator.model.stock;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class StockWrapper {

    private String stockSymbol;
    private String stockName;
    private BigDecimal sharePrice;
    private Timestamp quoteTimestamp;

    public StockWrapper() {

    }

    public StockWrapper(String stockSymbol, String stockName, BigDecimal sharePrice, Timestamp quoteTimestamp) {
        this.stockSymbol = stockSymbol;
        this.stockName = stockName;
        this.sharePrice = sharePrice;
        this.quoteTimestamp = quoteTimestamp;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
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
