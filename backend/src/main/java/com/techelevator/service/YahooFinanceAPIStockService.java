package com.techelevator.service;

import com.techelevator.dao.PortfolioDao;
import com.techelevator.dao.StockDao;
import com.techelevator.model.portfolio.Portfolio;
import com.techelevator.model.stock.StockWrapper;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Component
public class YahooFinanceAPIStockService implements StockService {

    private final StockDao stockDao;
    private final PortfolioDao portfolioDao;

    public YahooFinanceAPIStockService(StockDao stockDao, PortfolioDao portfolioDao) {
        this.stockDao = stockDao;
        this.portfolioDao = portfolioDao;
    }

    public void updateAllStocksEveryXMin() {
        List<StockWrapper> stockListToConvert = stockDao.getStocks();
        String[] arrayOfSymbols = new String[stockListToConvert.size()];
        for (int i = 0; i < arrayOfSymbols.length; i++) {
            arrayOfSymbols[i] = stockListToConvert.get(i).getStockSymbol();
        }

        Map<String, Stock> stocks = null;
        try {
            stocks = YahooFinance.get(arrayOfSymbols, false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            assert stocks != null;
            for (String stockSymbol : stocks.keySet()) {
                Stock tempStock = stocks.get(stockSymbol);
                BigDecimal updatedPrice = tempStock.getQuote().getPrice();
                Timestamp currentTempTimestamp = new Timestamp(System.currentTimeMillis());
                StockWrapper tempStockWrapper = new StockWrapper(tempStock.getSymbol(), updatedPrice, currentTempTimestamp);
                stockDao.update(tempStockWrapper);
            }
            portfolioDao.updatePortfolioTotalValues();
            try {
                Thread.sleep(900000); //90 is 15 minutes
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
