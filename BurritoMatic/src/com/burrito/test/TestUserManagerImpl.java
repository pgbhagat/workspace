package com.burrito.test;

import org.junit.Test;

import com.burritomatic.exception.UserException;
import com.burritomatic.user.IUserManager;
import com.burritomatic.user.User;
import com.burritomatic.user.UserManagerImpl;

import junit.framework.TestCase;
import org.junit.Assert;

public class TestUserManagerImpl extends TestCase {

	@Test
	public void testAddUser() {
		IUserManager manager = UserManagerImpl.getInstance();
		try {
			manager.addUser("Pranay", "Shah", "Pranay.ShahTest@gmail.com", "woodPassword", "98405558986",
					"Marketyard, Pune");
			User user = manager.getUserByEmail("Pranay.ShahTest@gmail.com");
			Assert.assertNotNull(user);
			Assert.assertEquals(user.getFirstname(), "Pranay");
		} catch (UserException e) {
			Assert.fail("Exception while adding user - " + e.getMessage());
		}
	}

	@Test
	public void testAddUserInvalidEmaild() {
		IUserManager manager = UserManagerImpl.getInstance();
		boolean failure = false;
		try {
			manager.addUser("Pranay", "Shah", "Pranay.Shah@gmailcom", "woodPassword", "98405558986",
					"Marketyard, Pune");
		} catch (UserException e) {
			failure = true;
		}
		if (!failure) {
			Assert.fail("No error while adding user with duplicate emailId.");
		}
		User user = manager.getUserByEmail("Pranay.Shah@gmailcom");
		Assert.assertNull(user);

	}

	@Test
	public void testAddUserDuplicateUser() {
		IUserManager manager = UserManagerImpl.getInstance();
		boolean failure = false;
		try {
			manager.addUser("Pranay", "Shah", "Pranay.Shah1@gmail.com", "woodPassword", "98405558986",
					"Marketyard, Pune");
			manager.addUser("Pranay", "Shah", "Pranay.Shah1@gmail.com", "woodPassword", "98405558986",
					"Marketyard, Pune");
			User user = manager.getUserByEmail("Pranay.Shah1@gmail.com");
			Assert.assertNull(user);
		} catch (UserException e) {
			failure = true;

		}
		if (!failure) {
			Assert.fail("Adding user with already registed email id show thrwo exception.");
		}

	}

	@Test
	public void testDeleteUser() {

	}

}
