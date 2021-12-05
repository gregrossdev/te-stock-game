package com.techelevator.dao;

import com.techelevator.model.stock.Stock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcStockDao implements StockDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcStockDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // TODO Fill in all methods below.

    @Override
    public List<Stock> getStocks() {
        return null;
    }

    @Override
    public Stock getStockByStockSymbol(String stockSymbol) {
        return null;
    }

    @Override
    public boolean create(Stock stockToCreate) {
        return false;
    }

    @Override
    public boolean update(Stock stockToUpdate) {
        return false;
    }

    @Override
    public boolean delete(String stockSymbolToDelete) {
        return false;
    }

    private Stock mapRowToStock(SqlRowSet results) {
        return null;
    }
}
