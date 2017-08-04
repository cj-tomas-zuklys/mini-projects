package net.codinginaction.mp.sbrestapi.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;
import net.codinginaction.mp.sbrestapi.data.User;
import net.codinginaction.mp.sbrestapi.data.UserFilter;
import net.codinginaction.mp.sbrestapi.exception.UserNotFoundException;
import net.codinginaction.mp.sbrestapi.service.UserService;
import net.codinginaction.mp.sbrestapi.util.DataGeneratorUtil;

@Component
@Slf4j
public class UserServiceImpl implements UserService {

	private Map<Long, User> userMap;
	private static final String[] IGNORE_CURRENT_USER_PROPERTIES = {"id", "username", "password"};

	@PostConstruct
	private void init() {
		log.info("init. ");
		userMap = DataGeneratorUtil.generateUserMap();
	}

	@Override
	public Collection<User> getUserList(UserFilter request) {
		Collection<User> userList = userMap.values();
		userList = filterUsers(request, userList);
		return userList;
	}

	private List<User> filterUsers(UserFilter request, Collection<User> userList) {
		Stream<User> userListStream = userList.stream();
		if (!StringUtils.isEmpty(request.getUsername())) {
			userListStream = userListStream.filter(user -> user.getUsername().toLowerCase().contains(request.getUsername().toLowerCase()));
		}
		if (!StringUtils.isEmpty(request.getFirstname())) {
			userListStream = userListStream.filter(user -> user.getUsername().toLowerCase().contains(request.getFirstname().toLowerCase()));
		}
		if (!StringUtils.isEmpty(request.getLastname())) {
			userListStream = userListStream.filter(user -> user.getLastname().toLowerCase().contains(request.getLastname().toLowerCase()));
		}
		if (request.getGender() != null) {
			userListStream = userListStream.filter(user -> user.getGender() == request.getGender());
		}
		if (request.getRole() != null) {
			userListStream = userListStream.filter(user -> user.getRole() == request.getRole());
		}
		return userListStream.collect(Collectors.toList());
	}

	@Override
	public User getUserById(Long id) {
		return userMap.get(id);
	}

	@Override
	public void createUser(User user) {
		log.debug("createUser. ");
		user.setId(Long.valueOf(userMap.size() + 1));
		userMap.put(user.getId(), user);
	}

	@Override
	public User updateUser(Long id, User newUser) {
		log.debug("updateUser. ");
		User existingUser = userMap.get(id);
		if (existingUser == null) {
			throw new UserNotFoundException(id);
		}
		BeanUtils.copyProperties(newUser, existingUser, IGNORE_CURRENT_USER_PROPERTIES);
		return existingUser;
	}

	@Override
	public void deleteUserById(Long id) {
		log.debug("deleteUserByUsername. ");
		User existingUser = userMap.get(id);
		if (existingUser == null) {
			throw new UserNotFoundException(id);
		}
		userMap.remove(id);
	}

	@Override
	public User getUserByUsername(String username) {
		List<User> filteredList = userMap.values().stream().filter(user -> user.getUsername().toLowerCase().equals(username.toLowerCase())).collect(Collectors.toList());
		User user = null;
		if (!filteredList.isEmpty()) {
			user = filteredList.get(0);
		}
		return user;
	}



}
