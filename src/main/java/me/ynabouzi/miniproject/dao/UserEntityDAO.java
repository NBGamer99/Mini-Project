package me.ynabouzi.miniproject.dao;

import me.ynabouzi.miniproject.model.UserEntity;

import java.util.List;

public interface UserEntityDAO {

	UserEntity getUserById(Long id);

	UserEntity getUserByUsername(String username);

	List<UserEntity> getAllUsers();

	UserEntity saveUser(UserEntity user);

	boolean deleteUser(Long id);

	UserEntity updateUser(UserEntity user, Long id);

}
