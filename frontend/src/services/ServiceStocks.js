import axios from 'axios';

const http = axios.create({
	baseURL: "http://localhost:8080"
});

export default {

	list() {
		return http.get(`/api/stocks/`);
	},

	test(){
		return http.get('http://localhost:8080/api/stocks/test/endpoint');
	}

}