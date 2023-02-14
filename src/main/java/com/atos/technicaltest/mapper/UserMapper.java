package com.atos.technicaltest.mapper;

import org.springframework.stereotype.Service;

import com.atos.technicaltest.dto.UserDto;
import com.atos.technicaltest.model.User;

@Service
public class UserMapper {

	public UserDto mapUserToUserDto(User user) {
		return new UserDto( 
				user.getUserName(), 
				user.getBirthDate(), 
				user.getResidenceCoutry(), 
				user.getPhoneNumber(), 
				user.getGender());
	}
	
	public User mapUserDtoToUser(UserDto userDto) {
		return new User(0, userDto.userName(), userDto.birthDate(), userDto.residenceCoutry(), 
				userDto.phoneNumber(), userDto.gender());
	}

}
