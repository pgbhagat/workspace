package com.burritomatic.user;

import java.util.HashMap;
import java.util.Map;

import com.burritomatic.exception.UserException;

public class UserManagerImpl implements IUserManager {

	Map<String, User> userStore = new HashMap<String, User>();
	CredentialManagerImpl credentialManager = new CredentialManagerImpl();

	private static UserManagerImpl instance;

	private UserManagerImpl() {
	}

	
	public static UserManagerImpl getInstance() {
		if (instance == null) {
			synchronized (UserManagerImpl.class) {
				if (instance == null) {
					instance = new UserManagerImpl();
				}
			}
		}
		return instance;
	}

	public boolean addUser(String firstName, String lastName, String emailId, String password, String phone,
			String address) throws UserException {

		if (firstName == null || lastName == null || firstName.isEmpty() || lastName.isEmpty()) {
			throw new UserException("Provide firstname lastname for the user");
		}
		if (!isValidEmailId(emailId)) {
			throw new UserException("Provide a valid email address");
		}
		if (password == null || password.isEmpty()) {
			throw new UserException("Provide valid passCode");
		}

		synchronized (userStore) {
			if (userStore.containsKey(emailId.toLowerCase())) {
				throw new UserException(
						"User with email id - " + emailId + " already exist, try recovering the passcode");
			}
		}

		User user = new User();
		user.setFirstname(firstName);
		user.setLastname(lastName);
		user.setAddress(address);
		user.setEmailId(emailId);
		user.setPhone(phone);

		synchronized (userStore) {
			userStore.put(user.getEmailId().toLowerCase(), user);
			credentialManager.addUpdateCredential(emailId, password);
		}

		return true;
	}

	private boolean isValidEmailId(String emailId) throws UserException {
		if (emailId == null || emailId.isEmpty()) {
			return false;
		}
		if (emailId.contains("@") && emailId.contains(".") && emailId.lastIndexOf("@") < emailId.lastIndexOf("."))
			return true;
		else
			return false;
	}

	@Override
	public boolean deleteUserByEmailId(String emailId) throws UserException {
		if (isValidEmailId(emailId)) {
			synchronized (userStore) {
				if (userStore.remove(emailId.toLowerCase()) == null)
					return false;
			}
		}
		return true;
	}

	@Override
	public User getUserByEmail(String emailId) {
		synchronized (userStore) {
			return userStore.get(emailId.toLowerCase());
		}
	}

	@Override
	public void reSendPassword(String emailId) throws UserException {
		// TODO Send the passcode for the user to his emailid/phone no.
		// Not implemented yet..

	}

	@Override
	public boolean updateUser(String firstName, String lastName, String emailId, String passCode, String phone,
			String address) throws UserException {
		// TODO Not implemented yet...
		return false;
	}


	@Override
	public boolean verifyUserCredentials(String emailId, String password) {
		return credentialManager.validateCredential(emailId, password);
	}

}
