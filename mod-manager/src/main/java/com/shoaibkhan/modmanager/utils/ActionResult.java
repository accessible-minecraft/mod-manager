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
package com.shoaibkhan.modmanager.utils;

public enum ActionResult {
    PASS("Success"), FAILED("Failed"), NAME_ALREADY_PRESENT("Profile with this name is already present."),
    DIRECTORY_ALREADY_PRESENT("Profile with this directory is already present."),
    INVALID_DIRECORY("Directory provided is not a valid directory"),
    CORRUPTED_JSON_RESETTED_TO_DEFAULT("Curropted config.json, config.json is now set to default"),
    CANNOT_REMOVE_DEFAULT_PROFILE("Cannot remove the default profile"), MOD_NOT_INSTALLED("Mod is not installed"),
    FAILED_TO_CREATE_DIRECTORY("Failed to create directory while extracting zip file"),
    ENTRY_IS_OUTSIDE_OF_TARGET_DIRECTORY("Entry is outside of target directory while extracting zip file"),
    CLOSE("closed");

    private final String description;

    private ActionResult(String s) {
        description = s;
    }

    public String getDescription() {
        return description;
    }
}
