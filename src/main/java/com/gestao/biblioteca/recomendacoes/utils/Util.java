package com.gestao.biblioteca.recomendacoes.utils;

import java.util.UUID;

public class Util {
	
	private static final String EMAIL_REGEX ="(?=^.{4,40}$)[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,4}$";

	public static boolean isValid(String email) {
		return email.matches(EMAIL_REGEX);
	}
	
	public static Long geneateShortUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.getMostSignificantBits();
	}
}
