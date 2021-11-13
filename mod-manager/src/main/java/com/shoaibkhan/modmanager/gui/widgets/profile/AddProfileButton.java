package com.shoaibkhan.modmanager.gui.widgets.profile;

import javax.swing.JOptionPane;
import javax.swing.plaf.DimensionUIResource;

import com.shoaibkhan.modmanager.gui.widgets.base.BaseButton;
import com.shoaibkhan.modmanager.profiles.AddNewProfile;
import com.shoaibkhan.modmanager.profiles.utils;
import com.shoaibkhan.modmanager.utils.ActionResult;

public class AddProfileButton extends BaseButton {
    public AddProfileButton(String text) {
        super(text);
        this.setPreferredSize(new DimensionUIResource(150, 50));

        this.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Enter name of the profile", "Enter profile name");
            if (name == null)
                return;
            double version = 0.0;
            try {
                version = Double.parseDouble(JOptionPane.showInputDialog("Enter minecraft version of the profile", "Enter minecraft version"));
                if (!(version == 1.16 || version == 1.17)) {
                    JOptionPane.showMessageDialog(this,
                            "Please enter correct version(only 1.16 and 1.17 are supported).");
                    return;
                }

            } catch (Exception exception) {
                exception.printStackTrace();
                JOptionPane.showMessageDialog(this, "Please enter correct version(only 1.16 and 1.17 are supported).");
                return;
            }
            String directory = JOptionPane.showInputDialog("Enter directory path", utils.getMinecraftDirectory());
            if (directory == null)
                return;

            ActionResult response = AddNewProfile.addNewProfile(name, directory, version);
            if (response != ActionResult.PASS) {
                JOptionPane.showMessageDialog(this, response.getDescription());
            }
        });
    }
}
