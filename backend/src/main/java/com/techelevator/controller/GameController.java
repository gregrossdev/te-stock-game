package com.techelevator.controller;

import com.techelevator.dao.GameDao;
import com.techelevator.model.game.Game;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
//@PreAuthorize("isAuthenticated()")
@RequestMapping("/api/games/")

public class GameController {
    private final GameDao gameDao;

    public GameController(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    // Use: ADMIN/TEST only
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Game> getGames() {
        return gameDao.getGames();
    }

    // USE: View current/active Game.
    @RequestMapping(path = "{gameId}", method = RequestMethod.GET)
    public Game getGameByGameId(@PathVariable Long gameId) {
        return gameDao.getGameByGameId(gameId);
    }

    // USE: View all Games for current/active User.
    @RequestMapping(path="user/{userId}", method = RequestMethod.GET)
    public List<Game> getGamesByUserId(@PathVariable Long userId) {
        return gameDao.getGamesByUserId(userId);
    }

    // USE: ADMIN/TEST only
    @RequestMapping(path = "organizer/{gameOrganizer}", method = RequestMethod.GET)
    public Game getGameByGameOrganizer(@PathVariable Long gameOrganizer) {
        return gameDao.getGameByGameOrganizer(gameOrganizer);
    }

    // USE: View which/how many Games a User has won.
    @RequestMapping(path = "winner/{gameWinner}", method = RequestMethod.GET)
    public Game getGameByGameWinner(@PathVariable Long gameWinner) {
        return gameDao.getGameByGameWinner(gameWinner);
    }

    // USE: User can create a Game.
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public boolean create(@RequestBody Game gameToCreate) {
        return gameDao.create(gameToCreate);
    }

    // USE: At Game end, the game winner is logged.
    @RequestMapping(path = "{gameId}", method = RequestMethod.PUT)
    public boolean update(@RequestBody Game gameToUpdate) {
        return gameDao.update(gameToUpdate);
    }

    // USE: ADMIN/TEST only
    @RequestMapping(path = "{gameIdToDelete}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable Long gameIdToDelete) {
        return gameDao.delete(gameIdToDelete);
    }
}
