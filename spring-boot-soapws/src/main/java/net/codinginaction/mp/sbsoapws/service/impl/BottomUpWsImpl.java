package net.codinginaction.mp.sbsoapws.service.impl;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codahale.metrics.annotation.Timed;

import lombok.extern.slf4j.Slf4j;
import net.codinginaction.mp.sbsoapws.data.GetUserListRequest;
import net.codinginaction.mp.sbsoapws.data.GetUserListResponse;
import net.codinginaction.mp.sbsoapws.data.GetUserRoleListRequest;
import net.codinginaction.mp.sbsoapws.data.GetUserRoleListResponse;
import net.codinginaction.mp.sbsoapws.helper.BuException;
import net.codinginaction.mp.sbsoapws.service.BottomUpWs;
import net.codinginaction.mp.sbsoapws.service.UserService;
import net.codinginaction.mp.sbsoapws.util.RequestUtil;

@Component
@WebService(endpointInterface = "net.codinginaction.mp.sbsoapws.service.BottomUpWs", targetNamespace = "net.codinginaction.mp.sbsoapws", serviceName = "BottomUpWs", portName = "BottomUpWs")
@Slf4j
public class BottomUpWsImpl implements BottomUpWs {

	@Autowired
	private UserService userService;

	@Override
	@Timed
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
	@Timed
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
