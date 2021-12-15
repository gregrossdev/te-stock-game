package com.techelevator.dao;

import com.techelevator.model.game.Game;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class JdbcGameDaoTests extends FinalCapstoneDaoTests {

    private JdbcGameDao sut;

    @Before
    public void setup() {
        DataSource dataSource = this.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcGameDao(jdbcTemplate);
    }

    @Test
    public void get_games_returns_list_of_games() {
        List<Game> gamesListFromDB = sut.getGames();
        Game gameFromGamesList = gamesListFromDB.get(0); //Should be gameID1
        long gameIdOne = gameFromGamesList.getGameId();

        Assert.assertEquals(1L, gameIdOne);
    }

    @Test
    public void get_game_by_game_id() {
        Game thisGamesIdShouldBeOne = sut.getGameByGameId(1L);

        Long objectLongOne = 1L;
        Assert.assertEquals(objectLongOne, thisGamesIdShouldBeOne.getGameId());

    }
}
