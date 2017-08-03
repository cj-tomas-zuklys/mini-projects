package net.codinginaction.mp.soapwsbu.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import net.codinginaction.mp.soapwsbu.data.GetUserListRequest;
import net.codinginaction.mp.soapwsbu.data.GetUserListResponse;
import net.codinginaction.mp.soapwsbu.data.GetUserRoleListRequest;
import net.codinginaction.mp.soapwsbu.data.GetUserRoleListResponse;
import net.codinginaction.mp.soapwsbu.data.User;
import net.codinginaction.mp.soapwsbu.service.UserService;
import net.codinginaction.mp.soapwsbu.util.DataGeneratorUtil;

@Component
public class UserServiceImpl implements UserService {

	@Override
	public GetUserListResponse getUserList(GetUserListRequest request) throws Exception {
		GetUserListResponse response = new GetUserListResponse();
		List<User> userList = DataGeneratorUtil.generateUserList();
		userList = filterUsers(request, userList);
		response.setUserList(userList);
		return response;
	}

	private List<User> filterUsers(GetUserListRequest request, List<User> userList) {
		Stream<User> userListStream = userList.stream();
		if (!StringUtils.isEmpty(request.getUsername())) {
			userListStream = userListStream.filter(user -> user.getUsername().toLowerCase().contains(request.getUsername().toLowerCase()));
		}
		if (!StringUtils.isEmpty(request.getFirstname())) {
			userListStream = userListStream.filter(user -> user.getUsername().toLowerCase().contains(request.getFirstname().toLowerCase()));
		}
		if (!StringUtils.isEmpty(request.getLastname())) {
			userListStream = userListStream.filter(user -> user.getLastname().toLowerCase().contains(request.getLastname().toLowerCase()));
		}
		if (request.getGender() != null) {
			userListStream = userListStream.filter(user -> user.getGender() == request.getGender());
		}
		if (request.getRole() != null) {
			userListStream = userListStream.filter(user -> user.getRole() == request.getRole());
		}
		return userListStream.collect(Collectors.toList());
	}

	@Override
	public GetUserRoleListResponse getUserRoleList(GetUserRoleListRequest request) throws Exception {
		GetUserRoleListResponse response = new GetUserRoleListResponse();
		response.setUserRoleList(DataGeneratorUtil.generateUserRoleList());
		return response;
	}

}
