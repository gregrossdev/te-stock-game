import axios from 'axios';

const http = axios.create({
	baseURL: "http://localhost:8080/api/transactions/"
});

export default {

	// USE: ADMIN/TEST
	list() {
		return http.get("");
	},

	// USE: ADMIN/TEST
	getTransactionByTransactionId(transactionId) {
		return http.get(`${transactionId}`);
	},

	// USE: Data for YOUR TRANSACTION HISTORY in PortfolioDetails in ViewGame
	getTransactionsByPortfolioId(portfolioid) {
		return http.get(`portfolio/${portfolioid}`);
	},

	// USE: ADMIN/TEST
	getTransactionsByStockSymbol(stockSymbol) {
		return http.get(`stock/${stockSymbol}`);
	},

	// USE: Creates a transaction when a user buys or sells stocks.
	// Portfolio cash and total value are checked, adjusted, and calculated on the backend.
	create(transactionToCreate) {
		return http.post("", transactionToCreate);
	},

	// USE: ADMIN/TEST
	update(transactionToUpdate) {
		return http.put(`${transactionToUpdate.transactionId}`, transactionToUpdate);
	},

	// USE: ADMIN/TEST
	delete(transactionIdToDelete) {
		return http.delete(`${transactionIdToDelete}`);
	}

}