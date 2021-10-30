package com.shoaibkhan.modmanager;

import org.junit.Test;

public class profileManagerTest {

    @Test
    public void testGetMinecraftDirectory() {
        System.out.println(new profileManager().getMinecraftDirectory());
    }

    @Test
    public void testGetCurrentProfileName() {
        System.out.println(new profileManager().getCurrentProfileName());
    }

    @Test
    public void testGetCurrentProfileDirectory() {
        System.out.println(new profileManager().getCurrentProfileDirectory());
    }

    @Test
    public void testAddNewProfile() {
        new profileManager().addNewProfile("asdawa", new profileManager().getMinecraftDirectory() + "/hello");
        System.out.println(Config.getData());
    }

    @Test
    public void testRemoveCurrentProfile() {
        profileManager.removeCurentProfile();
        System.out.println("IN config");
        System.out.println(Config.getData());
    }
}
