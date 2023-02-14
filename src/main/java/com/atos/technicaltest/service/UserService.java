package com.atos.technicaltest.service;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.atos.technicaltest.dto.UserDto;
import com.atos.technicaltest.exception.CustomException;
import com.atos.technicaltest.mapper.UserMapper;
import com.atos.technicaltest.repository.UserRepository;
import com.atos.technicaltest.utils.Messages;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserMapper userMapper;
	
	public void saveUser(UserDto userDto) {
		if(calculateAge(userDto.birthDate()) < 18)
			throw new CustomException(Messages.getMessage("user.age.adult.only"), HttpStatus.BAD_REQUEST);
		
		if(userRepository.findByUserName(userDto.userName()).isPresent())
			throw new CustomException(Messages.getMessage("user.already.exist"), HttpStatus.CONFLICT);
		
		userRepository.save(userMapper.mapUserDtoToUser(userDto));
	}
	
	public UserDto getUser(String username) {
		return userRepository.findByUserName(username).stream().map(userMapper::mapUserToUserDto).findFirst()
				.orElseThrow(()->new CustomException(Messages.getMessage("user.not.found"), HttpStatus.NOT_FOUND));
	}
	
	private int calculateAge(LocalDate birthDate) {
		return Period.between(birthDate, LocalDate.now()).getYears();
	}
}
