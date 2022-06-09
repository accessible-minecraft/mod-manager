package com.shoaibkhan.modmanager.gui.widgets.profile;

import com.shoaibkhan.modmanager.configs.PropertiesJSON;
import com.shoaibkhan.modmanager.gui.dialog.ChooseVersionDialog;
import com.shoaibkhan.modmanager.gui.dialog.FolderChooserDialog;
import com.shoaibkhan.modmanager.gui.panels.ProfilesPanel;
import com.shoaibkhan.modmanager.gui.widgets.base.BaseButton;
import com.shoaibkhan.modmanager.profiles.CurrentProfile;
import com.shoaibkhan.modmanager.profiles.EditCurrentProfile;
import com.shoaibkhan.modmanager.utils.ActionResult;

import javax.swing.*;
import java.util.Objects;

public class EditProfileButton extends BaseButton {
    public EditProfileButton() {
        this.addActionListener(e -> {
            // Skip if the current profile is the default profile
            int current = 0;
            try {
                current = ProfilesPanel.profilesComboBox.getSelectedIndex();
            } catch (Exception ignored) {
            }

            String name = "default";
            if (current > 0) {
                name = JOptionPane.showInputDialog("Edit name of the profile", CurrentProfile.getCurrentProfileName());
                if (name == null) {
                    return;
                }
            }

            String directory = new FolderChooserDialog(null, false, true).choose();
            if (directory == null) {
                return;
            }

            // Get version to install from user
            Object[] supportedVersions = Objects.requireNonNull(PropertiesJSON.getSupportedVersionList()).toArray();
            int re = new ChooseVersionDialog(null, false).choose(supportedVersions);
            if (re < 0) {
                // Didn't chose anything, closed the option dialogue
                return;
            }


            ActionResult response = EditCurrentProfile.editCurrentProfile(name, directory, Double.parseDouble(supportedVersions[re].toString()));
            if (response != ActionResult.PASS) {
                JOptionPane.showMessageDialog(this, response.getDescription());
                return;
            }

            JOptionPane.showMessageDialog(null, "Profile edited", "Success", JOptionPane.PLAIN_MESSAGE);

            ProfilesPanel.profilesComboBox.refresh();
        });
    }
}
