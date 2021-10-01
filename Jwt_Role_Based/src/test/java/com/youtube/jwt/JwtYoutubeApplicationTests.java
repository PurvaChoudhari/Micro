/*package com.youtube.jwt;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;

import com.jwt.entity.User;
import com.jwt.service.UserService;


@ExtendWith(MockitoExtension.class)
class JwtYoutubeApplicationTests {

	    @Mock
		private UserService userService;
		
    @Test
    void contextLoads() {
    }
    
    @Test
	public void saveUserTest()
	{
		//Create dummy object
		User userResponse = mock(User.class);
		 //return 101
		when(userResponse.getUserName()).thenReturn("user");
		 //create another dummy object
		 User userReq = mock(User.class);
		 //
		 when(userService.registerNewUser(any(User.class))).thenReturn(userResponse);
		User userres=  userService.registerNewUser(userReq);
		 assertEquals(userResponse.getUserName(),userres.getUserName());
		
		//return departmentService.saveDepartment(department);
		
	}

}*/
