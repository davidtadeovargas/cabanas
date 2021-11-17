package com.cabanas.pagos.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.cabanas.pagos.models.User;

public interface IUser {
	List<User> getAll();
    Optional<User> findById(int id);
    Optional<User> findByUsername(String username);
    User save(User std);
    void deleteById(int id);
}