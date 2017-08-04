package net.codinginaction.mp.sbrestapi.service;

import java.util.Collection;

import net.codinginaction.mp.sbrestapi.data.User;
import net.codinginaction.mp.sbrestapi.data.UserFilter;

public interface UserService {

	Collection<User> getUserList(UserFilter request);

	User getUserById(Long id);

	void createUser(User user);

	User updateUser(Long id, User user);

	void deleteUserById(Long id);

	User getUserByUsername(String username);

}
