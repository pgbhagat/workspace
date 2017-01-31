package com.burritomatic.user;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CredentialManagerImpl implements ICredentialManager {

	Map<String, String> credentialStore = new ConcurrentHashMap<>();

	public void addUpdateCredential(String emailId, String password) {
		credentialStore.put(emailId, password);
	}

	public boolean validateCredential(String emailId, String password) {
		String pass = credentialStore.get(emailId);
		return pass != null && pass.equals(password);
	}

}
