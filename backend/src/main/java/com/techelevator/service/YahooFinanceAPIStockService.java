package com.techelevator.service;

import com.techelevator.dao.GameDao;
import com.techelevator.dao.PortfolioDao;
import com.techelevator.dao.StockDao;
import com.techelevator.model.game.Game;
import com.techelevator.model.portfolio.Portfolio;
import com.techelevator.model.stock.StockWrapper;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class YahooFinanceAPIStockService implements StockService {

    private final StockDao stockDao;
    private final PortfolioDao portfolioDao;
    private final GameDao gameDao;

    public YahooFinanceAPIStockService(StockDao stockDao, PortfolioDao portfolioDao, GameDao gameDao) {
        this.stockDao = stockDao;
        this.portfolioDao = portfolioDao;
        this.gameDao = gameDao;
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
            try {
                stocks = YahooFinance.get(arrayOfSymbols, false);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (String stockSymbol : stocks.keySet()) {
                Stock tempStock = stocks.get(stockSymbol);
                BigDecimal updatedPrice = tempStock.getQuote().getPrice();
                Timestamp currentTempTimestamp = new Timestamp(System.currentTimeMillis());
                StockWrapper tempStockWrapper = new StockWrapper(tempStock.getSymbol(), tempStock.getName(), updatedPrice, currentTempTimestamp);
//                System.out.println("Stocks ARE updating");
                stockDao.update(tempStockWrapper);

            }
            portfolioDao.updatePortfolioTotalValues();
            try {
//                System.out.println("Stocks should be updating");
//                Thread.sleep(30000); //30000 is 30 seconds

                Thread.sleep(900000); //900000 is 15 minutes
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void checkUpdateGameWinner(){
        List<Game> gameListWithouWinnersFromDB = gameDao.gamesWithoutWinnersYet();
        Timestamp currentTempTimestamp = new Timestamp(System.currentTimeMillis());
        for(int z = 0; z <= gameListWithouWinnersFromDB.size()-1; z++){
            System.out.println("Looping through gamesList");
            if(gameListWithouWinnersFromDB.get(z).getEndTimestamp().before(currentTempTimestamp)){

                List<Portfolio> portfolioListById = portfolioDao.getPortfoliosByGameId((long) z);
                for(int i = 0; i <= portfolioListById.size()-1; i ++){

                    BigDecimal highestValue = portfolioListById.get(i).getPortfolioTotalValue();
                    BigDecimal totalValueInForLoopi = portfolioListById.get(i).getPortfolioTotalValue();

                    System.out.println("The highest value is:" + highestValue);
                    System.out.println(totalValueInForLoopi);
                    if(totalValueInForLoopi.compareTo(highestValue) == 1 || totalValueInForLoopi.compareTo(highestValue) == 0 ){
                        gameDao.updateGameEnd(portfolioListById.get(i).getUserId(),portfolioListById.get(i).getGameId());
                    }
//
//
                }
        }






//            gameDao.updateGameEnd(gameListWithouWinnersFromDB.get(0).getGameId(),gameListWithouWinnersFromDB.get(0).getGameOrganizer());
//            System.out.println("For now the winner is defaulted to game organizer" + gameListWithouWinnersFromDB.get(0).getGameOrganizer());


        };


    }
}
