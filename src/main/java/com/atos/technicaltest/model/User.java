package com.atos.technicaltest.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="table_user")
public class User {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String userName;
	
	private LocalDate birthDate;
	
	private String residenceCoutry;
	
	private String phoneNumber;
	
	private String gender;
	
	public User() {}
	
	public User(int id, String userName, LocalDate birthDate, 
			String residenceCoutry, String phoneNumber, String gender) {
		super();
		this.id = id;
		this.userName = userName;
		this.birthDate = birthDate;
		this.residenceCoutry = residenceCoutry;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	public String getResidenceCoutry() {
		return residenceCoutry;
	}
	public void setResidenceCoutry(String residenceCoutry) {
		this.residenceCoutry = residenceCoutry;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
