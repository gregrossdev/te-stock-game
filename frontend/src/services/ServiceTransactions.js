import axios from 'axios';

const http = axios.create({
	baseURL: "http://localhost:8080/api/transactions/"
});

export default {

	list() {
		return http.get("");
	}


// 	@Override
// 	public List<Transaction> getTransactions() {
// 	List<Transaction> transactions = new ArrayList<>();
// 	String sql = "SELECT * FROM transactions;";
// 	SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
// 	while (results.next()) {
// 		transactions.add(mapRowToTransaction(results));
// 	}
// 	return transactions;
// }
//
// @Override
// public Transaction getTransactionByTransactionId(Long transactionId) {
// 	String sql = "SELECT * FROM transactions WHERE transaction_id = ?;";
// 	SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transactionId);
// 	if (results.next()) {
// 		return mapRowToTransaction(results);
// 	}
// 	throw new TransactionNotFoundException();
// }
//
// @Override
// public List<Transaction> getTransactionsByPortfolioId(Long portfolioId) {
// 	List<Transaction> transactions = new ArrayList<>();
// 	String sql = "SELECT * FROM transactions WHERE portfolio_id = ?;";
// 	SqlRowSet results = jdbcTemplate.queryForRowSet(sql, portfolioId);
// 	while (results.next()) {
// 		transactions.add(mapRowToTransaction(results));
// 	}
// 	return transactions;
// }
//
// @Override
// public List<Transaction> getTransactionsByStockSymbol(String stockSymbol) {
// 	List<Transaction> transactions = new ArrayList<>();
// 	String sql = "SELECT * FROM transactions WHERE stock_symbol = ?;";
// 	SqlRowSet results = jdbcTemplate.queryForRowSet(sql, stockSymbol);
// 	while (results.next()) {
// 		transactions.add(mapRowToTransaction(results));
// 	}
// 	return transactions;
// }
//
// @Override
// public boolean create(Transaction transactionToCreate) {
// 	String sql = "INSERT INTO transactions (portfolio_id, stock_symbol, transaction_type, transaction_amount, " +
// 		"transaction_shares, share_price, transaction_timestamp, transaction_status, portfolio_balance, " +
// 		"portfolio_value) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
// 	return jdbcTemplate.update(sql, transactionToCreate.getPortfolioId(), transactionToCreate.getStockSymbol(),
// 		transactionToCreate.getTransactionType(), transactionToCreate.getTransactionAmount(),
// 		transactionToCreate.getTransactionShares(), transactionToCreate.getSharePrice(),
// 		transactionToCreate.getTransactionTimestamp(), transactionToCreate.getTransactionStatus(),
// 		transactionToCreate.getPortfolioBalance(), transactionToCreate.getPortfolioValue()) == 1;
// }
//
// @Override
// public boolean update(Transaction transactionToUpdate) {
// 	String sql =
// 		"UPDATE transactions SET portfolio_id = ?, stock_symbol = ?, transaction_type = ?, transaction_amount = ?, " +
// 		"transaction_shares = ?, share_price = ?, transaction_timestamp = ?, transaction_status = ?, portfolio_balance = ?, " +
// 		"portfolio_value = ? WHERE transaction_id = ?;";
// 	return jdbcTemplate.update(sql, transactionToUpdate.getPortfolioId(), transactionToUpdate.getStockSymbol(),
// 		transactionToUpdate.getTransactionType(), transactionToUpdate.getTransactionAmount(),
// 		transactionToUpdate.getTransactionShares(), transactionToUpdate.getSharePrice(),
// 		transactionToUpdate.getTransactionTimestamp(), transactionToUpdate.getTransactionStatus(),
// 		transactionToUpdate.getPortfolioBalance(), transactionToUpdate.getPortfolioValue(),
// 		transactionToUpdate.getTransactionId()) == 1;
// }
//
// @Override
// public boolean delete(Long transactionIdToDelete) {
// 	String sql = "DELETE FROM transactions WHERE transaction_id = ?;";
// 	return jdbcTemplate.update(sql, transactionIdToDelete) == 1;
// }

}