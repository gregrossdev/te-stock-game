import axios from 'axios';

const http = axios.create({
	baseURL: "http://localhost:8080/api/users/"
});

export default {

	list() {
		return http.get("");
	}

// 	@Override
// 	public int findIdByUsername(String username) {
// 	return jdbcTemplate.queryForObject("select user_id from users where username = ?", int.class, username);
// }
//
// @Override
// public User getUserById(Long userId) {
// 	String sql = "SELECT * FROM users WHERE user_id = ?";
// 	SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
// 	if(results.next()) {
// 		return mapRowToUser(results);
// 	} else {
// 		throw new RuntimeException("userId "+userId+" was not found.");
// 	}
// }
//
// @Override
// public List<User> findAll() {
// 	List<User> users = new ArrayList<>();
// 	String sql = "select * from users";
//
// 	SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
// 	while(results.next()) {
// 		User user = mapRowToUser(results);
// 		users.add(user);
// 	}
//
// 	return users;
// }
//
// @Override
// public User findByUsername(String username) throws UsernameNotFoundException {
// 	for (User user : this.findAll()) {
// 		if( user.getUsername().toLowerCase().equals(username.toLowerCase())) {
// 			return user;
// 		}
// 	}
// 	throw new UsernameNotFoundException("User " + username + " was not found.");
// }
//
// @Override
// public boolean create(String username, String password, String role) {
// 	boolean userCreated = false;
//
// 	// create user
// 	String insertUser = "insert into users (username,password_hash,role) values(?,?,?)";
// 	String password_hash = new BCryptPasswordEncoder().encode(password);
// 	String ssRole = "ROLE_" + role.toUpperCase();
//
// 	GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
// 	String id_column = "user_id";
// 	userCreated = jdbcTemplate.update(con -> {
// 			PreparedStatement ps = con.prepareStatement(insertUser, new String[]{id_column});
// 			ps.setString(1, username);
// 			ps.setString(2, password_hash);
// 			ps.setString(3, ssRole);
// 			return ps;
// 		}
// 		, keyHolder) == 1;
// 	int newUserId = (int) keyHolder.getKeys().get(id_column);
//
// 	return userCreated;
// }

}