package com.atos.technicaltest.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDto (		
		@NotBlank(message="{user.name.empty}")
		String userName,
		
		@NotNull(message="{user.birthDate.empty}")
		@JsonFormat(pattern = "dd/MM/yyyy")
		LocalDate birthDate,
		
		@NotBlank(message="{user.residence.country.empty}")
		String residenceCoutry,
		
		String phoneNumber,
		
		String gender){}
