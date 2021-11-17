package com.cabanas.pagos.test;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.annotation.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cabanas.pagos.models.User;
import com.cabanas.pagos.services.UserService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateUserTest {

	@Autowired
	private UserService userService;
	
	//Inserta un usuario
	@Test
	@Order(1)
	public void insertUser() {
    	
		final User user = new User();
		user.setUsername("DTVV");
		user.setNombre("David Tadeo");
		user.setPassword("123456");
		user.setRol("ADMIN");
		
		userService.save(user);
    	
    	Optional<User> user_ = userService.findByUsername(user.getUsername());
    	
    	Assert.assertNotNull(user_.get());
    }
}
