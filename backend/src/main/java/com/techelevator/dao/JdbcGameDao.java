package com.techelevator.dao;

import com.techelevator.model.game.Game;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcGameDao implements GameDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcGameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // TODO Fill in all methods below.

    @Override
    public List<Game> getGames() {
        return null;
    }

    @Override
    public Game getGameByGameId(Long gameId) {
        return null;
    }

    @Override
    public Game getGameByGameOrganizer(Long gameOrganizer) {
        return null;
    }

    @Override
    public Game getGameByGameWinner(Long gameWinner) {
        return null;
    }

    @Override
    public boolean create(Game gameToCreate) {
        return false;
    }

    @Override
    public boolean update(Game gameToUpdate) {
        return false;
    }

    @Override
    public boolean delete(Long gameIdToDelete) {
        return false;
    }

    private Game mapRowToGame(SqlRowSet results) {
        return null;
    }
}
