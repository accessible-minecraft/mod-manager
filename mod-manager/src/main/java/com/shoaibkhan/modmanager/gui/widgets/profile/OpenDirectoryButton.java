package com.shoaibkhan.modmanager.gui.widgets.profile;

import com.shoaibkhan.modmanager.gui.widgets.base.BaseButton;
import com.shoaibkhan.modmanager.profiles.CurrentProfile;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class OpenDirectoryButton extends BaseButton {
    public OpenDirectoryButton() {
        this.addActionListener(e -> {
            try {
                File file = new File(CurrentProfile.getCurrentProfileDirectory());
                Desktop desktop = Desktop.getDesktop();
                desktop.open(file);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
