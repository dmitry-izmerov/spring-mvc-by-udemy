package ru.demi.springmvc.services.security;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EncryptionServiceImpl implements EncryptionService {

	@Autowired
	private StrongPasswordEncryptor passwordEncryptor;

	@Override
	public String encryptPassword(String password) {
		return passwordEncryptor.encryptPassword(password);
	}

	@Override
	public boolean checkPassword(String plainPwd, String encryptedPwd) {
		return passwordEncryptor.checkPassword(plainPwd, encryptedPwd);
	}
}
