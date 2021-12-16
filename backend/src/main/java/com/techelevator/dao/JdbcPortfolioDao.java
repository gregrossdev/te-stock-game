package com.techelevator.dao;

import com.techelevator.model.portfolio.Portfolio;
import com.techelevator.model.portfolio.PortfolioNotFoundException;
import com.techelevator.model.portfolioStock.PortfolioStock;
import com.techelevator.model.portfolioStock.PortfolioStockNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Portfolio getPortfolioByUserIdAndGameId(Long userId, Long gameId) {

        String sql = "SELECT * FROM portfolios WHERE user_id = ? AND game_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, gameId);

        if (results.next()) {
            return mapRowToPortfolio(results);
        }

        throw new PortfolioNotFoundException();
    }

    @Override
    public List<PortfolioStock> getPortfolioStocksByPortfolioId(Long portfolioId) {
        List<PortfolioStock> portfolioStocksByPortfolioId = new ArrayList<PortfolioStock>();
        String sql = "SELECT * FROM portfolios_stocks WHERE portfolio_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, portfolioId);
        while (results.next()) {
            portfolioStocksByPortfolioId.add(mapRowToPortfolioStock(results));
        }
        return portfolioStocksByPortfolioId;
    }

    @Override
    public PortfolioStock getPortfolioStockByPortfolioIdAndStockSymbol(Long portfolioId, String stockSymbol) {
        PortfolioStock portfolioStock = new PortfolioStock();

        String sql = "SELECT * FROM portfolios_stocks WHERE portfolio_id = ? AND stock_symbol = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, portfolioId, stockSymbol);

        if (results.next()) {
            return mapRowToPortfolioStock(results);
        }
        throw new PortfolioStockNotFoundException();
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
        String sql = "UPDATE portfolios " +
                "SET user_id = ?, game_id = ?, portfolio_cash = ?, portfolio_stocks_value = ?, portfolio_total_value = ?, " +
                "portfolio_status = ? " +
                "WHERE portfolio_id =?;";
        return jdbcTemplate.update(sql, portfolioToUpdate.getUserId(), portfolioToUpdate.getGameId(),
                portfolioToUpdate.getPortfolioCash(), portfolioToUpdate.getPortfolioStocksValue(), portfolioToUpdate.getPortfolioTotalValue(),
                portfolioToUpdate.getPortfolioStatus(), portfolioToUpdate.getPortfolioId()) == 1;
    }

    // EXPERIMENTAL METHOD BELOW! DEEP SQL MAGIC! STILL NEED TO TEST!
    @Override
    public void updatePortfolioTotalValues() {

        String sql = "SELECT portfolios.portfolio_id, portfolios.portfolio_cash, portfolios.portfolio_stocks_value, " +
                "portfolios.portfolio_total_value, portfolios_stocks.stock_symbol, portfolios_stocks.total_shares, " +
                "stocks.share_price " +
                "FROM portfolios_stocks " +
                "JOIN portfolios ON portfolios_stocks.portfolio_id = portfolios.portfolio_id " +
                "JOIN stocks ON portfolios_stocks.stock_symbol = stocks.stock_symbol " +
                "WHERE portfolios.portfolio_status = 'ACTIVE';";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        Map<Long, BigDecimal> portfolioStocksValueMap = new HashMap<>();

        while (results.next()) {
            Long portfolioId = results.getLong("portfolio_id");
            BigDecimal totalShares = results.getBigDecimal("total_shares");
            BigDecimal stockValue = results.getBigDecimal("share_price");

            if (portfolioStocksValueMap.get(portfolioId) == null) {
                assert stockValue != null;
                portfolioStocksValueMap.put(portfolioId, (stockValue.multiply(totalShares)));
            } else {
                assert stockValue != null;
                portfolioStocksValueMap.put(portfolioId, (portfolioStocksValueMap.get(portfolioId).add(stockValue.multiply(totalShares))));
            }
        }

        for (Map.Entry<Long, BigDecimal> entry : portfolioStocksValueMap.entrySet()) {
            Long portfolioId = entry.getKey();
            BigDecimal portfolioStocksValue = entry.getValue();
            String updateSql = "UPDATE portfolios " +
                    "SET portfolio_stocks_value = ?, " +
                    "portfolio_total_value = portfolio_cash + ? " +
                    "WHERE portfolio_id = ?;";
            jdbcTemplate.update(updateSql, portfolioStocksValue, portfolioStocksValue, portfolioId);
        }
    }

    @Override
    public void buyStock(Long portfolioId, String stockSymbol, BigDecimal totalSharesToBuy) {
        String sql = "INSERT INTO portfolios_stocks (portfolio_id, stock_symbol, total_shares) " +
                "VALUES (?, ?, ?) " +
                "ON CONFLICT (portfolio_id, stock_symbol) DO UPDATE SET total_shares = portfolios_stocks.total_shares + EXCLUDED.total_shares;";
        jdbcTemplate.update(sql, portfolioId, stockSymbol, totalSharesToBuy);
    }

    @Override
    public void sellStock(Long portfolioId, String stockSymbol, BigDecimal totalSharesToSell) {
        String sql = "UPDATE portfolios_stocks SET total_shares = total_shares - ? WHERE portfolio_id = ? AND stock_symbol = ?;";
        jdbcTemplate.update(sql, totalSharesToSell, portfolioId, stockSymbol);
    }

    @Override
    public boolean delete(Long portfolioIdToDelete) {
        String sql = "DELETE FROM portfolios WHERE portfolio_id = ?;";
        return jdbcTemplate.update(sql, portfolioIdToDelete) == 1;
    }

    @Override
    public List<Portfolio> getPendingPortfoliosByUserId(Long userId){
        List<Portfolio> returnedPendingPortfoliosFromUserId = new ArrayList<>();
        String sql = "SELECT * FROM portfolios WHERE (user_id = ? AND portfolio_status = 'PENDING');";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while(results.next()){
            returnedPendingPortfoliosFromUserId.add(mapRowToPortfolio(results));
        }
        return returnedPendingPortfoliosFromUserId;
    }

    private Portfolio mapRowToPortfolio(SqlRowSet results) {
        Portfolio portfolio = new Portfolio();
        portfolio.setPortfolioId(results.getLong("portfolio_id"));
        portfolio.setUserId(results.getLong("user_id"));
        portfolio.setGameId(results.getLong("game_id"));
        portfolio.setPortfolioCash(results.getBigDecimal("portfolio_cash"));
        portfolio.setPortfolioStocksValue(results.getBigDecimal("portfolio_stocks_value"));
        portfolio.setPortfolioTotalValue(results.getBigDecimal("portfolio_total_value"));
        portfolio.setPortfolioStatus(results.getString("portfolio_status"));
        return portfolio;
    }

    private PortfolioStock mapRowToPortfolioStock(SqlRowSet results) {
        PortfolioStock portfolioStock = new PortfolioStock();
        portfolioStock.setPortfolioId(results.getLong("portfolio_id"));
        portfolioStock.setStockSymbol(results.getString("stock_symbol"));
        portfolioStock.setTotalShares(results.getBigDecimal("total_shares"));
        return portfolioStock;
    }
}
