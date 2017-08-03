package net.codinginaction.mp.soapwsbu.data;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
@ToString
@Getter @Setter
public class GetUserListResponse {
	private List<User> userList;
}
