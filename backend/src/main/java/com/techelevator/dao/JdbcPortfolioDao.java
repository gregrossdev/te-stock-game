package com.techelevator.dao;

import com.techelevator.model.portfolio.Portfolio;
import com.techelevator.model.portfolio.PortfolioNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPortfolioDao implements PortfolioDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPortfolioDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Portfolio> getPortfolios() {
        List<Portfolio> portfolios = new ArrayList<>();
        String sql = "SELECT * FROM portfolios;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            portfolios.add(mapRowToPortfolio(results));
        }
        return portfolios;
    }

    @Override
    public Portfolio getPortfolioByPortfolioId(Long portfolioId) {
        String sql = "SELECT * FROM portfolios WHERE portfolio_id =?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, portfolioId);
        if (results.next()) {
            return mapRowToPortfolio(results);
        }
        throw new PortfolioNotFoundException();
    }

    @Override
    public List<Portfolio> getPortfoliosByUserId(Long userId) {
        List<Portfolio> portfolios = new ArrayList<>();
        String sql = "SELECT * FROM portfolios WHERE user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            portfolios.add(mapRowToPortfolio(results));
        }
        return portfolios;
    }

    @Override
    public List<Portfolio> getPortfoliosByGameId(Long gameId) {
        List<Portfolio> portfolios = new ArrayList<>();
        String sql = "SELECT * FROM portfolios WHERE game_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, gameId);
        while (results.next()) {
            portfolios.add(mapRowToPortfolio(results));
        }
        return portfolios;
    }

    @Override
    public boolean create(Portfolio portfolioToCreate) {
        String sql = "INSERT INTO portfolios (user_id, game_id, portfolio_status) " +
                "VALUES (?, ?, ?);";
        return jdbcTemplate.update(sql, portfolioToCreate.getUserId(), portfolioToCreate.getGameId(),
                portfolioToCreate.getPortfolioStatus()) == 1;
    }

    @Override
    public boolean update(Portfolio portfolioToUpdate) {
        String sql = "UPDATE portfolios SET user_id = ?, game_id = ?, portfolio_cash = ?, portfolio_stocks_value = ?, portfolio_total_value = ?, " +
                "portfolio_status = ? WHERE portfolio_id =?;";
        return jdbcTemplate.update(sql, portfolioToUpdate.getUserId(), portfolioToUpdate.getGameId(),
                portfolioToUpdate.getPortfolioCash(), portfolioToUpdate.getPortfolioStocksValue(), portfolioToUpdate.getPortfolioTotalValue(),
                portfolioToUpdate.getPortfolioStatus()) == 1;
    }

    // EXPERIMENTAL METHOD BELOW! DEEP SQL MAGIC! STILL NEED TO TEST!
    @Override
    public void updatePortfolioTotalValues() {

        // Set all active portfolios' portfolio_stocks_value to 0.
        String zeroPortfolioStocksValueSql = "UPDATE portfolios SET portfolio_stocks_value = 0 WHERE portfolio_status = 'ACTIVE';";
        jdbcTemplate.update(zeroPortfolioStocksValueSql);

        // Get all the rows from portfolios_stocks that apply to active portfolios.
        String sql = "SELECT * from portfolios_stocks " +
                "JOIN portfolios ON portfolios_stocks.portfolio_id = portfolios.portfolio_id " +
                "WHERE portfolios.portfolio_status = 'ACTIVE';";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        // Loop through the rows in portfolios_stocks, adding (total_shares * share_price) to portfolio_stocks_value.
        while (results.next()) {
            Long portfolioId = results.getLong("portfolios_stocks.portfolio_id");
            String stockSymbol = results.getString("portfolios_stocks.stock_symbol");

            String updateSql = "UPDATE portfolios " +
                    "SET portfolio_stocks_value = portfolio_stocks_value + (portfolios_stocks.total_shares * stocks.share_price) " +
                    "FROM portfolios_stocks " +
                    "JOIN stocks ON portfolios_stocks.stock_symbol = stocks.stock_symbol " +
                    "WHERE portfolios.portfolio_id = portfolios_stocks.portfolio_id " +
                    "AND portfolios.portfolio_id = ? AND portfolios_stocks.stock_symbol = ?;";

            jdbcTemplate.update(updateSql, portfolioId, stockSymbol);
        }

        String calculatePortfolioTotalValueSql = "UPDATE portfolios SET portfolio_total_value = portfolio_cash + portfolio_stocks_value " +
                "WHERE portfolio_status = 'ACTIVE';";
        jdbcTemplate.update(calculatePortfolioTotalValueSql);
    }

    @Override
    public boolean delete(Long portfolioIdToDelete) {
        String sql = "DELETE FROM portfolios WHERE portfolio_id = ?;";
        return jdbcTemplate.update(sql, portfolioIdToDelete) == 1;
    }

    private Portfolio mapRowToPortfolio(SqlRowSet results) {
        Portfolio portfolio = new Portfolio();
        portfolio.setPortfolioId(results.getLong("portfolio_id"));
        portfolio.setUserId(results.getLong("user_id"));
        portfolio.setGameId(results.getLong("game_id"));
        portfolio.setPortfolioCash(results.getBigDecimal("portfolio_cash_after_transaction"));
        portfolio.setPortfolioTotalValue(results.getBigDecimal("portfolio_total_value"));
        portfolio.setPortfolioStatus(results.getString("portfolio_status"));
        return portfolio;
    }
}
