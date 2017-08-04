package net.codinginaction.mp.sbrestapi.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import net.codinginaction.mp.sbrestapi.data.User;
import net.codinginaction.mp.sbrestapi.service.UserService;
import net.codinginaction.mp.sbrestapi.util.DataGeneratorUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	Map<Long, User> userMap;

	ObjectMapper objectMapper;

	@Before
	public void init() {
		userMap = DataGeneratorUtil.generateUserMap();
		objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	@Test
	public void getUserList() throws Exception {
		Mockito.when(userService.getUserList(Mockito.any())).thenReturn(userMap.values());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/user/").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		List<User> userList = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<User>>(){});
		Assert.assertEquals(userMap.size(), userList.size());
	}

	@Test
	public void getUser() throws Exception {
		User userToRetrieve = userMap.entrySet().iterator().next().getValue();
		Mockito.when(userService.getUserById(Mockito.anyLong())).thenReturn(userToRetrieve);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/user/" + userToRetrieve.getId()).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		User retrievedUser = objectMapper.readValue(result.getResponse().getContentAsString(), User.class);
		Assert.assertEquals(userToRetrieve.getId(), retrievedUser.getId());
	}

	@Test
	public void createUser() throws Exception {
		String userInJson = constructUserJson();
		Mockito.when(userService.getUserByUsername(Mockito.anyString())).thenReturn(null);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/user/")
				.content(userInJson)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().isCreated());
	}
	
	@Test
	public void updateUser() throws Exception {
		String userInJson = constructUserJson();
		User userToUpdate = userMap.entrySet().iterator().next().getValue();
		Mockito.when(userService.updateUser(userToUpdate.getId(), userToUpdate)).thenReturn(userToUpdate);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/user/" + userToUpdate.getId())
				.content(userInJson)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().isNoContent());
	}
	
	@Test
	public void deleteUser() throws Exception {
		User userToUpdate = userMap.entrySet().iterator().next().getValue();
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/user/" + userToUpdate.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().isNoContent());
	}

	private String constructUserJson() {
		return "{\"id\" : null,	\"username\" : \"TestUserUsername\", \"firstname\" : \"TestUser\", \"lastname\" : \"TestUserLastname\", \"gender\" : \"MALE\", \"password\" : \"TestUserPassword\", \"role\" : \"ADMIN\"}";
	}
}

