package com.techelevator.controller;

import com.techelevator.dao.StockDao;
import com.techelevator.model.stock.Stock;
import com.techelevator.model.stock.StockNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/stocks/") // TODO double-check that this is the URL structure we want.
public class StockController {
    private final StockDao stockDao;

    public StockController(StockDao stockDao) {
        this.stockDao = stockDao;
    }

    @RequestMapping(path="", method = RequestMethod.GET)
    public List<Stock> getStocks() {
        return stockDao.getStocks();
    }

    @RequestMapping(path="{stockSymbol}", method = RequestMethod.GET)
    public Stock getStockByStockSymbol(@PathVariable String stockSymbol) {
        return stockDao.getStockByStockSymbol(stockSymbol);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="", method = RequestMethod.POST)
    public boolean create(Stock stockToCreate) {
        return stockDao.create(stockToCreate);
    }

    @RequestMapping(path="{stockSymbol}", method = RequestMethod.PUT)
    public boolean update(@RequestBody Stock stockToUpdate) {
        return stockDao.update(stockToUpdate);
    }

    @RequestMapping(path="{stockSymbolToDelete}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable String stockSymbolToDelete) {
        return stockDao.delete(stockSymbolToDelete);
    }

}
