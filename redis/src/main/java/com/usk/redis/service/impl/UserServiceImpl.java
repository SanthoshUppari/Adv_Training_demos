package com.usk.redis.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.usk.redis.entity.User;
import com.usk.redis.repository.UserRepository;
import com.usk.redis.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final String REDIS_CACHE_VALUE = "USER";

	@Autowired
	UserRepository userRepository;

	@Override
	@CachePut(value = REDIS_CACHE_VALUE, key = "#user.userId")
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	@Cacheable(value = REDIS_CACHE_VALUE, key = "#userId")
	public User getUserById(Long userId) {
		Optional<User> user = userRepository.findByUserId(userId);
		if (user.isPresent()) {
			return user.get();
		} else {
			return null;
		}
	}

	@Override
	@CacheEvict(value = REDIS_CACHE_VALUE, key = "#userId")
	public void deletUser(Long userId) {
		userRepository.deleteById(userId);
	}

}
