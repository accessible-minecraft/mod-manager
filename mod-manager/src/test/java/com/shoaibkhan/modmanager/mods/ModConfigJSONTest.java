package com.shoaibkhan.modmanager.mods;

import org.junit.Test;

public class ModConfigJSONTest {
    @Test
    public void testGetVersionsList() {
        System.out.println(ModConfigJSON.getVersionsList("controls-menu-bug-fix", 1.17));
    }

    @Test
    public void testGetDownloadUrlList() {
        System.out.println(ModConfigJSON.getDownloadUrlList("controls-menu-bug-fix", 1.17));
    }

    @Test
    public void testGetFileNameList() {
        System.out.println(ModConfigJSON.getFileNameList("controls-menu-bug-fix", 1.17));
    }
}
