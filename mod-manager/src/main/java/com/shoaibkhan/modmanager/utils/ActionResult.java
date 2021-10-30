package com.shoaibkhan.modmanager.utils;

public enum ActionResult {
    PASS("Success"), FAILED("Failes"), NAME_ALREADY_PRESENT("Profile with this name is already present."),
    DIRECTORY_ALREADY_PRESENT("Profile with this directory is already present."),
    INVALID_DIRECORY("Directory provided is not a valid directory"),
     CORRUPTED_JSON_RESETTED_TO_DEFAULT("Curropted config.json, config.json is now set to default");

    private final String description;

    private ActionResult(String s) {
        description = s;
    }

    public String getDescription() {
        return description;
    }
}
