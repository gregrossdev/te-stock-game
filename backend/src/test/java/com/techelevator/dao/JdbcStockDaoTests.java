package com.techelevator.dao;

import com.techelevator.model.stock.StockWrapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import yahoofinance.Stock;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class JdbcStockDaoTests extends FinalCapstoneDaoTests{


    private JdbcStockDao sut;

    @Before
    public void setup() {
        DataSource dataSource = this.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcStockDao(jdbcTemplate);
    }

    @Test
    public void returns_correct_amount_of_stocks(){
        int numberOfStocksInDB = 11;
        Assert.assertEquals(numberOfStocksInDB, sut.getStocks().size());
    }

    @Test
    public void get_stock_by_given_symbol(){
        String stockTickerExpected = "SPCE";

        StockWrapper stockReturned = sut.getStockByStockSymbol(stockTickerExpected);

        Assert.assertEquals(stockTickerExpected, stockReturned.getStockSymbol());
    }


    @Test
    public void updating_price_functions(){
        System.out.println("HMMMMMM");


    }

}
