package com.techelevator.controller;

import com.techelevator.dao.StockDao;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@CrossOrigin
@RestController
@RequestMapping("/api/stocks/test/")
public class StockAPIController {

    private StockDao stockDao;

    public StockAPIController(StockDao stockDao) {
        this.stockDao = stockDao;
    }


//
//    @RequestMapping(path="endpoint", method = RequestMethod.PUT)
//    public boolean testMethod() throws IOException {
//        Stock testStock = YahooFinance.get("SPCE");
//        BigDecimal price = testStock.getQuote().getPrice();
//        LocalDateTime nowDT = LocalDateTime.now();
//        return stockDao.testMethodUpdatePrice(price);
//    }


    @RequestMapping(path="endpoint", method = RequestMethod.GET)
    public int testMethod2() throws IOException {
        Stock testStock = YahooFinance.get("SPCE");
        BigDecimal price = testStock.getQuote().getPrice();
        LocalDateTime nowDT = LocalDateTime.now();
        stockDao.testMethodUpdatePrice(price);
        return 0;
    }
}
