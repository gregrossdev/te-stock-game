import axios from 'axios';

const http = axios.create({
	baseURL: "http://localhost:8080/api/portfolios/"
});

export default {

	list() {
		return http.get("");
	}

// 	@Override
// 	public List<Portfolio> getPortfolios() {
// 	List<Portfolio> portfolios = new ArrayList<>();
// 	String sql = "SELECT * FROM portfolios;";
// 	SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
// 	while (results.next()) {
// 		portfolios.add(mapRowToPortfolio(results));
// 	}
// 	return portfolios;
// }
//
// @Override
// public Portfolio getPortfolioByPortfolioId(Long portfolioId) {
// 	String sql = "SELECT * FROM portfolios WHERE portfolio_id =?;";
// 	SqlRowSet results = jdbcTemplate.queryForRowSet(sql, portfolioId);
// 	if (results.next()) {
// 		return mapRowToPortfolio(results);
// 	}
// 	throw new PortfolioNotFoundException();
// }
//
// @Override
// public List<Portfolio> getPortfoliosByUserId(Long userId) {
// 	List<Portfolio> portfolios = new ArrayList<>();
// 	String sql = "SELECT * FROM portfolios WHERE user_id = ?;";
// 	SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
// 	while (results.next()) {
// 		portfolios.add(mapRowToPortfolio(results));
// 	}
// 	return portfolios;
// }
//
// @Override
// public List<Portfolio> getPortfoliosByGameId(Long gameId) {
// 	List<Portfolio> portfolios = new ArrayList<>();
// 	String sql = "SELECT * FROM portfolios WHERE game_id = ?;";
// 	SqlRowSet results = jdbcTemplate.queryForRowSet(sql, gameId);
// 	while (results.next()) {
// 		portfolios.add(mapRowToPortfolio(results));
// 	}
// 	return portfolios;
// }
//
// @Override
// public boolean create(Portfolio portfolioToCreate) {
// 	String sql = "INSERT INTO portfolios (user_id, game_id, portfolio_balance, portfolio_value, portfolio_status) " +
// 		"VALUES (?, ?, ?, ?, ?);";
// 	return jdbcTemplate.update(sql, portfolioToCreate.getUserId(), portfolioToCreate.getGameId(),
// 		portfolioToCreate.getPortfolioBalance(), portfolioToCreate.getPortfolioValue(),
// 		portfolioToCreate.getPortfolioStatus()) == 1;
// }
//
// @Override
// public boolean update(Portfolio portfolioToUpdate) {
// 	String sql = "UPDATE portfolios SET user_id = ?, game_id = ?, portfolio_balance = ?, portfolio_value = ?, " +
// 		"portfolio_status = ? WHERE portfolio_id =?;";
// 	return jdbcTemplate.update(sql, portfolioToUpdate.getUserId(), portfolioToUpdate.getGameId(),
// 		portfolioToUpdate.getPortfolioBalance(), portfolioToUpdate.getPortfolioValue(),
// 		portfolioToUpdate.getPortfolioStatus()) == 1;
// }
//
// @Override
// public boolean delete(Long portfolioIdToDelete) {
// 	String sql = "DELETE FROM portfolios WHERE portfolio_id = ?;";
// 	return jdbcTemplate.update(sql, portfolioIdToDelete) == 1;
// }

}