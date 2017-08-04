package net.codinginaction.mp.sbrestapi.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.codinginaction.mp.sbrestapi.data.User;
import net.codinginaction.mp.sbrestapi.enumerations.Gender;
import net.codinginaction.mp.sbrestapi.enumerations.UserRole;

public class DataGeneratorUtil {

	private static final int NUMBER_OF_USERS = 10;
	private static final Random RANDOM = new Random();
	private static final List<UserRole> USER_ROLE_VALUES = Collections.unmodifiableList(Arrays.asList(UserRole.values()));
	private static final List<Gender> GENDER_VALUES = Collections.unmodifiableList(Arrays.asList(Gender.values()));

	public static Map<Long, User> generateUserMap() {
		Map<Long, User> userMap = new HashMap<>();
		for (int i = 0; i < NUMBER_OF_USERS; i++) {
			User user = new User();
			user.setId(Long.valueOf(i));
			user.setUsername("user" + i);
			user.setFirstname("SomeFirstname" + i);
			user.setLastname("SomeLastname" + i);
			user.setRole(USER_ROLE_VALUES.get(RANDOM.nextInt(USER_ROLE_VALUES.size())));
			user.setGender(GENDER_VALUES.get(RANDOM.nextInt(GENDER_VALUES.size())));
			userMap.put(user.getId(), user);
		}
		return userMap;
	}

}
