import axios from 'axios';

const http = axios.create({
	baseURL: "http://localhost:8080/api/users/"
});

export default {

	// USE: Data for INVITE USERS in GameNewForm
	// Allows user to see which other users can be invited to a game when the game is created
	list() {
		return http.get("");
	},

	// USE: ADMIN/TEST
	getUserByUserId(userId) {
		return http.get(`userid/${userId}`);
	},

	// USE: ADMIN/TEST
	getUserByUsername(username) {
		return http.get(`username/${username}`);
	}

}