package com.example.project.validation;

import java.util.regex.Pattern;

public class Validation {
	
	public static boolean isValidEmail(String email) {
		boolean b = Pattern.matches("[a-zA-Z0-9!#$%&*-+/=']{1,64}+[@]{1}+[A-Za-z0-9-]{1,254}+[.]+[A-Za-z]{2,3}", email);
		return b;
	}

	public static boolean isValidPassword(String pwd) {
		boolean b = Pattern.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%^&-+=()])(?=\\S+$).{8, 20}$",pwd);
		return b;
	}
}
