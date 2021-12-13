package com.techelevator.controller;

import com.techelevator.dao.StockDao;
import com.techelevator.model.stock.StockWrapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/stocks/test/")
public class StockAPIController {

    private final StockDao stockDao;
    public StockAPIController(StockDao stockDao) throws IOException {
        this.stockDao = stockDao;
    }
    @RequestMapping(path = "endpoint2", method = RequestMethod.GET)
    public void updateAllStocksEveryXMin() throws IOException, InterruptedException {

        //build out arrayFrom List
        List<StockWrapper> stockListToConvert = stockDao.getStocks();
        String[] arrayOfSymbols = new String[stockListToConvert.size()];
        for (int i = 0; i <= arrayOfSymbols.length-1; i++){
            arrayOfSymbols[i] = stockListToConvert.get(i).getStockSymbol();
            System.out.println(stockListToConvert.get(i).getStockSymbol());
        }
//        stockListToConvert.get(1).getStockSymbol();
        Map<String, Stock> stocks = YahooFinance.get(arrayOfSymbols, false);

        while (true) {
            for (String stockSymbol : stocks.keySet()) {
//            System.out.println(stockSymbol); prints stock symbol use to get actual stock
                Stock tempStock = stocks.get(stockSymbol);
                BigDecimal updatedPrice = tempStock.getQuote().getPrice();
//                if(tempStock.getSymbol().equals("TEST")){
//                    updatedPrice = new BigDecimal(100);
//                }
//                stockDao.testMethodUpdatePriceTwo(updatedPrice, stockSymbol); //no longer needed now that we update with real update method
                //TODO - Error because TEST symbol
                Timestamp currentTempTimestamp = new Timestamp(System.currentTimeMillis());
                StockWrapper tempStockWrapper = new StockWrapper(tempStock.getSymbol(), tempStock.getQuote().getPrice(), currentTempTimestamp);
                stockDao.update(tempStockWrapper);
            }
            System.out.println("Check back in 15 minutes for an updated price!");
            Thread.sleep(900000); //90 is 15 minutes
        }
    }

}