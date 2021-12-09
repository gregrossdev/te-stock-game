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

// 	@RequestMapping(path="", method = RequestMethod.GET)
// 	public List<StockWrapper> getStocks() {
// 	return stockDao.getStocks();
// }
//
// @RequestMapping(path="{stockSymbol}", method = RequestMethod.GET)
// public StockWrapper getStockByStockSymbol(@PathVariable String stockSymbol) {
// 	return stockDao.getStockByStockSymbol(stockSymbol);
// }
//
// @ResponseStatus(HttpStatus.CREATED)
// @RequestMapping(path="", method = RequestMethod.POST)
// public boolean create(StockWrapper stockWrapperToCreate) {
// 	return stockDao.create(stockWrapperToCreate);
// }
//
// @RequestMapping(path="{stockSymbol}", method = RequestMethod.PUT)
// public boolean update(@RequestBody StockWrapper stockWrapperToUpdate) {
// 	return stockDao.update(stockWrapperToUpdate);
// }
//
// @RequestMapping(path="{stockSymbolToDelete}", method = RequestMethod.DELETE)
// public boolean delete(@PathVariable String stockSymbolToDelete) {
// 	return stockDao.delete(stockSymbolToDelete);
// }

}