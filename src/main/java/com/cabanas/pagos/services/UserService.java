package com.cabanas.pagos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cabanas.pagos.models.User;
import com.cabanas.pagos.repositories.UserRepository;
import com.cabanas.pagos.services.interfaces.IUser;

@Service
public class UserService implements IUser {

    @Autowired
    private UserRepository userRepository;

    
    @Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findById(int id) {
		return userRepository.findById(id);
	}
	
	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Override
	public User save(User user) {
		
		final Optional<User> gasto_ = userRepository.findById(user.getId());
		if(gasto_.isPresent()) {
			return null;
		} else {
			userRepository.save(user);
			return user;
		}
	}
	
	@Override
	public User update(User user) {
		
		final Optional<User> user_ = userRepository.findById(user.getId());
		if(user_.isPresent()) {
			user.setId(user_.get().getId());
			userRepository.save(user);
			return user_.get();
		} else {
			return null;
		}
	}
	
	@Override
	public void deleteById(int id) {
		
		final Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			userRepository.delete(user.get());
		}
	}
}

