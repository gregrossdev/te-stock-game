package com.techelevator.dao;

import com.techelevator.model.game.Game;
import com.techelevator.model.game.GameNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcGameDao implements GameDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcGameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Game> getGames() {
        List<Game> games = new ArrayList<Game>();
        String sql = "SELECT * FROM games;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Game game = mapRowToGame(results);
            games.add(game);
        }
        return games;
    }

    @Override
    public Game getGameByGameId(Long gameId) {
        String sql = "SELECT * FROM games WHERE game_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, gameId);
        if (results.next()) {
            return mapRowToGame(results);
        }
        throw new GameNotFoundException();
    }

    @Override
    public List<Game> getGamesByUserId(Long userId) {
        List<Game> gamesByUserId = new ArrayList<>();
        String sql = "SELECT * FROM games JOIN portfolios ON games.game_id = portfolios.game_id WHERE portfolios.user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            Game game = mapRowToGame(results);
            gamesByUserId.add(game);
        }
        return gamesByUserId;
    }

    @Override
    public Game getGameByGameOrganizer(Long gameOrganizer) {
        String sql = "SELECT * from games WHERE game_organizer = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, gameOrganizer);
        if (results.next()) {
            return mapRowToGame(results);
        }
        throw new GameNotFoundException();
    }

    @Override
    public Game getGameByGameWinner(Long gameWinner) {
        String sql = "SELECT * from games WHERE game_winner = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, gameWinner);
        if (results.next()) {
            return mapRowToGame(results);
        }
        throw new GameNotFoundException();
    }

    @Override
    public Game create(Game gameToCreate) {
        String sql = "INSERT INTO games (game_name, game_organizer, start_timestamp, end_timestamp) " +
                "VALUES (?, ?, ?, ?); " +
                "INSERT INTO portfolios (user_id, game_id) " +
                "VALUES (?, (SELECT game_id FROM games WHERE game_organizer = ? AND start_timestamp = ? AND end_timestamp = ?));";
        if (jdbcTemplate.update(sql, gameToCreate.getGameName(), gameToCreate.getGameOrganizer(),
                gameToCreate.getStartTimestamp(), gameToCreate.getEndTimestamp(), gameToCreate.getGameOrganizer(),
                gameToCreate.getGameOrganizer(), gameToCreate.getStartTimestamp(), gameToCreate.getEndTimestamp()) == 1) {
            String newGameIdSql = "SELECT game_id FROM games WHERE game_organizer = ? AND start_timestamp = ? AND end_timestamp = ?;";
            Long newGameId = jdbcTemplate.queryForObject(newGameIdSql, Long.class, gameToCreate.getGameOrganizer(), gameToCreate.getStartTimestamp(), gameToCreate.getEndTimestamp());
            gameToCreate.setGameId(newGameId);
            return gameToCreate;
        };
        throw new GameNotFoundException();
    }

    @Override
    public boolean update(Game gameToUpdate) {
        String sql = "UPDATE games SET game_name = ?, game_organizer = ?, game_winner = ?, start_timestamp = ?, end_timestamp = ?, " +
                "game_status = ? WHERE game_id = ?;";
        return jdbcTemplate.update(sql, gameToUpdate.getGameName(), gameToUpdate.getGameOrganizer(), gameToUpdate.getGameWinner(),
                gameToUpdate.getStartTimestamp(), gameToUpdate.getEndTimestamp(), gameToUpdate.getGameStatus(),
                gameToUpdate.getGameId()) == 1;
    }

    @Override
    public boolean delete(Long gameIdToDelete) {
        String sql = "DELETE FROM games WHERE game_id = ?;";
        return jdbcTemplate.update(sql, gameIdToDelete) == 1;
    }

    private Game mapRowToGame(SqlRowSet results) {
        Game game = new Game();
        game.setGameId(results.getLong("game_id"));
        game.setGameName(results.getString("game_name"));
        game.setGameOrganizer(results.getLong("game_organizer"));
        game.setGameWinner(results.getLong("game_winner"));
        game.setStartTimestamp(results.getTimestamp("start_timestamp"));
        game.setEndTimestamp(results.getTimestamp("end_timestamp"));
        game.setGameStatus(results.getString("game_status"));
        return game;
    }
}
