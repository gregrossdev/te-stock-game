import axios from 'axios';

const http = axios.create({
	baseURL: "http://localhost:8080/api/transactions/"
});

export default {

	list() {
		return http.get("");
	}

// 	@RequestMapping(path="", method = RequestMethod.GET)
// 	public List<Transaction> getTransactions() {
// 	return transactionDao.getTransactions();
// }
//
// @RequestMapping(path="{transactionId}", method = RequestMethod.GET)
// public Transaction getTransactionByTransactionId(@PathVariable Long transactionId) {
// 	return transactionDao.getTransactionByTransactionId(transactionId);
// }
//
// @RequestMapping(path="portfolio/{portfolioId}", method = RequestMethod.GET)
// public List<Transaction> getTransactionsByPortfolioId(@PathVariable Long portfolioId) {
// 	return transactionDao.getTransactionsByPortfolioId(portfolioId);
// }
//
// @RequestMapping(path="stock/{stockSymbol}", method = RequestMethod.GET)
// public List<Transaction> getTransactionsByStockSymbol(@PathVariable String stockSymbol) {
// 	return transactionDao.getTransactionsByStockSymbol(stockSymbol);
// }
//
// @RequestMapping(path="", method = RequestMethod.POST)
// public boolean create(@RequestBody Transaction transactionToCreate) {
// 	return transactionDao.create(transactionToCreate);
// }
//
// @RequestMapping(path="{transactionId}", method = RequestMethod.PUT)
// public boolean update(@RequestBody Transaction transactionToUpdate) {
// 	return transactionDao.update(transactionToUpdate);
// }
//
// @RequestMapping(path="{transactionIdToDelete}", method = RequestMethod.DELETE)
// public boolean delete(@PathVariable Long transactionIdToDelete) {
// 	return transactionDao.delete(transactionIdToDelete);
// }

}