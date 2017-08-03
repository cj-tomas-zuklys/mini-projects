package net.codinginaction.mp.sbsoapws.service;

import net.codinginaction.mp.sbsoapws.data.GetUserListRequest;
import net.codinginaction.mp.sbsoapws.data.GetUserListResponse;
import net.codinginaction.mp.sbsoapws.data.GetUserRoleListRequest;
import net.codinginaction.mp.sbsoapws.data.GetUserRoleListResponse;

public interface UserService {

	GetUserListResponse getUserList(GetUserListRequest request) throws Exception;

	GetUserRoleListResponse getUserRoleList(GetUserRoleListRequest request) throws Exception;

}
