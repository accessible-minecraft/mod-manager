package com.shoaibkhan.modmanager.configs;

import org.junit.Test;

public class SupportedModsTest {

    @Test
    public void testGetSupportedModsListFor1_17() {
        System.out.println(SupportedMods.getSupportedModsListFor1_17());
    }

    @Test
    public void testGetSupportedModsListFor1_16() {
        System.out.println(SupportedMods.getSupportedModsListFor1_16());
    }

    @Test
    public void testLoadIfNotPresent() {
        SupportedMods.getSupportedModsListFor1_17();
    }

    @Test
    public void testDeleteTemporaryFile() {
        SupportedMods.DeleteTemporaryFile();
    }
}
