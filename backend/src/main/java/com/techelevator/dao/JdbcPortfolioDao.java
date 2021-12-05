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
        String sql = "INSERT INTO portfolios (user_id, game_id, portfolio_balance, portfolio_value, portfolio_status) " +
                "VALUES (?, ?, ?, ?, ?);";
        return jdbcTemplate.update(sql, portfolioToCreate.getUserId(), portfolioToCreate.getGameId(),
                portfolioToCreate.getPortfolioBalance(), portfolioToCreate.getPortfolioValue(),
                portfolioToCreate.getPortfolioStatus()) == 1;
    }

    @Override
    public boolean update(Portfolio portfolioToUpdate) {
        String sql = "UPDATE portfolios SET user_id = ?, game_id = ?, portfolio_balance = ?, portfolio_value = ?, " +
                "portfolio_status = ? WHERE portfolio_id =?;";
        return jdbcTemplate.update(sql, portfolioToUpdate.getUserId(), portfolioToUpdate.getGameId(),
                portfolioToUpdate.getPortfolioBalance(), portfolioToUpdate.getPortfolioValue(),
                portfolioToUpdate.getPortfolioStatus()) == 1;
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
        portfolio.setPortfolioBalance(results.getBigDecimal("portfolio_balance"));
        portfolio.setPortfolioValue(results.getBigDecimal("portfolio_value"));
        portfolio.setPortfolioStatus(results.getString("portfolio_status"));
        return portfolio;
    }
}
