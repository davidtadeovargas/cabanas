package com.cabanas.pagos.controllers.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

import com.cabanas.pagos.models.User;
import com.cabanas.pagos.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserApiController {
	
	//Capa de servicio a usuarios
	@Autowired
	UserService userService;
	
	//Obtiene todos los usuarios
	@GetMapping("/getall")
	public List<User> getAll(){
		return userService.getAll();
	}
	
	//Obtiene por ID
	@GetMapping("/get/{id}")
	public User getById(@PathVariable("id") int id){
		final Optional<User> user = userService.findById(id);
		if(user.isPresent()) {
			return user.get();
		} else {
			return null;
		}
	}
	
	//Borra un usuario
	@PostMapping("/delete/{id}")
	public boolean delete(@PathVariable("id") int id) {
		userService.deleteById(id);
		return true;
	}
	
	//Crea un usuario
	@PostMapping("/save")
	public boolean save(@Valid @RequestBody User user) {
		
		//Si el usuario ya existe no permitas avanzar
		Optional<User> user_ = userService.findByUsername(user.getUsername());
		if(user_!=null) {
			return false;
		}
		
		//Crea el nuevo usuario
		userService.save(user);
		return true;
	}
	
	//Actualiza un usuario
	@PostMapping("/update/{id}")
	public boolean update(@PathVariable("id") int id, @Valid @RequestBody User user) {
		
		//Si el usuario no existe no permitas avanzar
		Optional<User> user_ = userService.findById(id);
		if(user_==null) {
			return false;
		}
		
		//Actualiza el usuario
		user.setId(id);
		userService.save(user);
		return true;
	}
}

