package com.youtube.jwt;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jwt.entity.Role;
import com.jwt.service.RoleService;

@ExtendWith(MockitoExtension.class)
public class RoleTest {

	@Mock
	private RoleService roleService;
	
	 @Test
		public void saveRoleTest()
		{
			//Create dummy object
			Role roleResponse = mock(Role.class);
			 //return 101
			when(roleResponse.getRoleName()).thenReturn("role");
			 //create another dummy object
			 Role roleReq = mock(Role.class);
			 //
			 when(roleService.createNewRole(any(Role.class))).thenReturn(roleResponse);
			Role roleres=  roleService.createNewRole(roleReq);
			 assertEquals(roleResponse.getRoleName(),roleres.getRoleName());
			
			//return departmentService.saveDepartment(department);
			
		}

}
