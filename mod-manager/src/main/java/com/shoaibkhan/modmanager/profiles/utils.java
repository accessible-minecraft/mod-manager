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
package com.shoaibkhan.modmanager.profiles;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class utils {

    public static enum OS {
        WINDOWS, MACOS, LINUX;
    }

    public static String getMinecraftDirectory() {
        OS currentOs = getOS();
        String homeDirectory = "";
        StringBuilder minecraftDirectory = new StringBuilder("");

        switch (currentOs) {
            case WINDOWS: {
                homeDirectory = System.getenv("APPDATA");
                minecraftDirectory = new StringBuilder(homeDirectory);
                minecraftDirectory.append("\\.minecraft");
                break;
            }
            case LINUX: {
                homeDirectory = System.getProperty("user.home");
                minecraftDirectory = new StringBuilder(homeDirectory);
                minecraftDirectory.append("/.minecraft");
                break;
            }
            case MACOS: {
                homeDirectory = System.getProperty("user.home");
                minecraftDirectory = new StringBuilder(homeDirectory);
                minecraftDirectory.append("/Library/Application Support/minecraft");
                break;
            }
        }

        if (Files.exists(Paths.get(minecraftDirectory.toString()))) {
            return minecraftDirectory.toString();
        } else {
            return homeDirectory;
        }
    }

    public static OS getOS() {
        String osName = System.getProperty("os.name").toLowerCase();

        if (osName.contains("win")) {
            return OS.WINDOWS;
        } else if (osName.contains("mac")) {
            return OS.MACOS;
        } else if (osName.contains("nix") || osName.contains("nux") || osName.indexOf("aix") > 0) {
            return OS.LINUX;
        }
        return OS.LINUX;
    }

    public static boolean checkValidity(String path) {
        if (!Files.exists(Paths.get(path).toAbsolutePath())) {
            return false;
        }

        File folder = new File(path);

        try {
            List<String> listOfItemsInFolder = Arrays.asList(folder.list());

            boolean hasSaves = listOfItemsInFolder.contains("saves");

            return hasSaves;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean isOptionsTxtPresent(String path) {
        if (!Files.exists(Paths.get(path).toAbsolutePath())) {
            return false;
        }

        File folder = new File(path);

        try {
            List<String> listOfItemsInFolder = Arrays.asList(folder.list());

            boolean hasOptionsTXT = listOfItemsInFolder.contains("options.txt");

            return hasOptionsTXT;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
