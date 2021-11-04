package com.shoaibkhan.modmanager.configs;

import org.junit.Test;

public class ModsJSONTest {

    @Test
    public void testGetSupportedModsListFor1_17() {
        System.out.println(ModsJSON.getSupportedModsList(1.17));
    }

    @Test
    public void testGetSupportedModsListFor1_16() {
        System.out.println(ModsJSON.getSupportedModsList(1.16));
    }

    @Test
    public void testLoadIfNotPresent() {
        ModsJSON.loadIfNotPresent();
    }

    @Test
    public void testDeleteTempFolder() {
        ModsJSON.DeleteTempFolder();
    }

    @Test
    public void testGetModConfigFilePath() {
        System.out.println(ModsJSON.getModConfigFilePath("controls-menu-bug-fix"));
    }
}
