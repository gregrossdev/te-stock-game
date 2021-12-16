package com.techelevator.service;

import java.io.IOException;

public interface StockService {

    void updateAllStocksEveryXMin() throws IOException, InterruptedException;
    void checkUpdateGameWinner();
}
