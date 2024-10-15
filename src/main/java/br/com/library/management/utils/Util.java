package br.com.library.management.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class Util {
	
	private static final String EMAIL_REGEX ="(?=^.{4,40}$)[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,4}$";

	public static boolean isValid(String email) {
		return email.matches(EMAIL_REGEX);
	}
	 public static String generateShortID() {
	        return UUID.randomUUID().toString().replace("-", "").substring(0, 12);
	    }
	
	private static final  DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	public static String formatDate(LocalDateTime dateTime) {
		return dateTime.format(FORMATTER);
	}
	
	public static Long generateReducedUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.getMostSignificantBits();
	}
	
	
}

