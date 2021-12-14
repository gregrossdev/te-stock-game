import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";

Vue.use(Vuex);

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem("token");
const currentUser = JSON.parse(localStorage.getItem("user"));

if (currentToken != null) {
  axios.defaults.headers.common["Authorization"] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || "",
    user: currentUser || {},
    users: [], 
    games: [],
    userPortfolios: [],
    gamePortfolios: [],
    stocks: [],
    transactions: [],
    activeGame: {
      gameId: 1,
      gameOrganizer: 2,
      gameStartTimestamp: null,
      gameEndTimestamp: null
    },
    activePortfolio: {
      portfolioId: 1,
      userId: 2,
      gameId: 1,
      portfolioCash: null,
      portfolioStocksValue: null,
      portfolioTotalValue: null
      // portfolioStocks: []
    }
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem("token", token);
      axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem("user", JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem("token");
      localStorage.removeItem("user");
      state.token = "";
      state.user = {};
      axios.defaults.headers.common = {};
    },
    /*
     * Users
     */
    SET_USERS(state, data) {
      state.users = data;
    },
    /*
     * Stocks
     */
    SET_STOCKS(state, data) {
      state.stocks = data;
    },
    /*
     * GAMES
     */
    SET_GAMES(state, data) {
      state.games = data;
    },
    SAVE_GAME(state, game) {
      state.games.push(game);
    },
    SET_ACTIVE_GAME(state, data) {
      state.activeGame = data;
    },
    /*
     * Portfolios
     */
    SET_USER_PORTFOLIOS(state, data) {
      state.userPortfolios = data;
    },
    SET_GAME_PORTFOLIOS(state, data) {
      state.gamePortfolios = data;
    },
    SET_ACTIVE_PORTFOLIO(state, data) {
      state.activePortfolio = data;
    },
    /*
     * Transactions
     */
    SET_TRANSACTIONS(state, data) {
      state.transactions = data;
    },
    
  },
});
