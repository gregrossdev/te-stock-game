package com.techelevator.model.transaction;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Transaction {

    private Long transactionId;
    private Long portfolioId;
    private String stockSymbol;
    private String transactionType;
    private BigDecimal transactionAmount;
    private BigDecimal transactionShares;
    private BigDecimal sharePrice;
    private Timestamp transactionTimestamp;
    private String transactionStatus;
    private BigDecimal portfolioCashAfterTransaction;

    public Transaction() {

    }

    public Transaction(Long transactionId, Long portfolioId, String stockSymbol, String transactionType,
                       BigDecimal transactionAmount, BigDecimal transactionShares, BigDecimal sharePrice,
                       Timestamp transactionTimestamp, String transactionStatus, BigDecimal portfolioCashAfterTransaction) {
        this.transactionId = transactionId;
        this.portfolioId = portfolioId;
        this.stockSymbol = stockSymbol;
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.transactionShares = transactionShares;
        this.sharePrice = sharePrice;
        this.transactionTimestamp = transactionTimestamp;
        this.transactionStatus = transactionStatus;
        this.portfolioCashAfterTransaction = portfolioCashAfterTransaction;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
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

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public BigDecimal getTransactionShares() {
        return transactionShares;
    }

    public void setTransactionShares(BigDecimal transactionShares) {
        this.transactionShares = transactionShares;
    }

    public BigDecimal getSharePrice() {
        return sharePrice;
    }

    public void setSharePrice(BigDecimal sharePrice) {
        this.sharePrice = sharePrice;
    }

    public Timestamp getTransactionTimestamp() {
        return transactionTimestamp;
    }

    public void setTransactionTimestamp(Timestamp transactionTimestamp) {
        this.transactionTimestamp = transactionTimestamp;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public BigDecimal getPortfolioCashAfterTransaction() {
        return portfolioCashAfterTransaction;
    }

    public void setPortfolioCashAfterTransaction(BigDecimal portfolioCashAfterTransaction) {
        this.portfolioCashAfterTransaction = portfolioCashAfterTransaction;
    }

}
