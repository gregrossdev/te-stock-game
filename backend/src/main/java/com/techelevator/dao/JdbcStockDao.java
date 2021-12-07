package com.techelevator.dao;

import com.techelevator.model.User;
import com.techelevator.model.stock.Stock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
        List<Stock> stocks = new ArrayList<>();
        String sql = "select stock_symbol, share_price from stocks;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while(results.next()) {
            Stock stock = mapRowToStock(results);
            stocks.add(stock);
        }

        return stocks;
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
        Stock stock = new Stock();
        stock.setStockSymbol(results.getString("stock_symbol"));
        stock.setSharePrice(results.getBigDecimal("share_price"));
        return stock;
    }
}
