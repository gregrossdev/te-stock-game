import axios from 'axios';

const http = axios.create({
	baseURL: "http://localhost:8080/api/stocks/"
});

export default {

	list() {
		return http.get("");
	},

	test(){
		return http.get('test/endpoint');
	},

	getStockByStockSymbol(stockSymbol) {
		return http.get(`${stockSymbol}`);
	},

	create(stockToCreate) {
		return http.post("", stockToCreate);
	},

	update(stockToUpdate) {
		return http.put(`${stockToUpdate.stockSymbol}`, stockToUpdate);
	},

	delete(stockSymbolToDelete) {
		return http.delete(`${stockSymbolToDelete}`);
	}
}