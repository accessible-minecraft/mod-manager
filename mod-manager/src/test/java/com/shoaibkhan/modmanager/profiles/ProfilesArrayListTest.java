package com.shoaibkhan.modmanager.profiles;

import org.junit.Test;

public class ProfilesArrayListTest {

	@Test
	public void testProfilesArrayList() {
		Object[] list = ProfilesArrayList.profilesArrayList();
		for (Object object : list) {
			System.out.println(object);
		}
	}

}
