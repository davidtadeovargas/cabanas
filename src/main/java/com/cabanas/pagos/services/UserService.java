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
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findById(int id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}
	
	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Override
	public User save(User product) {
		// TODO Auto-generated method stub
		
		final Optional<User> user_ = userRepository.findById(product.getId());
		if(user_.isPresent()) {
			product.setId(user_.get().getId());
			product.setNombre(user_.get().getNombre());
		}
		
		return userRepository.save(product);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		final Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			userRepository.delete(user.get());
		}
	}
}

