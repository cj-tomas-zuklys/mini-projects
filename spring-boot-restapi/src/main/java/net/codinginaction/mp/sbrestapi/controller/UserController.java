package net.codinginaction.mp.sbrestapi.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import net.codinginaction.mp.sbrestapi.data.User;
import net.codinginaction.mp.sbrestapi.data.UserFilter;
import net.codinginaction.mp.sbrestapi.exception.UserAlreadyExists;
import net.codinginaction.mp.sbrestapi.exception.UserNotFoundException;
import net.codinginaction.mp.sbrestapi.service.UserService;
import net.codinginaction.mp.sbrestapi.validation.UserValidator;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

	//TODO include security, include validation, include tests, include post, put
	@Autowired
	private UserService userService;

	@Autowired
	private UserValidator validator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.setValidator(validator);
	}

	@RequestMapping(method = RequestMethod.GET)
	public Collection<User> getUserList(UserFilter filter) throws Exception {
		log.debug("getUserList. filter: {}", filter);
		return userService.getUserList(filter);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable("id") Long id) {
		log.debug("getUser. id: {}", id);
		User user = userService.getUserById(id);
		if (user == null) {
			log.error("getUser. User with id: {} not found.", id);
			throw new UserNotFoundException(id);
		}
		return user;
	}

	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody @Validated User user) {
        log.debug("createUser. user: {}", user);

        if (userService.getUserByUsername(user.getUsername()) != null) {
            log.error("createUser. User: {} already exists", user.getUsername());
        	throw new UserAlreadyExists(user.getUsername());
        }
        userService.createUser(user);
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateUser(@PathVariable("id") Long id, @RequestBody @Validated User user) {
        log.debug("updateUser. id: {}", id);
        userService.updateUser(id, user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        log.debug("deleteUser. id: {}", id);
        userService.deleteUserById(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
}
