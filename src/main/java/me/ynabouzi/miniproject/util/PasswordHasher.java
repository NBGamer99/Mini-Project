package me.ynabouzi.miniproject.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordHasher {
	public static String hashPassword(String password) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
			return Base64.getEncoder().encodeToString(hash);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error hashing password");
			return null;
		}
	}
	public static boolean verifyPassword(String password, String hashedPassword) {
		String newHash = hashPassword(password);
		return hashedPassword.equals(newHash);
	}
}
