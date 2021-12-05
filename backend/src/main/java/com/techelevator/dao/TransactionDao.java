package com.techelevator.dao;

import com.techelevator.model.transaction.Transaction;

import java.util.List;

public interface TransactionDao {

    List<Transaction> getTransactions();

    Transaction getTransactionByTransactionId(Long transactionId);

    List<Transaction> getTransactionsByPortfolioId(Long portfolioId);

    List<Transaction> getTransactionsByStockSymbol(String stockSymbol);

    boolean create(Transaction transactionToCreate);

    boolean update(Transaction transactionToUpdate);

    boolean delete(Long transactionIdToDelete);
}
