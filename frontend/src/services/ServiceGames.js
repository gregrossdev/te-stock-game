import axios from "axios";

const http = axios.create({
  baseURL: "http://localhost:8080",
});

export default {

	
  list() {
    return http.get(`/api/games/`);
  },

  getGameByGameId(gameId) {
    return http.get(`${gameId}`);
  },


};

//
// @RequestMapping(path = "organizer/{gameOrganizer}", method = RequestMethod.GET)
// public Game getGameByGameOrganizer(@PathVariable Long gameOrganizer) {
//     return gameDao.getGameByGameOrganizer(gameOrganizer);
// }
//
// @RequestMapping(path="winner/{gameWinner}", method = RequestMethod.GET)
// public Game getGameByGameWinner(@PathVariable Long gameWinner) {
//     return gameDao.getGameByGameWinner(gameWinner);
// }
//
// @ResponseStatus(HttpStatus.CREATED)
// @RequestMapping(path="", method = RequestMethod.POST)
// public boolean create(@RequestBody Game gameToCreate) {
//     return gameDao.create(gameToCreate);
// }
//
// // TODO Check that this is correct, using the gameId as a path variable even when it's not used in the method.
// @RequestMapping(path="{gameId}", method = RequestMethod.PUT)
// public boolean update(@RequestBody Game gameToUpdate) {
//     return gameDao.update(gameToUpdate);
// }
//
// @RequestMapping(path = "{gameIdToDelete}", method = RequestMethod.DELETE)
// public boolean delete(@PathVariable Long gameIdToDelete) {
//     return gameDao.delete(gameIdToDelete);
// }
// }
