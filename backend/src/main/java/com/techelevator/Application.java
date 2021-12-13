package com.techelevator;

import com.techelevator.service.StockService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;


@SpringBootApplication
public class Application {

    private static StockService stockService;

    public Application(StockService stockService) {
        this.stockService = stockService;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(Application.class, args);
        stockService.updateAllStocksEveryXMin();
    }

}
