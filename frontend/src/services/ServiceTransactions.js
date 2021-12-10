import axios from 'axios';

const http = axios.create({
	baseURL: "http://localhost:8080/api/transactions/"
});

export default {

	list() {
		return http.get("");
	},

	getTransactionByTransactionId(transactionId) {
		return http.get(`${transactionId}`);
	},

	getTransactionsByPortfolioId(portfolioid) {
		return http.get(`portfolio/${portfolioid}`);
	},

	getTransactionsByStockSymbol(stockSymbol) {
		return http.get(`stock/${stockSymbol}`);
	},

	create(transactionToCreate) {
		return http.post("", transactionToCreate);
	},

	update(transactionToUpdate) {
		return http.put(`${transactionToUpdate.transactionId}`, transactionToUpdate);
	},

	delete(transactionIdToDelete) {
		return http.delete(`${transactionIdToDelete}`);
	}

}