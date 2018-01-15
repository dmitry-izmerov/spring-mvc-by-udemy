package ru.demi.springmvc.services.security;

public interface EncryptionService {
	String encryptPassword(String password);
	boolean checkPassword(String plainPwd, String encryptedPwd);
}
