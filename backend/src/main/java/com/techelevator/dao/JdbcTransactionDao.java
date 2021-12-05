package com.techelevator.dao;

import com.techelevator.model.transaction.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcTransactionDao implements TransactionDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTransactionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // TODO Fill in all methods below.

    @Override
    public List<Transaction> getTransactions() {
        return null;
    }

    @Override
    public Transaction getTransactionByTransactionId(Long transactionId) {
        return null;
    }

    @Override
    public List<Transaction> getTransactionsByPortfolioId(Long portfolioId) {
        return null;
    }

    @Override
    public List<Transaction> getTransactionsByStockSymbol(String stockSymbol) {
        return null;
    }

    @Override
    public boolean create(Transaction transactionToCreate) {
        return false;
    }

    @Override
    public boolean update(Transaction transactionToUpdate) {
        return false;
    }

    @Override
    public boolean delete(Long transactionIdToDelete) {
        return false;
    }

    private Transaction mapRowToTransaction(SqlRowSet results) {
        return null;
    }
}
