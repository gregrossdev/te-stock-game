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
	}

// 	@Override
// 	public List<StockWrapper> getStocks() {
// 	List<StockWrapper> stockWrappers = new ArrayList<>();
// 	String sql = "SELECT * FROM stocks;";
// 	SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
// 	while (results.next()) {
// 		stockWrappers.add(mapRowToStock(results));
// 	}
// 	return stockWrappers;
// }
//
//
// @Override
// public StockWrapper getStockByStockSymbol(String stockSymbol) {
// 	String sql = "SELECT * FROM stocks WHERE stock_symbol = ?;";
// 	SqlRowSet results = jdbcTemplate.queryForRowSet(sql, stockSymbol);
// 	if (results.next()) {
// 		return mapRowToStock(results);
// 	}
// 	throw new StockNotFoundException();
// }
//
// @Override
// public boolean create(StockWrapper stockWrapperToCreate) {
// 	String sql = "INSERT INTO stocks (stock_symbol, share_price, quote_timestamp) VALUES (?, ?, ?);";
// 	return jdbcTemplate.update(sql, stockWrapperToCreate.getStockSymbol(), stockWrapperToCreate.getSharePrice(),
// 		stockWrapperToCreate.getQuoteTimestamp()) == 1;
// }
//
// @Override
// public boolean update(StockWrapper stockWrapperToUpdate) {
// 	String sql = "UPDATE stocks SET share_price = ?, quote_timestamp = ? WHERE stock_symbol = ?;";
// 	return jdbcTemplate.update(sql, stockWrapperToUpdate.getSharePrice(), stockWrapperToUpdate.getQuoteTimestamp(),
// 		stockWrapperToUpdate.getStockSymbol()) == 1;
// }
//
// @Override
// public boolean delete(String stockSymbolToDelete) {
// 	String sql = "DELETE FROM stocks WHERE stock_symbol = ?;";
// 	return jdbcTemplate.update(sql, stockSymbolToDelete) == 1;
// }
// public boolean testMethodUpdatePrice(BigDecimal priceChange){
// 	String sql = "UPDATE stocks SET share_price = ? WHERE stock_symbol = 'SPCE';";
// 	return jdbcTemplate.update(sql, priceChange) == 1; //return true if works?
// }
// public boolean testMethodUpdatePriceTwo(BigDecimal price, String ticker){
// 	String sql = "UPDATE stocks SET share_price = ? WHERE stock_symbol = ?;";
// 	return jdbcTemplate.update(sql, price, ticker) == 1; //return true if works?
// }

}