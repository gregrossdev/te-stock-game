import axios from 'axios';

const http = axios.create({
	baseURL: "http://localhost:8080/api/users/"
});

export default {

	list() {
		return http.get("");
	}

// 	@RequestMapping(path="", method = RequestMethod.GET)
// 	public List<User> getUsers() {
// 	return userDao.findAll();
// }
//
// @RequestMapping(path="userid/{userId}", method = RequestMethod.GET)
// public User getUserByUserId(@PathVariable Long userId) {
// 	return userDao.getUserById(userId);
// }
//
// @RequestMapping(path="username/{username}", method = RequestMethod.GET)
// public User getUserByUsername(@PathVariable String username) {
// 	return userDao.findByUsername(username);
// }

}