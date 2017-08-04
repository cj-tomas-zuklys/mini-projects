package net.codinginaction.mp.sbrestapi.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.codinginaction.mp.sbrestapi.enumerations.Gender;
import net.codinginaction.mp.sbrestapi.enumerations.UserRole;

@ToString
@Getter @Setter
public class User {
	private Long id;
	private String username;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	private String firstname;
	private String lastname;
	private Gender gender;
	private UserRole role;
}
