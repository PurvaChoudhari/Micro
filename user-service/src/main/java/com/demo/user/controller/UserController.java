
package com.demo.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.demo.user.Vo.ResponseTemplateVO;
import com.demo.user.entity.User;
import com.demo.user.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/users")
@Api(value = "/users", tags = "Users Managment")

public class UserController {

	@Autowired
	private UserService userService;
	
	public UserController()
	{
		
	}
	
	
	public UserController(UserService userService)
	{
		this.userService=userService;
	}
	

	@Autowired
	private RestTemplate restTemplate;
	
	public UserController(RestTemplate restTemplatee)
	{
		this.restTemplate=restTemplate;
	} 

	@PutMapping("/{id}")
	public User update(@PathVariable int id, @RequestBody User user) {

		user.setUserId(id);

		return userService.addOrUpdate(user);

	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int userId) {

		System.out.println("in controller");
		User user = userService.findByUserId(userId);
		System.out.println(user);
		userService.deleteById(userId);
		return "delete user id " + userId;

	}

	@PostMapping("/save")
	public User saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}

	@ApiOperation(value = "Get User", notes = "Get User", tags = { "User Managment" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "User created successfully"),
			@ApiResponse(code = 404, message = "Invalid Data"),
			@ApiResponse(code = 500, message = "Internal server error") })
	
	@GetMapping("/{id}")
	public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") int userId) {
		System.out.println("inside controller");
		return userService.getUserWithDepartment(userId);
	}

//	@GetMapping("/message")
//	public String getMessage() {
//		logger.info("[getMessage] info message");
//		logger.warn("[getMessage] warn message");
//		logger.error("[getMessage] error message");
//		logger.debug("[getMessage] debug message");
//		logger.trace("[getMessage] trace message");
//		//System.out.println(10/0);
//		return "open console to check log messages.";
//	}
}
