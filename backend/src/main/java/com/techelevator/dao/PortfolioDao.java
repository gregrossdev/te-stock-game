package com.techelevator.dao;

import com.techelevator.model.portfolio.Portfolio;

import java.util.List;

public interface PortfolioDao {

    List<Portfolio> getPortfolios();

    Portfolio getPortfolioByPortfolioId(Long portfolioId);

    List<Portfolio> getPortfoliosByUserId(Long userId);

    List<Portfolio> getPortfoliosByGameId(Long gameId);

    boolean create(Portfolio portfolioToCreate);

    boolean update(Portfolio portfolioToUpdate);

    void updatePortfolioTotalValues();

    boolean delete(Long portfolioIdToDelete);

}
