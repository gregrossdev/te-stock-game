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

	getPortfoliosByGameId(gameId) {
		return http.get(`game/${gameId}`);
	},

	getPortfolioByUserIdAndGameId(userId, gameId) {
		return http.get(`user/${userId}/game/${gameId}`);
	},

	create(portfolioToCreate) {
		return http.post("", portfolioToCreate);
	},

	update(portfolioToUpdate) {
		return http.put(`${portfolioToUpdate.portfolioId}`, portfolioToUpdate);
	},

	delete(portfolioIdToDelete) {
		return http.delete(`${portfolioIdToDelete}`);
	}

}