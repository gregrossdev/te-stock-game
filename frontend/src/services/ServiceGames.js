import axios from "axios";

const http = axios.create({
  baseURL: "http://localhost:8080/api/games/",
});

export default {

  // Use: ADMIN/TEST only
  list() {
    return http.get("");
  },

  // USE: View current/active Game.
  getGameByGameId(gameId) {
    return http.get(`${gameId}`);
  },

  // USE: View all Games for current/active User.
  getGamesByUserId(userId) {
    return http.get(`user/${userId}`);
  },

  // USE: ADMIN/TEST only
  getGameByGameOrganizer(gameOrganizer) {
    return http.get(`organizer/${gameOrganizer}`);
  },

  // USE: View which/how many Games a User has won.
  getGameByGameWinner(gameWinner) {
    return http.get(`winner/${gameWinner}`);
  },

  // USE: User can create a Game.
  create(gameToCreate) {
    return http.post("", gameToCreate);
  },

  // USE: At Game end, the game winner is added to the Game. Game status is set to 'ARCHIVED'.
  update(gameToUpdate) {
    return http.put(`${gameToUpdate.gameId}`, gameToUpdate);
  },

  //USE: when game ends

  // USE: ADMIN/TEST only. Game status is set to 'ARCHIVED' rather than deleting a Game after it's finished. That way, we can look up which User won which games.
  delete(gameIdToDelete) {
    return http.delete(`${gameIdToDelete}`);
  }

};
