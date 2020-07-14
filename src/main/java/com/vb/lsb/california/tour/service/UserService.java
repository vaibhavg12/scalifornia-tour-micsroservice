package com.vb.lsb.california.tour.service;

import com.vb.lsb.california.tour.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    /**
     * Sign in a user into the application, with JWT-enabled authentication
     *
     * @param username username
     * @param password password
     * @return Optional of the Java Web Token, empty otherwise
     */
    Optional<String> signin(String username, String password);

    /**
     * Create a new user in the database.
     *
     * @param username  username
     * @param password  password
     * @param firstName first name
     * @param lastName  last name
     * @return Optional of user, empty if the user already exists.
     */
    Optional<User> signup(String username, String password, String firstName, String lastName);

    List<User> getAll();
}
