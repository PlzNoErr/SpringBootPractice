package com.ssafy.hw.model.dao;

import com.ssafy.hw.model.dto.User;

public interface UserDao {
	int insertUser(User user);
	
	User getUserById(String id);
}
