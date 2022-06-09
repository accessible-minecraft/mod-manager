package com.shoaibkhan.modmanager.profiles;

import org.junit.Test;

public class utilsTest {

    @Test
    public void getMinecraftDirectory() {
        System.out.println(utils.getMinecraftDirectory());
    }

    @Test
    public void getOS() {
        System.out.println(utils.getOS());
    }

    @Test
    public void checkInvalidity() {
        System.out.println(utils.checkInvalidity(utils.getMinecraftDirectory()));
    }

    @Test
    public void getLatestMinecraftVersion() {
        System.out.println(utils.getLatestMinecraftVersion());
    }
}