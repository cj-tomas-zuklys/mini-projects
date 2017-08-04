package net.codinginaction.mp.sbrestapi.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.codinginaction.mp.sbrestapi.enumerations.Gender;
import net.codinginaction.mp.sbrestapi.enumerations.UserRole;

@ToString
@Getter @Setter
public class UserFilter {
	private String username;
	private String firstname;
	private String lastname;
	private Gender gender;
	private UserRole role;
}
