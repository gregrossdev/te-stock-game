package com.techelevator.dao;

import com.techelevator.model.game.Game;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
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
        while(results.next()) {
            Game game = mapRowToGame(results);
            games.add(game);
        }
        return games;
    }

    @Override
    public Game getGameByGameId(Long gameId) {
        String sql = "SELECT * FROM games WHERE game_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, gameId);
        return mapRowToGame(results);
    }

    @Override
    public Game getGameByGameOrganizer(Long gameOrganizer) {
        String sql = "SELECT * from games WHERE game_organizer = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, gameOrganizer);
        return mapRowToGame(results);
    }

    @Override
    public Game getGameByGameWinner(Long gameWinner) {
        String sql = "SELECT * from games WHERE game_winner = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, gameWinner);
        return mapRowToGame(results);
    }

    @Override
    public boolean create(Game gameToCreate) {
        String sql = "INSERT INTO games (game_organizer, game_winner, start_datetime, end_datetime, game_status) " +
                "VALUES (?, ?, ?, ?, ?);";
        return jdbcTemplate.update(sql, gameToCreate.getGameOrganizer(), gameToCreate.getGameWinner(),
                gameToCreate.getStartDatetime(), gameToCreate.getEndDatetime(), gameToCreate.getGameStatus()) == 1;
    }

    @Override
    public boolean update(Game gameToUpdate) {
        String sql = "UPDATE games SET game_organizer = ?, game_winner = ?, start_datetime = ?, end_datetime = ?, " +
                "game_status = ? WHERE game_id = ?;";
        return jdbcTemplate.update(sql, gameToUpdate.getGameOrganizer(), gameToUpdate.getGameWinner(),
                gameToUpdate.getStartDatetime(), gameToUpdate.getEndDatetime(), gameToUpdate.getGameStatus(),
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
        game.setGameOrganizer(results.getLong("game_organizer"));
        game.setGameWinner(results.getLong("game_winner"));
        game.setStartDatetime(results.getObject("start_datetime", LocalDateTime.class));
        game.setEndDatetime(results.getObject("end_datetime", LocalDateTime.class));
        game.setGameStatus(results.getString("game_status"));
        return game;
    }
}
