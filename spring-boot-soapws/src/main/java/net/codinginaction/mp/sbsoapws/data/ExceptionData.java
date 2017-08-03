package net.codinginaction.mp.sbsoapws.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.codinginaction.mp.sbsoapws.enumerations.ErrorCode;
import net.codinginaction.mp.sbsoapws.util.RequestUtil;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "net.codinginaction.mp.sbsoapws")
@ToString
@NoArgsConstructor
public class ExceptionData {

	@XmlElement(required = true, nillable = false)
	@Getter @Setter
	private ErrorCode code;

	@XmlElement(required = false)
	@Getter @Setter
	private String message;

	@XmlElement(required = true, nillable = false)
	@Getter @Setter
	private String requestId;

	public ExceptionData(ErrorCode code) {
		this.code = code;
		this.requestId = RequestUtil.getCurrentRequestId();
	}

	public ExceptionData(ErrorCode code, String message) {
		this.code = code;
		this.message = message;
		this.requestId = RequestUtil.getCurrentRequestId();
	}
}
