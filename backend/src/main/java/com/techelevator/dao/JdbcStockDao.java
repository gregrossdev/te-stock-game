package com.techelevator.dao;

import com.techelevator.model.stock.StockWrapper;
import com.techelevator.model.stock.StockNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcStockDao implements StockDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcStockDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<StockWrapper> getStocks() {
        List<StockWrapper> stockWrappers = new ArrayList<>();
        String sql = "SELECT * FROM stocks;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            stockWrappers.add(mapRowToStock(results));
        }
        return stockWrappers;
    }


    @Override
    public StockWrapper getStockByStockSymbol(String stockSymbol) {
        String sql = "SELECT * FROM stocks WHERE stock_symbol = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, stockSymbol);
        if (results.next()) {
            return mapRowToStock(results);
        }
        throw new StockNotFoundException();
    }

    @Override
    public boolean create(StockWrapper stockWrapperToCreate) {
        String sql = "INSERT INTO stocks (stock_symbol, share_price, quote_timestamp) VALUES (?, ?, ?);";
        return jdbcTemplate.update(sql, stockWrapperToCreate.getStockSymbol(), stockWrapperToCreate.getSharePrice(),
                stockWrapperToCreate.getQuoteTimestamp()) == 1;
    }

    @Override
    public boolean update(StockWrapper stockWrapperToUpdate) {
        String sql = "UPDATE stocks SET share_price = ?, quote_timestamp = ? WHERE stock_symbol = ?;";
        return jdbcTemplate.update(sql, stockWrapperToUpdate.getSharePrice(), stockWrapperToUpdate.getQuoteTimestamp(),
                stockWrapperToUpdate.getStockSymbol()) == 1;
    }

    @Override
    public boolean delete(String stockSymbolToDelete) {
        String sql = "DELETE FROM stocks WHERE stock_symbol = ?;";
        return jdbcTemplate.update(sql, stockSymbolToDelete) == 1;
    }
    public boolean testMethodUpdatePrice(BigDecimal priceChange){
        String sql = "UPDATE stocks SET share_price = ? WHERE stock_symbol = 'SPCE';";
        return jdbcTemplate.update(sql, priceChange) == 1; //return true if works?
    }
    public boolean testMethodUpdatePriceTwo(BigDecimal price, String ticker){
        String sql = "UPDATE stocks SET share_price = ? WHERE stock_symbol = ?;";
        return jdbcTemplate.update(sql, price, ticker) == 1; //return true if works?
    }

    private StockWrapper mapRowToStock(SqlRowSet results) {
        StockWrapper stockWrapper = new StockWrapper();
        stockWrapper.setStockSymbol(results.getString("stock_symbol"));
        stockWrapper.setSharePrice(results.getBigDecimal("share_price"));
        stockWrapper.setQuoteTimestamp(results.getTimestamp("quote_timestamp"));
        return stockWrapper;
    }
}
