import axios from 'axios';

const http = axios.create({
	baseURL: "http://localhost:8080/api/portfolios/"
});

export default {

	list() {
		return http.get("");
	},

	getPortfolioByPortfolioId(portfolioId) {
		return http.get(`${portfolioId}`);
	},

	getPortfoliosByUserId(userId) {
		return http.get(`user/${userId}`);
	},

	// USE: Data for LEADERBOARD inside ViewGame
	getPortfoliosByGameId(gameId) {
		return http.get(`game/${gameId}`);
	},

	// USE: Data for ACTIVE PORTFOLIO once ACTIVE GAME ID is known
	getPortfolioByUserIdAndGameId(userId, gameId) {
		return http.get(`user/${userId}/game/${gameId}`);
	},

	// USE: Data for YOUR INVESTMENTS in PortfolioDetails in ViewGame
	getPortfolioStocksByPortfolioId(portfolioId) {
		return http.get(`portfolio-stocks/${portfolioId}`);
	},

	// USE: Creates portfolio with ACTIVE status for game organizer when game is created
	// USE: Creates portfolio with PENDING status for user once they're invited to a game
	create(portfolioToCreate) {
		return http.post("", portfolioToCreate);
	},

	// USE: Updates portfolio status from PENDING to ACTIVE once a user accepts a pending invitation to a game
	update(portfolioToUpdate) {
		return http.put(`${portfolioToUpdate.portfolioId}`, portfolioToUpdate);
	},

	// USE: Deletes portfolio once a user rejects a pending invitation to a game
	delete(portfolioIdToDelete) {
		return http.delete(`${portfolioIdToDelete}`);
	},

	//USE: Gets all pending portfolios from a userID
	getPendingPortfoliosByUserId(userId) {
		return http.get(`user/pending/${userId}`);
	}
}


/*
@RequestMapping(path = "user/pending/{userId}", method = RequestMethod.GET)
    public List<Portfolio> getPendingPortfoliosByUserId(@PathVariable Long userId) {
        return portfolioDao.getPendingPortfoliosByUserId(userId);
    }
*/