package com.techelevator.dao;

import com.techelevator.model.stock.StockNotFoundException;
import com.techelevator.model.stock.StockWrapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import javax.sql.DataSource;
import java.io.IOException;
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
        int numberOfStocksInDB = 100;
        Assert.assertEquals(numberOfStocksInDB, sut.getStocks().size());
    }

    @Test
    public void get_stock_by_given_symbol(){
        String stockTickerExpected = "SPCE";

        StockWrapper stockReturned = sut.getStockByStockSymbol(stockTickerExpected);

        Assert.assertEquals(stockTickerExpected, stockReturned.getStockSymbol());
    }


    @Test
    public void getting_stock_not_included_throws(){
        boolean thrown = false;
        try{
            //get a symbol not in DB
            StockWrapper madeUpStock = sut.getStockByStockSymbol("BATMAN");
//            StockWrapper notMadeUpStock = sut.getStockByStockSymbol("AMD"); //TEST YO TESTS - Comment above and uncomment this to not pass test
        } catch (StockNotFoundException e){
            thrown = true;
        }

        Assert.assertTrue(thrown);

    }

    @Test
    public void updating_price_functions(){
        //OK new logic. Update price to not real time. Then compare with DB? --Update this isn't necessary
        StockWrapper amdPassedInForUpdate = new StockWrapper("AMD",new BigDecimal(10), new Timestamp(System.currentTimeMillis())); //Should I create variables above for the price / timestamp?

        boolean isTrueOnUpdate = sut.update(amdPassedInForUpdate); //Actual use of method

        Assert.assertTrue(isTrueOnUpdate); //well this works



        /*My logic behind this test
            -get AMD stock from YAHOOFINANCE API and keep price
            -Pull price from DB for AMD
                -This is updated (from 99 default value) on start up - therefore tests UPDATE the method??
         */
//        Stock amdFromAPI = new Stock("AMD");
//        try {
//             amdFromAPI = YahooFinance.get("AMD",false);
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//
//
//        BigDecimal amdPriceFromAPI = amdFromAPI.getQuote().getPrice();
//
//        StockWrapper amdFromDataBase = sut.getStockByStockSymbol("AMD");
//        BigDecimal amdPriceFromDatabase = amdFromDataBase.getSharePrice();
//
//        Assert.assertEquals(amdPriceFromAPI, amdPriceFromDatabase);

    }

}
