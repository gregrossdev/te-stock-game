package com.techelevator.dao;

import com.techelevator.model.game.Game;
import com.techelevator.model.game.GameNotFoundException;
import com.techelevator.model.portfolio.Portfolio;
import com.techelevator.model.portfolio.PortfolioNotFoundException;
import com.techelevator.model.portfolioStock.PortfolioStock;
import com.techelevator.model.portfolioStock.PortfolioStockNotFoundException;
import com.techelevator.model.stock.StockWrapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import yahoofinance.Stock;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class JdbcPortfolioDaoTests extends FinalCapstoneDaoTests{


    private JdbcPortfolioDao sut;

    @Before
    public void setup() {
        DataSource dataSource = this.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcPortfolioDao(jdbcTemplate);
    }

    //getPortfolio
    @Test
    public void get_portfolios_returns_list_of_portfolios(){
        List<Portfolio> portfolioListFromDB = sut.getPortfolios();
        Portfolio portfolioFromPortfolioList = portfolioListFromDB.get(0); //should be portfolio_ID 6
        long portfolioIdSix = portfolioFromPortfolioList.getPortfolioId();

        Assert.assertEquals(6L, portfolioIdSix);
    }

    //getPortfolioByPortfolioId
    @Test
    public void get_portfolio_by_portfolioId_returns_portfolioId(){
        Portfolio portfolioFromDB = sut.getPortfolioByPortfolioId(1L);
        long portfolioId = portfolioFromDB.getPortfolioId();
        Assert.assertEquals(1L, portfolioId);
    }

    @Test
    public void get_portfolios_by_portfolioID_throws_exception() {
        boolean thrown = false;

        try{
            Portfolio portfolioIDThatDoesntExistToThrow = sut.getPortfolioByPortfolioId(0L);
        } catch (PortfolioNotFoundException e) {
            thrown = true;
        }

        Assert.assertTrue(thrown);
    }

    //getPortfoliosByUserId
    @Test
    public void get_portfolios_by_userid_returns_portfolio(){
        List<Portfolio> portfolioListFromDB = sut.getPortfoliosByUserId(2L);
        Portfolio portfolioId = portfolioListFromDB.get(0);
        long portfolioIDTwo = portfolioId.getGameId();
        Assert.assertEquals(2L, portfolioIDTwo);
    }

    //getPortfoliosByGameId
    @Test
    public void get_portfolios_by_gameId(){
        List<Portfolio> portfolioListFromDB = sut.getPortfoliosByGameId(4L);
        int amountOfPortfolios = portfolioListFromDB.size();
        Assert.assertEquals(5L, amountOfPortfolios);
    }

    //getPortfolioByUserIdAndGameId
    @Test
    public void get_portfolios_by_userID_gameID_returns_portfolioId() {
        Portfolio portfolioFromDB = sut.getPortfolioByUserIdAndGameId(2L, 3L);
        long portfolioId = portfolioFromDB.getPortfolioId();
        Assert.assertEquals(11L, portfolioId);
    }

    @Test
    public void get_portfolios_by_userID_gameID_throws_exception() {
        boolean thrown = false;

        try{
            Portfolio userIDAndGameIDThatDoesntExistToThrow = sut.getPortfolioByUserIdAndGameId(0L, 0L);
        } catch (PortfolioNotFoundException e) {
            thrown = true;
        }

        Assert.assertTrue(thrown);
    }

    //getPortfolioStocksByPortfolioId
    @Test
    public void get_stonks_by_portfolioId() {
        List<PortfolioStock> listStocksByPortfolioID = sut.getPortfolioStocksByPortfolioId(1L);
        int stonkSize = listStocksByPortfolioID.size();
        Assert.assertEquals(1L, stonkSize);
    }

    //getPortfolioStockByPortfolioIdAndStockSymbol
    @Test
    public void get_share_amount_with_id_and_symbol() {
        PortfolioStock portfolioStockFromDb = sut.getPortfolioStockByPortfolioIdAndStockSymbol(1L, "RKLB");
        BigDecimal stonkShares = portfolioStockFromDb.getTotalShares();
        Assert.assertEquals(stonkShares, new BigDecimal(10));
    }

    @Test
    public void get_share_amount_with_id_and_symbol_throws_exception() {
        boolean thrown = false;

        try{
            PortfolioStock getShareAmtDoesntExistToThrow = sut.getPortfolioStockByPortfolioIdAndStockSymbol(0L, "AMZN");
        } catch (PortfolioStockNotFoundException e) {
            thrown = true;
        }

        Assert.assertTrue(thrown);
    }

    //create
//    @Test
//    public void create_creates_a_portfolio() {
//        Portfolio testPortfolio = null;
//        testPortfolio.setPortfolioId(1000L);
//        testPortfolio.setUserId(1L);
//        testPortfolio.setGameId(1000L);
//        testPortfolio.setPortfolioCash(new BigDecimal(100000));
//        testPortfolio.setPortfolioStocksValue(new BigDecimal(100000));
//        testPortfolio.setPortfolioTotalValue(new BigDecimal(100000));
//        testPortfolio.setPortfolioStatus("ACTIVE");
//
//    }
    //update
    @Test
    public void delete_deletes_a_portfolio() {

    }

    //updatePortfolioTotalValues

    //buyStock

    //sellStock

    //delete
}
