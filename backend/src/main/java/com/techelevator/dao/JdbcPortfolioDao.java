package com.techelevator.dao;

import com.techelevator.model.portfolio.Portfolio;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcPortfolioDao implements PortfolioDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPortfolioDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // TODO Fill in all methods below.

    @Override
    public List<Portfolio> getPortfolios() {
        return null;
    }

    @Override
    public Portfolio getPortfolioByPortfolioId(Long portfolioId) {
        return null;
    }

    @Override
    public List<Portfolio> getPortfoliosByUserId(Long userId) {
        return null;
    }

    @Override
    public List<Portfolio> getPortfoliosByGameId(Long gameId) {
        return null;
    }

    @Override
    public boolean create(Portfolio portfolioToCreate) {
        return false;
    }

    @Override
    public boolean update(Portfolio portfolioToUpdate) {
        return false;
    }

    @Override
    public boolean delete(Long portfolioIdToDelete) {
        return false;
    }

    private Portfolio mapRowToPortfolio(SqlRowSet results) {
        return null;
    }
}
