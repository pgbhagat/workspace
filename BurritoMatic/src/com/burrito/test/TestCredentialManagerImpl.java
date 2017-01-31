package com.burrito.test;

import org.junit.Assert;
import org.junit.Test;

import com.burritomatic.user.CredentialManagerImpl;
import com.burritomatic.user.ICredentialManager;

import junit.framework.TestCase;

public class TestCredentialManagerImpl extends TestCase {
	
	@Test
	public void testValidateCredential() {
		ICredentialManager manager = new CredentialManagerImpl();
		manager.addUpdateCredential("Pranay.Shah@gmail.com", "goodWell");
		Assert.assertTrue(manager.validateCredential("Pranay.Shah@gmail.com", "goodWell"));
		Assert.assertFalse(manager.validateCredential("Pranay.Shah@gmail.com", "wrongPassword"));
	}
}
