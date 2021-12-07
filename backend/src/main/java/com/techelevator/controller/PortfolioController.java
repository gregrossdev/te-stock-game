package com.techelevator.controller;

import com.techelevator.dao.PortfolioDao;
import com.techelevator.model.portfolio.Portfolio;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/api/portfolios/") // TODO double-check that this is the URL structure we want.
public class PortfolioController {
    private final PortfolioDao portfolioDao;

    public PortfolioController(PortfolioDao portfolioDao) {
        this.portfolioDao = portfolioDao;
    }

    @RequestMapping(path="", method = RequestMethod.GET)
    public List<Portfolio> getPortfolios() {
        return portfolioDao.getPortfolios();
    }

    @RequestMapping(path="{portfolioId}", method = RequestMethod.GET)
    public Portfolio getPortfolioByPortfolioId(@PathVariable Long portfolioId) {
        return portfolioDao.getPortfolioByPortfolioId(portfolioId);
    }

    @RequestMapping(path="user/{userId}", method = RequestMethod.GET)
    public List<Portfolio> getPortfoliosByUserId(@PathVariable Long userId) {
        return portfolioDao.getPortfoliosByUserId(userId);
    }

    @RequestMapping(path="game/{gameId}", method = RequestMethod.GET)
    public List<Portfolio> getPortfoliosByGameId(@PathVariable Long gameId) {
        return portfolioDao.getPortfoliosByGameId(gameId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="", method = RequestMethod.POST)
    public boolean create(@RequestBody Portfolio portfolioToCreate) {
        return portfolioDao.create(portfolioToCreate);
    }

    // TODO Check that this is correct, using the portfolioId as a path variable even when it's not used in the method.
    @RequestMapping(path="{portfolioId}", method = RequestMethod.PUT)
    public boolean update(@RequestBody Portfolio portfolioToUpdate) {
        return portfolioDao.update(portfolioToUpdate);
    }

    @RequestMapping(path="{portfolioIdToDelete}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable Long portfolioIdToDelete) {
        return portfolioDao.delete(portfolioIdToDelete);
    }
}
