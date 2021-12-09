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
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/stocks/test/")
public class StockAPIController {

    private StockDao stockDao;

    String[] stockSymbols = new String[]{"SPCE", "AMD", "MP", "AMD", "MSFT", "IRM", "LCID", "CGC", "AMZN", "AAPL"};
    Map<String, Stock> stocks = YahooFinance.get(stockSymbols, false);

    public StockAPIController(StockDao stockDao) throws IOException {
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
    @RequestMapping(path="endpoint2", method = RequestMethod.GET)
    public void updateAllStocksEveryXMin() throws IOException, InterruptedException {
        //Why am I updating one at a time? I should create a method to update them all

        while(true){
            for(String stockSymbol : stocks.keySet()){
//            System.out.println(stockSymbol); prints stock symbol use to get actual stock
                Stock tempStock = stocks.get(stockSymbol);
                BigDecimal updatedPrice = tempStock.getQuote().getPrice();

                stockDao.testMethodUpdatePriceTwo(updatedPrice, stockSymbol);


            }
            System.out.println("Check back in 5 minutes for an updated price!");
            Thread.sleep(300000);
        }


    }

}
