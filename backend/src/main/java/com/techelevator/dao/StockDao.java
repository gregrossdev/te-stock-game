package com.techelevator.dao;

import com.techelevator.model.stock.StockWrapper;

import java.math.BigDecimal;
import java.util.List;

public interface StockDao {

    List<StockWrapper> getStocks();

    StockWrapper getStockByStockSymbol(String stockSymbol);

    boolean create(StockWrapper stockWrapperToCreate);

    boolean update(StockWrapper stockWrapperToUpdate);

    boolean delete(String stockSymbolToDelete);

    boolean testMethodUpdatePrice(BigDecimal price);

    boolean testMethodUpdatePriceTwo(BigDecimal price, String ticker);
}
