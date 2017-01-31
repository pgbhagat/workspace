package com.burrito.test;

import org.junit.Assert;
import org.junit.Test;

import com.burrito.manager.IBurritoMaticManager;
import com.burrito.manager.IUserPortal;
import com.burrito.manager.UserPortalImpl;
import com.burritomatic.exception.UserException;
import com.burritomatic.user.IUserManager;
import com.burritomatic.user.UserManagerImpl;

import junit.framework.TestCase;

public class TestUserPortalImpl extends TestCase {

	@Test
	public void testLogin() {
		IUserPortal portal = new UserPortalImpl();
		IUserManager manager = UserManagerImpl.getInstance();
		try {
			manager.addUser("Pranay", "Shah", "Pranay.Shah@gmail.com", "woodPassword", "98405558986",
					"Marketyard, Pune");
			IBurritoMaticManager burritoMaticManager = portal.login("Pranay.Shah@gmail.com", "woodPassword");
			Assert.assertNotNull(burritoMaticManager);
			burritoMaticManager = portal.login("Pranay.Shah@gmail.com", "wrongPassword");
			Assert.assertNull(burritoMaticManager);
		} catch (UserException e) {
			Assert.fail("Exception while adding user - " + e.getMessage());
		}

	}

	@Test
	public void testLogon() {

	}
}
