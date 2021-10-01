package com.demo.department.controller;

import java.time.LocalTime;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.demo.department.entity.Department;
import com.demo.department.service.DepartmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


//import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/departments")
@Api(value="/departments", tags="Departments Managment")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@PostMapping("/save")
	public Department saveDepartment(@RequestBody Department department)
	{
		//log.info("Inside saveDepartment of DepartmentController");
		return departmentService.saveDepartment(department);
	}
	
	
	@ApiOperation(value="Get department",notes ="Get department", tags= {"Department Managment"})
	@ApiResponses(value= {
			@ApiResponse(code=200,message="Department created successfully"),
			@ApiResponse(code=404,message="Invalid Data"),
			@ApiResponse(code=500,message="Internal server error")})
    @PreAuthorize(value="sergio")
    @GetMapping("/{id}")
	public Department findDepartmentById(@PathVariable("id")int departmentId)
	{
		//log.info("Inside findDepartmentById of DepartmentController");
		return departmentService.findDepartmentById(departmentId);
	}
	
	@RequestMapping("/{id}")
	ResponseEntity< Department> findDepartmentById(@PathVariable int id, WebRequest request) {
		// ...

		Department dept = departmentService.findDepartmentById(id);
		CacheControl cacheControl = CacheControl.maxAge(5, TimeUnit.MINUTES);

		ZonedDateTime expiresDate = ZonedDateTime.now().with(LocalTime.MAX);
		String expires = expiresDate.format(DateTimeFormatter.RFC_1123_DATE_TIME);

		Long lastUpdated1 = ((Date) dept.getModificationDate()).toInstant().toEpochMilli();

		String lastUpdated = dept.getModificationDate().toString();
		String eTag = DigestUtils.md5DigestAsHex( lastUpdated.getBytes());

		if (request.checkNotModified(lastUpdated1)) {
			return null;
		}

		if (request.checkNotModified(eTag)) {
			return null;
		}

		return ResponseEntity.ok().cacheControl(cacheControl).header(HttpHeaders.EXPIRES, expires)
				.lastModified(lastUpdated1).eTag(eTag).body(dept);
	}
	
	@PutMapping("/{id}")
	private Department update(@PathVariable int id, @RequestBody Department dept) {

		Department department = departmentService.findDepartmentById(id);

		
		return departmentService.addOrUpdate(department);

	}

	@DeleteMapping("/{id}")
	private String delete(@PathVariable int id) {

		System.out.println("inside delete");
		try {

			Department department = departmentService.findDepartmentById(id);
			departmentService.deleteById(id);
			return "department found and Deleted";
		} catch (Exception e) {
			return ("department id not found");
		}
	}
	
}
