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
}
