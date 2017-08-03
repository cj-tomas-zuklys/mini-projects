package net.codinginaction.mp.soapwsbu.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import net.codinginaction.mp.soapwsbu.data.User;
import net.codinginaction.mp.soapwsbu.enumerations.UserRole;

public class DataGeneratorUtil {

	private static final int NUMBER_OF_USERS = 10;
	private static final Random RANDOM = new Random();
	private static final List<UserRole> USER_ROLE_VALUES = Collections.unmodifiableList(Arrays.asList(UserRole.values()));

	public static List<User> generateUserList() {
		List<User> list = new ArrayList<>();
		for (int i = 0; i < NUMBER_OF_USERS; i++) {
			User user = new User();
			user.setUsername("user" + i);
			user.setFirstname("SomeFirstname" + i);
			user.setLastname("SomeLastname" + i);
			user.setRole(USER_ROLE_VALUES.get(RANDOM.nextInt(UserRole.values().length)));
			list.add(user);
		}
		return list;
	}

	public static List<UserRole> generateUserRoleList() {
		return Arrays.asList(UserRole.values());
	}

}
