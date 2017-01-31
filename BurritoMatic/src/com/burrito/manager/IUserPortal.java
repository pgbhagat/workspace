package com.burrito.manager;

import com.burritomatic.exception.UserException;

public interface IUserPortal {

	public IBurritoMaticManager login(String emailId, String passCode) throws UserException;

	public void logon(String firstName, String lastName, String emailId, String passCode, String phone, String address) throws UserException;

	public void forgotPasscode(String emailId) throws UserException;

	public void logout();
}
