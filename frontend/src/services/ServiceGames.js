import axios from "axios";

const http = axios.create({
  baseURL: "http://localhost:8080/api/games/",
});

export default {

  list() {
    return http.get("");
  },

  getGameByGameId(gameId) {
    return http.get(`${gameId}`);
  },

  getGamesByUserId(userId) {
    return http.get(`user/${userId}`);
  },

  getGameByGameOrganizer(gameOrganizer) {
    return http.get(`organizer/${gameOrganizer}`);
  },

  getGameByGameWinner(gameWinner) {
    return http.get(`winner/${gameWinner}`);
  },

  create(gameToCreate) {
    return http.post("", gameToCreate);
  },

  update(gameToUpdate) {
    return http.put(`${gameToUpdate.gameId}`, gameToUpdate);
  },

  delete(gameIdToDelete) {
    return http.delete(`${gameIdToDelete}`);
  }

};
