package com.shoaibkhan.modmanager.profiles;

import org.junit.Test;

public class utilsTest {

	@Test
	public void testCheckValidity() {
		System.out.println(utils.checkValidity(utils.getMinecraftDirectory()));
	}

	@Test
	public void testIsOptionsTxtPresent() {
		System.out.println(utils.isOptionsTxtPresent(utils.getMinecraftDirectory()));
	}

}
