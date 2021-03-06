/*
 * The MIT License
 *
 * Copyright 2021 shoaib.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.shoaibkhan.modmanager;

import com.shoaibkhan.modmanager.configs.Config;
import com.shoaibkhan.modmanager.profiles.AddNewProfile;
import com.shoaibkhan.modmanager.profiles.CurrentProfile;
import com.shoaibkhan.modmanager.profiles.RemoveCurrentProfile;
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
    public void testGetCurrentProfileVersion() {
        System.out.println(CurrentProfile.getCurrentProfileVersion());
    }

    @Test
    public void testAddNewProfile() {
        AddNewProfile.addNewProfile("1.17", utils.getMinecraftDirectory() + "/home/1.17", 1.17);
        AddNewProfile.addNewProfile("Fabric-1.16", utils.getMinecraftDirectory() + "/home/Fabric-1.16", 1.16);
        AddNewProfile.addNewProfile("Fabric-1.17", utils.getMinecraftDirectory() + "/home/Fabric-1.17", 1.17);
        System.out.println(Config.getData());
    }

    @Test
    public void testRemoveCurrentProfile() {
        RemoveCurrentProfile.removeCurrentProfile();
        System.out.println(Config.getData());
    }
}
