package net.codinginaction.mp.soapwsbu.data;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.codinginaction.mp.soapwsbu.enumerations.UserRole;

@ToString
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
@Getter @Setter
public class GetUserRoleListResponse {
	private List<UserRole> userRoleList;
}
