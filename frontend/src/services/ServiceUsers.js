import axios from 'axios';

const http = axios.create({
	baseURL: "http://localhost:8080/api/users/"
});

export default {

	list() {
		return http.get("");
	},

	getUserByUserId(userId) {
		return http.get(`userid/${userId}`);
	},

	getUserByUsername(username) {
		return http.get(`username/${username}`);
	}

}