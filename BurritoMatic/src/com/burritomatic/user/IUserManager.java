package com.burritomatic.user;

import com.burritomatic.exception.UserException;

public interface IUserManager {

	public boolean addUser(String firstName, String lastName, String emailId, String password, String phone,
			String address) throws UserException;

	public boolean updateUser(String firstName, String lastName, String emailId, String password, String phone,
			String address) throws UserException;

	public boolean deleteUserByEmailId(String emailId) throws UserException;

	public User getUserByEmail(String emailId);

	public void reSendPassword(String emaildId) throws UserException;

	public boolean verifyUserCredentials(String emailId, String password);

}
