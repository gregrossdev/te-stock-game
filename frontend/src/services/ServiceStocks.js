import axios from 'axios';

const http = axios.create({
	baseURL: "http://localhost:8080/api/stocks/"
});

export default {

	// USE: Used regularly to update stocks in the store with the latest stock prices from the database
	list() {
		return http.get("");
	},

	// USE: ADMIN/TEST
	test(){
		return http.get('test/endpoint');
	},

	// USE: ADMIN/TEST
	getStockByStockSymbol(stockSymbol) {
		return http.get(`${stockSymbol}`);
	},

	// USE: ADMIN/TEST
	create(stockToCreate) {
		return http.post("", stockToCreate);
	},

	// USE: ADMIN/TEST
	update(stockToUpdate) {
		return http.put(`${stockToUpdate.stockSymbol}`, stockToUpdate);
	},

	// USE: ADMIN/TEST
	delete(stockSymbolToDelete) {
		return http.delete(`${stockSymbolToDelete}`);
	}
}