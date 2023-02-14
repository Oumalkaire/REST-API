package com.atos.technicaltest.utils;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class Messages {

	private static MessageSource messageSource;
	
	public static void setMessageSource(MessageSource messageSource) {
		Messages.messageSource = messageSource;
	}
	
	public static String getMessage(String key) {
		return getMessageWithParam(key,null);
	}
	
	public static String getMessageWithParam(String key, @Nullable String[] args) {
		return messageSource.getMessage(key, args, Locale.getDefault());
	}
}
