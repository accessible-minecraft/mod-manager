package com.shoaibkhan.modmanager;

import com.shoaibkhan.modmanager.Config.Config;
import com.shoaibkhan.modmanager.profiles.addNewProfile;
import com.shoaibkhan.modmanager.profiles.currentProfile;
import com.shoaibkhan.modmanager.profiles.removeCurrentProfile;
import com.shoaibkhan.modmanager.profiles.utils;

import org.junit.Test;

public class profilesTest {

    @Test
    public void testGetMinecraftDirectory() {
        System.out.println(utils.getMinecraftDirectory());
    }

    @Test
    public void testGetCurrentProfileName() {
        System.out.println(currentProfile.getCurrentProfileName());
    }

    @Test
    public void testGetCurrentProfileDirectory() {
        System.out.println(currentProfile.getCurrentProfileDirectory());
    }

    @Test
    public void testAddNewProfile() {
        new addNewProfile("1.17", utils.getMinecraftDirectory() + "/home/1.17");
        System.out.println(Config.getData());
    }

    @Test
    public void testRemoveCurrentProfile() {
        new removeCurrentProfile();
        System.out.println(Config.getData());
    }
}
