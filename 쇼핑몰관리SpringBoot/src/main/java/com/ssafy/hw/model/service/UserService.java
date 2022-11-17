package com.ssafy.hw.model.service;

import com.ssafy.hw.model.dto.User;

public interface UserService {
	
	int insertUser(User user);
	
	User getUserById(String id);
}
