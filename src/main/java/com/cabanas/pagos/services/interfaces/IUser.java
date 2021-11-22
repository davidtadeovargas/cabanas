package com.cabanas.pagos.services.interfaces;

import java.util.Optional;

import com.cabanas.pagos.models.User;

public interface IUser extends ICrud<User> {
	Optional<User> findByUsername(String username);
}