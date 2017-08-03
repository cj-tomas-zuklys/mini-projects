package net.codinginaction.mp.soapwsbu.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import net.codinginaction.mp.soapwsbu.data.GetUserListRequest;
import net.codinginaction.mp.soapwsbu.data.GetUserListResponse;
import net.codinginaction.mp.soapwsbu.data.GetUserRoleListRequest;
import net.codinginaction.mp.soapwsbu.data.GetUserRoleListResponse;
import net.codinginaction.mp.soapwsbu.helper.BuException;

@WebService(targetNamespace = "net.codinginaction.mp.soapwsbu")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface BottomUpWs {

	@WebResult(name = "GetUserListResponse", targetNamespace = "net.codinginaction.mp.soapwsbu")
	@WebMethod(operationName = "GetUserList", action = "GetUserList")
	GetUserListResponse getUserList(@WebParam(name = "GetUserListRequest") GetUserListRequest request) throws BuException;

	@WebResult(name = "GetUserRoleListResponse", targetNamespace = "net.codinginaction.mp.soapwsbu")
	@WebMethod(operationName = "GetUserRoleList", action = "GetUserRoleList")
	GetUserRoleListResponse getUserRoleList(@WebParam(name = "GetUserRoleListRequest") GetUserRoleListRequest request) throws BuException;

}
