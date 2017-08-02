package net.codinginaction.mp.soapwstd.service.impl;

import javax.jws.WebService;

import org.apache.cxf.hello_world_soap_http.Greeter;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@WebService(portName = "SoapwsTopdownService", serviceName = "SoapwsTopdownService", endpointInterface = "org.apache.cxf.hello_world_soap_http.Greeter", targetNamespace = "http://www.codinginaction.net/SoapwsTopdownService")
public class GreeterImpl implements Greeter {

	@Override
	public String sayHi() {
		log.debug("sayHi. ");
		return "Congratulations, you have successfully invoked SOAP web service. Operation: sayHi!";
	}

	@Override
	public String greetMe(String requestType) {
		log.debug("greetMe. requestType: {}", requestType);
		return "Congratulations, you have successfully invoked SOAP web service. Operation: greetMe!";
	}

	@Override
	public void greetMeOneWay(String requestType) {
		log.debug("greetMeOneWay. requestType: {}", requestType);
	}

}
