package com.techelevator.model.stock;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.NOT_FOUND, reason = "Stock Not Found")
public class StockNotFoundException extends RuntimeException {

}
