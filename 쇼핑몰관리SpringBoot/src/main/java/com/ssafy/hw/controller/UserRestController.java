package com.ssafy.hw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.hw.model.dto.User;
import com.ssafy.hw.model.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/userapi")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
public class UserRestController {
	@Autowired
	UserService userService;

	@PostMapping("/login")
	@ApiOperation(value = "해당 id와 password를 가진 사용자 정보를 반환한다.")
	public ResponseEntity<?> login(@RequestBody User user) {

		User loginUser = userService.getUserById(user.getUserId());
		if(loginUser != null && user.getUserPass().equals(loginUser.getUserPass())) {
			return new ResponseEntity<User>(loginUser, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

}
