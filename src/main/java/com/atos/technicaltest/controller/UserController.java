package com.atos.technicaltest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atos.technicaltest.dto.UserDto;
import com.atos.technicaltest.service.UserService;
import com.atos.technicaltest.utils.Messages;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping
	public ResponseEntity<String> saveUser(@RequestBody @Valid UserDto userDto){
		service.saveUser(userDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(Messages.getMessageWithParam("user.creation.successful", 
				new String[]{userDto.userName()}));
	}
	
	@GetMapping("{username}")
	public ResponseEntity<UserDto> getUser(@PathVariable String username) {
		return ResponseEntity.status(HttpStatus.OK).body(service.getUser(username));
	}
	
	
}
