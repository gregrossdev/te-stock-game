package com.techelevator.dao;

import com.techelevator.model.stock.Stock;

import java.math.BigDecimal;
import java.util.List;

public interface StockDao {

    List<Stock> getStocks();

    Stock getStockByStockSymbol(String stockSymbol);

    boolean create(Stock stockToCreate);

    boolean update(Stock stockToUpdate);

    boolean delete(String stockSymbolToDelete);

    boolean testMethodUpdatePrice(BigDecimal price);

}
