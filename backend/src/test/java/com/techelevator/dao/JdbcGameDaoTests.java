package com.techelevator.dao;

import com.techelevator.model.game.Game;
import com.techelevator.model.game.GameNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.platform.commons.function.Try;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Timestamp;
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

    @Test
    public void get_game_by_game_invalid_game_id_throws() {
        boolean thrown = false;

        try{
            Game gameIDThatDoesntExistToThrow = sut.getGameByGameId(0L);
        } catch (GameNotFoundException e) {
            thrown = true;
        }

        Assert.assertTrue(thrown);
    }

    @Test
    public void get_game_by_user_id_returns_a_not_null_game(){

        List<Game> aReturnedGameListFromUserId = sut.getGamesByUserId(2L);
//        List<Game> aReturnedGameListFromUserId = sut.getGamesByUserId(999L); //this userid doesnt exist

        Game specificGameFromThatList = aReturnedGameListFromUserId.get(0);

        //A game from a game list using getGamesByUserId should return a game id not null.
        Assert.assertNotNull(specificGameFromThatList);
    }

    @Test
    public void get_game_by_game_organizer_returns_a_not_null_game(){

        Game aReturnedGameListFromGameOrganizer = sut.getGameByGameOrganizer(2L);

        Assert.assertNotNull(aReturnedGameListFromGameOrganizer);

    }

    @Test
    public void get_game_by_game_organizer_throws_with_invalid_organizer(){
        boolean thrown = false;

        try{
            Game aReturnedGameListFromGameOrganizer = sut.getGameByGameOrganizer(80085L);
        } catch (GameNotFoundException e){
            thrown = true;
        }

        Assert.assertTrue(thrown);
    }


    // TODO REWRITE TEST, NOW THAT CREATE GAME RETURNS A GAME OBJECT
//    //TODO throwing sql error
//    @Test
//    public void create_game_returns_true(){
//        List<Game> gamesListBeforeCreatingNewGame = sut.getGames();
//
//        Game specificGameFromThatList = gamesListBeforeCreatingNewGame.get(0);
//        //2021-14-11T17:00:00.000+00:00
//        Game newGameToAddToIncreaseSize = new Game(null, 2L, null, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()),null);
//
//        boolean shouldBeTrue = sut.create(newGameToAddToIncreaseSize);
//
//        Assert.assertTrue(shouldBeTrue);
//    }

}
