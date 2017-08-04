package net.codinginaction.mp.sbrestapi.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.extern.slf4j.Slf4j;
import net.codinginaction.mp.sbrestapi.config.Configuration;
import net.codinginaction.mp.sbrestapi.data.User;
import net.codinginaction.mp.sbrestapi.enumerations.ValidationErrorCode;
import net.codinginaction.mp.sbrestapi.service.UserService;


@Slf4j
@Component
public class UserValidator implements Validator {

	@Autowired private Configuration config;
	@Autowired private UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (!(target instanceof User)) {
			return;
		}
		User user = (User) target;
		if (user.getId() == null || user.getId() <= 0) {
			if (StringUtils.isEmpty(user.getPassword())) {
				log.debug("Password field is required");
				errors.rejectValue("password", ValidationErrorCode.REQUIRED.name(), "Password field is required");
			} else if (user.getPassword().length() > config.getUserMaxPassswordLength()) {
				log.debug("Password is too long: {}, max: {}", user.getPassword().length(), config.getUserMaxPassswordLength());
				errors.rejectValue("password", ValidationErrorCode.TOO_LONG.name(), "Password is too long, max length: " + config.getUserMaxPassswordLength());
			}
			if (StringUtils.isEmpty(user.getUsername())) {
				log.debug("Username field is required");
				errors.rejectValue("username", ValidationErrorCode.REQUIRED.name(), "Username field is required");
			} else if (user.getUsername().length() > config.getUserMaxUsernameLength()) {
				log.debug("Username is too long: {}, max: {}", user.getUsername().length(), config.getUserMaxUsernameLength());
				errors.rejectValue("username", ValidationErrorCode.TOO_LONG.name(), "Username is too long, max length: " + config.getUserMaxUsernameLength());
			} else {
				User existing = userService.getUserByUsername(user.getUsername());
				if (existing != null) {
					log.debug("User with provided username: {} already exists", existing.getUsername());
					errors.rejectValue("username", ValidationErrorCode.ALREADY_EXIST.name(), "User with provided username already exists");
				}
			}
		}
		if (!StringUtils.isEmpty(user.getFirstname()) && user.getFirstname().length() > config.getUserMaxFirstnameLength()) {
			log.debug("Firstname is too long: {}, max: {}", user.getFirstname().length(), config.getUserMaxFirstnameLength());
			errors.rejectValue("firstname", ValidationErrorCode.TOO_LONG.name(), "Firstname is too long, max length: " + config.getUserMaxFirstnameLength());
		}
		if (!StringUtils.isEmpty(user.getLastname()) && user.getLastname().length() > config.getUserMaxLastnameLength()) {
			log.debug("Lastname is too long: {}, max: {}", user.getLastname().length(), config.getUserMaxLastnameLength());
			errors.rejectValue("lastname", ValidationErrorCode.TOO_LONG.name(), "Lastname is too long, max length: " + config.getUserMaxLastnameLength());
		}

		if (StringUtils.isEmpty(user.getRole())) {
			log.debug("Role field is required");
			errors.rejectValue("role", ValidationErrorCode.REQUIRED.name(), "Role field is required");
		}

	}

}
