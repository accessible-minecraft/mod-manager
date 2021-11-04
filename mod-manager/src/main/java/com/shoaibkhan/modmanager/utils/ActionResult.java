package com.shoaibkhan.modmanager.utils;

public enum ActionResult {
    PASS("Success"), FAILED("Failed"), NAME_ALREADY_PRESENT("Profile with this name is already present."),
    DIRECTORY_ALREADY_PRESENT("Profile with this directory is already present."),
    INVALID_DIRECORY("Directory provided is not a valid directory"),
    CORRUPTED_JSON_RESETTED_TO_DEFAULT("Curropted config.json, config.json is now set to default"),
    CANNOT_REMOVE_DEFAULT_PROFILE("Cannot remove the default profile"), MOD_NOT_INSTALLED("Mod is not installed"),
    CLOSE("closed");

    private final String description;

    private ActionResult(String s) {
        description = s;
    }

    public String getDescription() {
        return description;
    }
}
