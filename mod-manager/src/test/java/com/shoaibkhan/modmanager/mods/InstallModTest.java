package com.shoaibkhan.modmanager.mods;

import org.junit.Test;

public class InstallModTest {

    @Test
    public void testInstallMod() {
        InstallMod.installMod("controls-menu-bug-fix", 1.17);
    }
}
