package com.burrito.manager;

import com.burritomatic.exception.UserException;
import com.burritomatic.user.User;
import com.burritomatic.user.UserManagerImpl;

public class UserPortalImpl implements IUserPortal {

	@Override
	public IBurritoMaticManager login(String emailId, String password) throws UserException {
		User user = UserManagerImpl.getInstance().getUserByEmail(emailId);
		if (user == null) {
			return null;
		}
		if (UserManagerImpl.getInstance().verifyUserCredentials(emailId, password)) {
			return new BurritoMaticManagerImpl(user);
		} else {
			return null;
		}
	}

	@Override
	public void logon(String firstName, String lastName, String emailId, String password, String phone, String address)
			throws UserException {
		UserManagerImpl.getInstance().addUser(firstName, lastName, emailId, password, phone, address);
	}

	@Override
	public void forgotPasscode(String emailId) throws UserException {
		UserManagerImpl.getInstance().reSendPassword(emailId);
	}

	@Override
	public void logout() {
		// TODO to be implemented..
	}
}
