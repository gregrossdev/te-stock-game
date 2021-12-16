package com.techelevator.dao;

import com.techelevator.model.game.Game;

import java.util.List;

public interface GameDao {

    List<Game> getGames();

    List<Game> gamesWithoutWinnersYet();

    Game getGameByGameId(Long gameId);

    List<Game> getGamesByUserId(Long userId);

    Game getGameByGameOrganizer(Long gameOrganizer);

    Game getGameByGameWinner(Long gameWinner);

    Game create(Game gameToCreate);

    boolean update(Game gameToUpdate);

    //update when a game ends
    boolean updateGameEnd(Long gameId, Long winnerId);

    boolean delete(Long gameIdToDelete);

}
