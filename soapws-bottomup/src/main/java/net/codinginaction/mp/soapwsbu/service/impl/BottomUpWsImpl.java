package net.codinginaction.mp.soapwsbu.service.impl;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.codinginaction.mp.soapwsbu.data.GetUserListRequest;
import net.codinginaction.mp.soapwsbu.data.GetUserListResponse;
import net.codinginaction.mp.soapwsbu.data.GetUserRoleListRequest;
import net.codinginaction.mp.soapwsbu.data.GetUserRoleListResponse;
import net.codinginaction.mp.soapwsbu.helper.BuException;
import net.codinginaction.mp.soapwsbu.service.BottomUpWs;
import net.codinginaction.mp.soapwsbu.service.UserService;
import net.codinginaction.mp.soapwsbu.util.RequestUtil;

@Component
@WebService(endpointInterface = "net.codinginaction.mp.soapwsbu.service.BottomUpWs", targetNamespace = "net.codinginaction.mp.soapwsbu", serviceName = "BottomUpWs", portName = "BottomUpWs")
@Slf4j
public class BottomUpWsImpl implements BottomUpWs {

	@Autowired
	private UserService userService;

	@Override
	public GetUserListResponse getUserList(GetUserListRequest request) throws BuException {
		log.debug("getUserList. request: {}", request);
		try {
			GetUserListResponse response = userService.getUserList(request);
			log.debug("getUserList. response: {}", response);
			return response;
		} catch (Exception e) {
			throw RequestUtil.formBuException(e);
		}
	}

	@Override
	public GetUserRoleListResponse getUserRoleList(GetUserRoleListRequest request) throws BuException {
		log.debug("getUserRoleList. request: {}", request);
		try {
			GetUserRoleListResponse response = userService.getUserRoleList(request);
			log.debug("getUserRoleList. response: {}", response);
			return response;
		} catch (Exception e) {
			throw RequestUtil.formBuException(e);
		}
	}

}
