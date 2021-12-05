package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/api/users/") // TODO double-check that this is the URL structure we want.
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping(path="", method = RequestMethod.GET)
    public List<User> getUsers() {
        return userDao.findAll();
    }

    @RequestMapping(path="{userId}", method = RequestMethod.GET)
    public User getUserByUserId(@PathVariable Long userId) {
        return userDao.getUserById(userId);
    }

    // TODO Add RequestMapping with username stored as a query parameter in the path.
    public User getUserIdByUsername(String username) {
        return userDao.findByUsername(username);
    }

    // TODO Add RequestMapping with username stored as a query parameter in the path.
    public User getUserByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
