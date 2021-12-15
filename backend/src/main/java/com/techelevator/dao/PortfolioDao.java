package com.techelevator.dao;

import com.techelevator.model.portfolio.Portfolio;
import com.techelevator.model.portfolioStock.PortfolioStock;

import java.math.BigDecimal;
import java.util.List;

public interface PortfolioDao {

    List<Portfolio> getPortfolios();

    Portfolio getPortfolioByPortfolioId(Long portfolioId);

    List<Portfolio> getPortfoliosByUserId(Long userId);

    List<Portfolio> getPortfoliosByGameId(Long gameId);

    Portfolio getPortfolioByUserIdAndGameId(Long userId, Long gameId);

    List<PortfolioStock> getPortfolioStocksByPortfolioId(Long portfolioId);

    PortfolioStock getPortfolioStockByPortfolioIdAndStockSymbol(Long portfolioId, String stockSymbol);

    boolean create(Portfolio portfolioToCreate);

    boolean update(Portfolio portfolioToUpdate);

    void updatePortfolioTotalValues();

    void buyStock(Long portfolioId, String stockSymbol, BigDecimal totalShares);

    void sellStock(Long portfolioId, String stockSymbol, BigDecimal totalShares);

    boolean delete(Long portfolioIdToDelete);

}
