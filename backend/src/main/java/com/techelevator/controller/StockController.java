package com.techelevator.controller;

import com.techelevator.dao.StockDao;
import com.techelevator.model.stock.StockWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/stocks/")
public class StockController {
    private final StockDao stockDao;

    public StockController(StockDao stockDao) {
        this.stockDao = stockDao;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<StockWrapper> getStocks() {
        return stockDao.getStocks();
    }

    @RequestMapping(path = "{stockSymbol}", method = RequestMethod.GET)
    public StockWrapper getStockByStockSymbol(@PathVariable String stockSymbol) {
        return stockDao.getStockByStockSymbol(stockSymbol);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public boolean create(StockWrapper stockWrapperToCreate) {
        return stockDao.create(stockWrapperToCreate);
    }

    @RequestMapping(path = "{stockSymbol}", method = RequestMethod.PUT)
    public boolean update(@RequestBody StockWrapper stockWrapperToUpdate) {
        return stockDao.update(stockWrapperToUpdate);
    }

    @RequestMapping(path = "{stockSymbolToDelete}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable String stockSymbolToDelete) {
        return stockDao.delete(stockSymbolToDelete);
    }

}
