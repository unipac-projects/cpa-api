package br.com.unipac.cpa.model.service.impl;

import br.com.unipac.cpa.model.service.PasswordCrypto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordCryptoImpl implements PasswordCrypto {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public String encrypt(String str) {
		return passwordEncoder.encode(str);
	}

	@Override
	public boolean matches(String rawPassword, String encodedPassword) {
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}
}