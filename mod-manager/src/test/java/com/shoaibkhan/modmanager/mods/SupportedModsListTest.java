package com.shoaibkhan.modmanager.mods;

import com.shoaibkhan.modmanager.configs.SupportedMods;

import org.junit.Test;

public class SupportedModsListTest {
    @Test
    public void testGetSupportedModsList() {
        System.out.println(SupportedMods.getSupportedModsList());
    }
}
