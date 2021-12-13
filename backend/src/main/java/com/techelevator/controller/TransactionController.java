package com.techelevator.controller;

import com.techelevator.dao.PortfolioDao;
import com.techelevator.dao.TransactionDao;
import com.techelevator.model.portfolio.Portfolio;
import com.techelevator.model.portfolioStock.PortfolioStock;
import com.techelevator.model.transaction.Transaction;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin
@RestController
//@PreAuthorize("isAuthenticated()")
@RequestMapping("/api/transactions/")
public class TransactionController {
    private final TransactionDao transactionDao;
    private final PortfolioDao portfolioDao;

    public TransactionController(TransactionDao transactionDao, PortfolioDao portfolioDao) {
        this.transactionDao = transactionDao;
        this.portfolioDao = portfolioDao;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Transaction> getTransactions() {
        return transactionDao.getTransactions();
    }

    @RequestMapping(path = "{transactionId}", method = RequestMethod.GET)
    public Transaction getTransactionByTransactionId(@PathVariable Long transactionId) {
        return transactionDao.getTransactionByTransactionId(transactionId);
    }

    @RequestMapping(path = "portfolio/{portfolioId}", method = RequestMethod.GET)
    public List<Transaction> getTransactionsByPortfolioId(@PathVariable Long portfolioId) {
        return transactionDao.getTransactionsByPortfolioId(portfolioId);
    }

    @RequestMapping(path = "stock/{stockSymbol}", method = RequestMethod.GET)
    public List<Transaction> getTransactionsByStockSymbol(@PathVariable String stockSymbol) {
        return transactionDao.getTransactionsByStockSymbol(stockSymbol);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public boolean create(@RequestBody Transaction transactionToCreate) {
        Long portfolioId = transactionToCreate.getPortfolioId();
        String stockSymbol = transactionToCreate.getStockSymbol();
        String transactionType = transactionToCreate.getTransactionType();
        BigDecimal transactionAmount = transactionToCreate.getTransactionAmount();
        BigDecimal transactionShares = transactionToCreate.getTransactionShares();

        Portfolio portfolio = portfolioDao.getPortfolioByPortfolioId(portfolioId);
        BigDecimal portfolioCash = portfolio.getPortfolioCash();

        PortfolioStock portfolioStock = portfolioDao.getPortfolioStockByPortfolioIdAndStockSymbol(portfolioId, stockSymbol);
        BigDecimal portfolioShares = portfolioStock.getTotalShares();

        if (transactionType.equalsIgnoreCase("BUY") && portfolioCash.compareTo(transactionAmount) >= 0) {
            portfolio.setPortfolioCash(portfolioCash.subtract(transactionAmount));
            portfolioDao.buyStock(portfolioId, stockSymbol, transactionShares);
            portfolioDao.update(portfolio);
        } else if (transactionType.equalsIgnoreCase("SELL") && portfolioShares.compareTo(transactionShares) >= 0) {
            portfolio.setPortfolioCash(portfolioCash.add(transactionAmount));
            portfolioDao.sellStock(portfolioId, stockSymbol, transactionShares);
            portfolioDao.update(portfolio);
        } else {
            return false;
        }

        return transactionDao.create(transactionToCreate);
    }

    @RequestMapping(path = "{transactionId}", method = RequestMethod.PUT)
    public boolean update(@RequestBody Transaction transactionToUpdate) {
        return transactionDao.update(transactionToUpdate);
    }

    @RequestMapping(path = "{transactionIdToDelete}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable Long transactionIdToDelete) {
        return transactionDao.delete(transactionIdToDelete);
    }
}
