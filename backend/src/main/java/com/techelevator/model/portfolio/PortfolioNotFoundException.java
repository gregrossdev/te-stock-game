package com.techelevator.model.portfolio;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Portfolio Not Found")
public class PortfolioNotFoundException extends RuntimeException {
}
