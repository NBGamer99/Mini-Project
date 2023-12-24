package me.ynabouzi.miniproject.dao;

import me.ynabouzi.miniproject.model.UserEntity;

import java.util.List;

public interface UserEntityDAO {

	UserEntity getUserById(Long id);

	UserEntity getUserByUsername(String username);

	List<UserEntity> getAllUsers();

	UserEntity saveUser(UserEntity user);

	void deleteUser(Long id);

	void updateUser(UserEntity user, Long id);

}
