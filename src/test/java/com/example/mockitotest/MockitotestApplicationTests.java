package com.example.mockitotest;

import com.example.mockitotest.user.*;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class MockitotestApplicationTests {


	@InjectMocks
	private UserController userController;

	@Mock
	private UserService userService;

	private MockMvc mockMvc;

	@BeforeEach
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@DisplayName("회원 가입 성공")
	@Test
	void signUpSuccess() throws Exception {
		// given
		SignUpRequest request = signUpRequest();
		UserResponse response = userResponse();

		when(userService.signUp(any(SignUpRequest.class))).thenReturn(response);
//		doReturn(response).when(userService)
//				.signUp(any(SignUpRequest.class));
		//then
		ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders.post("/users/signUp")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new Gson().toJson(request))
		);

		// then
		resultActions.andDo(print())
				.andExpect(status().is2xxSuccessful())
				.andExpect(jsonPath("email", response.getEmail()).exists())
				.andExpect(jsonPath("pw", response.getPw()).exists())
				.andExpect(jsonPath("role", response.getRole()).exists());
	}

	private SignUpRequest signUpRequest() {
		return SignUpRequest.builder()
				.email("test@test.test")
				.pw("test")
				.build();
	}

	private UserResponse userResponse() {
		return UserResponse.builder()
				.email("test@test.test")
				.pw("test")
				.role(UserRole.ROLE_USER)
				.build();
	}

}
