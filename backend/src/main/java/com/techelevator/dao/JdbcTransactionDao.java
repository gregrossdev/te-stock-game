package com.techelevator.dao;

import com.techelevator.model.transaction.Transaction;
import com.techelevator.model.transaction.TransactionNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            transactions.add(mapRowToTransaction(results));
        }
        return transactions;
    }

    @Override
    public Transaction getTransactionByTransactionId(Long transactionId) {
        String sql = "SELECT * FROM transactions WHERE transaction_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transactionId);
        if (results.next()) {
            return mapRowToTransaction(results);
        }
        throw new TransactionNotFoundException();
    }

    @Override
    public List<Transaction> getTransactionsByPortfolioId(Long portfolioId) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE portfolio_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, portfolioId);
        while (results.next()) {
            transactions.add(mapRowToTransaction(results));
        }
        return transactions;
    }

    @Override
    public List<Transaction> getTransactionsByStockSymbol(String stockSymbol) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE stock_symbol = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, stockSymbol);
        while (results.next()) {
            transactions.add(mapRowToTransaction(results));
        }
        return transactions;
    }

    @Override
    public boolean create(Transaction transactionToCreate) {
        String sql = "INSERT INTO transactions (portfolio_id, stock_symbol, transaction_type, transaction_amount, " +
                "transaction_shares, share_price, transaction_datetime, transaction_status, portfolio_balance, " +
                "portfolio_value) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        return jdbcTemplate.update(sql, transactionToCreate.getPortfolioId(), transactionToCreate.getStockSymbol(),
                transactionToCreate.getTransactionType(), transactionToCreate.getTransactionAmount(),
                transactionToCreate.getTransactionShares(), transactionToCreate.getSharePrice(),
                transactionToCreate.getTransactionDatetime(), transactionToCreate.getTransactionStatus(),
                transactionToCreate.getPortfolioBalance(), transactionToCreate.getPortfolioValue()) == 1;
    }

    @Override
    public boolean update(Transaction transactionToUpdate) {
        String sql =
                "UPDATE transactions SET portfolio_id = ?, stock_symbol = ?, transaction_type = ?, transaction_amount = ?, " +
                "transaction_shares = ?, share_price = ?, transaction_datetime = ?, transaction_status = ?, portfolio_balance = ?, " +
                "portfolio_value = ? WHERE transaction_id = ?;";
        return jdbcTemplate.update(sql, transactionToUpdate.getPortfolioId(), transactionToUpdate.getStockSymbol(),
                transactionToUpdate.getTransactionType(), transactionToUpdate.getTransactionAmount(),
                transactionToUpdate.getTransactionShares(), transactionToUpdate.getSharePrice(),
                transactionToUpdate.getTransactionDatetime(), transactionToUpdate.getTransactionStatus(),
                transactionToUpdate.getPortfolioBalance(), transactionToUpdate.getPortfolioValue(),
                transactionToUpdate.getTransactionId()) == 1;
    }

    @Override
    public boolean delete(Long transactionIdToDelete) {
        String sql = "DELETE FROM transactions WHERE transaction_id = ?;";
        return jdbcTemplate.update(sql, transactionIdToDelete) == 1;
    }

    private Transaction mapRowToTransaction(SqlRowSet results) {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(results.getLong("transaction_id"));
        transaction.setPortfolioId(results.getLong("portfolio_id"));
        transaction.setStockSymbol(results.getString("stock_symbol"));
        transaction.setTransactionType(results.getString("transaction_type"));
        transaction.setTransactionAmount(results.getBigDecimal("transaction_amount"));
        transaction.setTransactionShares(results.getBigDecimal("transaction_shares"));
        transaction.setSharePrice(results.getBigDecimal("share_price"));
        transaction.setTransactionDatetime(results.getObject("transaction_datetime", LocalDateTime.class));
        transaction.setTransactionStatus(results.getString("transaction_status"));
        transaction.setPortfolioBalance(results.getBigDecimal("portfolio_balance"));
        transaction.setPortfolioValue(results.getBigDecimal("portfolio_value"));
        return transaction;
    }
}
