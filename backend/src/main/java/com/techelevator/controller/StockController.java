package com.techelevator.controller;

import com.techelevator.dao.StockDao;
import com.techelevator.model.stock.Stock;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/stocks/") // TODO double-check that this is the URL structure we want.
public class StockController {

    private StockDao dao;

    public StockController(StockDao dao) {
        this.dao = dao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Stock> list() {
        return dao.getStocks();
    }

}
