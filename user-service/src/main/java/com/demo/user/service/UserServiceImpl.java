package com.demo.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.user.Vo.Department;
import com.demo.user.Vo.ResponseTemplateVO;
import com.demo.user.entity.User;
import com.demo.user.repository.UserRepository;

@Service

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	public UserServiceImpl()
	{
		
	}

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	private RestTemplate restTemplate;
	
	public UserServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override

     public ResponseTemplateVO getUserWithDepartment(int userId) {
		ResponseTemplateVO vo = new ResponseTemplateVO();

		User user = userRepository.findByUserId(userId);
		System.out.println(user);

		Department department = restTemplate
				.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId(), Department.class);

		System.out.println(department);
		vo.setUser(user);
		vo.setDepartment(department);
		System.out.println(vo);
		System.out.println("fetching from db");


		return vo;
	}

	@Override
	public User findByUserId(int id) {
		System.out.println("fetching from db");
		return userRepository.findByUserId(id);
	}

	@Override
	public User addOrUpdate(User users) {
	
		return userRepository.save(users);
	}

	@Override
	public void deleteById(int userId) {
		userRepository.deleteById(userId);
		
	}

}
