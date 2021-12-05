package com.techelevator.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/api/games/") // TODO double-check that this is the URL structure we want.
public class GameController {

// TODO fill in, starting with basic CRUD methods.

}
