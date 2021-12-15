package com.techelevator.dao;

import com.techelevator.model.portfolio.Portfolio;
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

    @Test
    public void get_portfolios_returns_list_of_portfolios(){
        List<Portfolio> portfolioListFromDB = sut.getPortfolios();
        Portfolio portfolioFromPortfolioList = portfolioListFromDB.get(0); //should be portfolio_ID 6
        long portfolioIdSix = portfolioFromPortfolioList.getPortfolioId();

        Assert.assertEquals(6L, portfolioIdSix);
    }

    @Test
    public void get_portfolio_by_portfolioId_returns_portfolioId(){
        Portfolio portfolioFromDB = sut.getPortfolioByPortfolioId(1L);
        long portfolioId = portfolioFromDB.getPortfolioId();
        Assert.assertEquals(1L, portfolioId);
    }

    @Test
    public void get_portfolios_by_userid_returns_portfolio(){
        List<Portfolio> portfolioListFromDB = sut.getPortfoliosByUserId(2L);
        Portfolio portfolioId = portfolioListFromDB.get(0);
        long portfolioIDTwo = portfolioId.getGameId();
        Assert.assertEquals(2L, portfolioIDTwo);
    }

    @Test
    public void get_portfolios_by_gameId(){
        List<Portfolio> portfolioListFromDB = sut.getPortfoliosByGameId(4L);
        int amountOfPortfolios = portfolioListFromDB.size();
        Assert.assertEquals(5L, amountOfPortfolios);
    }

}
