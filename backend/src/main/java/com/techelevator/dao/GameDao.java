package com.techelevator.dao;

import com.techelevator.model.game.Game;

import java.util.List;

public interface GameDao {

    List<Game> getGames();

    Game getGameByGameId(Long gameId);

    List<Game> getGamesByUserId(Long userId);

    Game getGameByGameOrganizer(Long gameOrganizer);

    Game getGameByGameWinner(Long gameWinner);

    boolean create(Game gameToCreate);

    boolean update(Game gameToUpdate);

    boolean delete(Long gameIdToDelete);

}
