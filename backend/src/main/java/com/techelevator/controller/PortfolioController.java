package com.techelevator.controller;

import com.techelevator.dao.PortfolioDao;
import com.techelevator.model.portfolio.Portfolio;
import com.techelevator.model.portfolioStock.PortfolioStock;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
//@PreAuthorize("isAuthenticated()")
@RequestMapping("/api/portfolios/")
public class PortfolioController {
    private final PortfolioDao portfolioDao;

    public PortfolioController(PortfolioDao portfolioDao) {
        this.portfolioDao = portfolioDao;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Portfolio> getPortfolios() {
        return portfolioDao.getPortfolios();
    }

    @RequestMapping(path = "{portfolioId}", method = RequestMethod.GET)
    public Portfolio getPortfolioByPortfolioId(@PathVariable Long portfolioId) {
        return portfolioDao.getPortfolioByPortfolioId(portfolioId);
    }

    @RequestMapping(path = "user/{userId}", method = RequestMethod.GET)
    public List<Portfolio> getPortfoliosByUserId(@PathVariable Long userId) {
        return portfolioDao.getPortfoliosByUserId(userId);
    }

    @RequestMapping(path = "user/pending/{userId}", method = RequestMethod.GET)
    public List<Portfolio> getPendingPortfoliosByUserId(@PathVariable Long userId) {
        return portfolioDao.getPendingPortfoliosByUserId(userId);
    }

    @RequestMapping(path = "game/{gameId}", method = RequestMethod.GET)
    public List<Portfolio> getPortfoliosByGameId(@PathVariable Long gameId) {
        return portfolioDao.getPortfoliosByGameId(gameId);
    }

    @RequestMapping(path = "user/{userId}/game/{gameId}", method = RequestMethod.GET)
    public Portfolio getPortfolioByUserIdAndGameId(@PathVariable Long userId, @PathVariable Long gameId) {
        return portfolioDao.getPortfolioByUserIdAndGameId(userId, gameId);
    }


    @RequestMapping(path="portfolio-stocks/{portfolioId}", method = RequestMethod.GET)
    public List<PortfolioStock> getPortfolioStocksByPortfolioId(@PathVariable Long portfolioId) {
        return portfolioDao.getPortfolioStocksByPortfolioId(portfolioId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public boolean create(@RequestBody Portfolio portfolioToCreate) {
        return portfolioDao.create(portfolioToCreate);
    }

    @RequestMapping(path = "{portfolioId}", method = RequestMethod.PUT)
    public boolean update(@RequestBody Portfolio portfolioToUpdate) {
        return portfolioDao.update(portfolioToUpdate);
    }

    @RequestMapping(path = "{portfolioIdToDelete}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable Long portfolioIdToDelete) {
        return portfolioDao.delete(portfolioIdToDelete);
    }
}
