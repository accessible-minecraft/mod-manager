package com.shoaibkhan.modmanager;

import com.shoaibkhan.modmanager.configs.Config;
import com.shoaibkhan.modmanager.profiles.AddNewProfile;
import com.shoaibkhan.modmanager.profiles.CurrentProfile;
import com.shoaibkhan.modmanager.profiles.RemoveCurrentProfile;
import com.shoaibkhan.modmanager.profiles.SelectNextProfile;
import com.shoaibkhan.modmanager.profiles.utils;

import org.junit.Test;

public class profilesTest {

    @Test
    public void testGetMinecraftDirectory() {
        System.out.println(utils.getMinecraftDirectory());
    }

    @Test
    public void testGetCurrentProfileName() {
        System.out.println(CurrentProfile.getCurrentProfileName());
    }

    @Test
    public void testGetCurrentProfileDirectory() {
        System.out.println(CurrentProfile.getCurrentProfileDirectory());
    }

    @Test
    public void testAddNewProfile() {
        AddNewProfile.addNewProfile("1.17", utils.getMinecraftDirectory() + "/home/1.17");
        AddNewProfile.addNewProfile("Fabric-1.16", utils.getMinecraftDirectory() + "/home/Fabric-1.16");
        AddNewProfile.addNewProfile("Fabric-1.17", utils.getMinecraftDirectory() + "/home/Fabric-1.17");
        System.out.println(Config.getData());
    }

    @Test
    public void testRemoveCurrentProfile() {
        RemoveCurrentProfile.removeCurrentProfile();
        System.out.println(Config.getData());
    }

    @Test
    public void testSelectNextProfile() {
        SelectNextProfile.selectNextProfiResult();
        System.out.println(Config.getData());
    }
}
