package com.atos.technicaltest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.atos.technicaltest.utils.Messages;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class TechnicalTestApplication {

	@Autowired
	private MessageSource messageSource;
	
	@PostConstruct
    public void init() {
		Messages.setMessageSource(messageSource);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(TechnicalTestApplication.class, args);
	}
	
}
