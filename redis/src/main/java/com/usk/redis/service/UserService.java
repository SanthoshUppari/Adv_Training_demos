package com.usk.redis.service;

import com.usk.redis.entity.User;

public interface UserService {

	public User saveUser(User user);

	public User getUserById(Long userId);

	void deletUser(Long userId);
	
}
