package net.codinginaction.mp.soapwsbu.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.codinginaction.mp.soapwsbu.enumerations.Gender;
import net.codinginaction.mp.soapwsbu.enumerations.UserRole;

@ToString
@Getter @Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class User {
	@XmlElement(nillable = false, required = true)
	private String username;

	private String firstname;

	private String lastname;

	private Gender gender;

	@XmlElement(nillable = false, required = true)
	private UserRole role;
}
