package net.codinginaction.mp.sbsoapws.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.codinginaction.mp.sbsoapws.enumerations.Gender;
import net.codinginaction.mp.sbsoapws.enumerations.UserRole;

@ToString
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
@Getter @Setter
public class GetUserListRequest {
	private String username;
	private String firstname;
	private String lastname;
	private Gender gender;
	private UserRole role;
}
