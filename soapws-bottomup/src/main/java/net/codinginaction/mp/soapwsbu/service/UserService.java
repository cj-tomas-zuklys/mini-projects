package net.codinginaction.mp.soapwsbu.service;

import net.codinginaction.mp.soapwsbu.data.GetUserListRequest;
import net.codinginaction.mp.soapwsbu.data.GetUserListResponse;
import net.codinginaction.mp.soapwsbu.data.GetUserRoleListRequest;
import net.codinginaction.mp.soapwsbu.data.GetUserRoleListResponse;

public interface UserService {

	GetUserListResponse getUserList(GetUserListRequest request) throws Exception;

	GetUserRoleListResponse getUserRoleList(GetUserRoleListRequest request) throws Exception;

}
