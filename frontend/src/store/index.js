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

        // USE: this.$store.state.user.id is the CURRENT USER'S USERID.
        // Set upon user logging in successfully.
        user: currentUser || {},

        // USE: Stores all users so that a user can select which users to invite to a game.
        // Set when View Profile renders after login.
        users: [],

        // USE: Stores all games, ACTIVE, with PENDING INVITES, and ARCHIVED that the CURRENT USER is a participant in.
        // Set when View Profile renders after login.
        games: [],

        // USE: NEVER USED? Stores all portfolios that a user is a part of, across games.
        // userPortfolios: [],

        // USE: Stores all pending portfolios, representing GAMES that a USER has been INVITED TO.
        // Set when View Profile renders after login.
        pendingPortfolios: [],

        // USE: Stores all user/portfolios for a particular game, so that LEADERBOARD can be displayed.
        // Set when View Game renders after clicking on a Game Card.
        gamePortfolios: [],

        // USE: Stores most recent stock prices from the database.
        // Set when View Profile renders after login.
        stocks: [],

        // USE: Stores all stocks, as well as total shares, that a particular portfolio is invested in.
        // Set when View Game renders after clicking on a Game Card.
        portfolioStocks: [],

        // USE: Displays transaction history for active user in a particular portfolio/game.
        // Set when View Game renders after clicking on a Game Card.
        transactions: [],

        // USE: Keeps track of active game in order to correctly populate View Game and its child components.
        // Set when View Game renders after clicking on a Game Card.
        activeGame: {
            gameId: 0,
            gameOrganizer: 0,
            gameWinner: 0,
            startTimestamp: "",
            endTimestamp: "",
            gameStatus: ""
        },

        // USE: Keeps track of active user's active portfolio for a particular game, so that View Game and its child components render correctly.
        // Set when View Game renders after clicking on a Game Card.
        activePortfolio: {
            portfolioId: 0,
            userId: 0,
            gameId: 0,
            portfolioCash: 0,
            portfolioStocksValue: 0,
            portfolioTotalValue: 0,
            portfolioStatus: ""
        },
        archivedPortfolio: {
            portfolioId: 0,
            userId: 0,
            gameId: 0,
            portfolioCash: 0,
            portfolioStocksValue: 0,
            portfolioTotalValue: 0,
            portfolioStatus: ""
        },
        
    },
    getters: {
        // USE: Keeps track of which games in games[] above are still PENDING invites.
        inactiveGameIds: state => {
            let inactiveGameIdsArray = [];
            state.pendingPortfolios.forEach(portfolio => {
                inactiveGameIdsArray.push(portfolio.gameId);
            })
            return inactiveGameIdsArray;
        },
        // USE: Filters games[] for Games List so that only ACTIVE games are shown.
        activeGames: (state, getters) => {
            return state.games.filter(item => !(getters.inactiveGameIds.includes(item.gameId)) && item.gameStatus === "ACTIVE");
        },
        archivedGames: (state, getters) => {
            return state.games.filter(item => !(getters.inactiveGameIds.includes(item.gameId)) && item.gameStatus === "ARCHIVED");
        },
        expiredGames: (state) => {
            return state.games.filter(item => Date.parse(item.endTimestamp) < Date());
        },
        gamesUserHasWon: (state) => {
            return state.games.filter(item => item.gameWinner === this.state.user.id);
        },
        // USE: Filters gamePortfolios[] in reverse order by total portfolio value, to correctly populate Leaderboard in ViewGame.
        gamePortfoliosSortedByLeader: (state) => {
            return state.gamePortfolios.sort((a, b) => b.portfolioTotalValue - a.portfolioTotalValue);
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
        // SET_USER_PORTFOLIOS(state, data) {
        //     state.userPortfolios = data;
        // },
        SET_GAME_PORTFOLIOS(state, data) {
            state.gamePortfolios = data;
        },
        SET_ACTIVE_PORTFOLIO(state, data) {
            state.activePortfolio = data;
        },
        SET_ACTIVE_PORTFOLIO_STOCKS(state, data) {
            state.portfolioStocks = data;
        },
        /*
         * Transactions
         */
        SET_TRANSACTIONS(state, data) {
            state.transactions = data;
        },
        /*
         * Pending Portfolios
         */
        SET_PENDING_PORTFOLIOS(state, data) {
            state.pendingPortfolios = data;
        }
    },
});
