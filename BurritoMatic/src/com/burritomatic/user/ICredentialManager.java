package com.burritomatic.user;

public interface ICredentialManager {
	
	public void addUpdateCredential(String emailId, String password);

	public boolean validateCredential(String emailId, String password);
}
