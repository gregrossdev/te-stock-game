package com.techelevator.dao;

import com.techelevator.model.User;
import com.techelevator.model.stock.Stock;
import com.techelevator.model.stock.StockNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcStockDao implements StockDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcStockDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Stock> getStocks() {
        List<Stock> stocks = new ArrayList<>();
        String sql = "SELECT * FROM stocks;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            stocks.add(mapRowToStock(results));
        }
        return stocks;
    }


    @Override
    public Stock getStockByStockSymbol(String stockSymbol) {
        String sql = "SELECT * FROM stocks WHERE stock_symbol = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, stockSymbol);
        if (results.next()) {
            return mapRowToStock(results);
        }
        throw new StockNotFoundException();
    }

    @Override
    public boolean create(Stock stockToCreate) {
        String sql = "INSERT INTO stocks (stock_symbol, share_price, quote_datetime) VALUES (?, ?, ?);";
        return jdbcTemplate.update(sql, stockToCreate.getStockSymbol(), stockToCreate.getSharePrice(),
                stockToCreate.getQuoteDatetime()) == 1;
    }

    @Override
    public boolean update(Stock stockToUpdate) {
        String sql = "UPDATE stocks SET share_price = ?, quote_datetime = ? WHERE stock_symbol = ?;";
        return jdbcTemplate.update(sql, stockToUpdate.getSharePrice(), stockToUpdate.getQuoteDatetime(),
                stockToUpdate.getStockSymbol()) == 1;
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

    private Stock mapRowToStock(SqlRowSet results) {
        Stock stock = new Stock();
        stock.setStockSymbol(results.getString("stock_symbol"));
        stock.setSharePrice(results.getBigDecimal("share_price"));
        stock.setQuoteDatetime(results.getTimestamp("quote_datetime"));
        return stock;
    }
}
