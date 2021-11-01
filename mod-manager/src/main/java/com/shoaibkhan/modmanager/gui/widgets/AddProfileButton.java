package com.shoaibkhan.modmanager.gui.widgets;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.shoaibkhan.modmanager.profiles.AddNewProfile;
import com.shoaibkhan.modmanager.profiles.utils;
import com.shoaibkhan.modmanager.utils.ActionResult;

public class AddProfileButton extends JButton{
    public AddProfileButton(String text){
        super(text);
        this.setToolTipText("Add Profile Button");
        this.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Enter name of the profile");
            if (name == null)
                return;
            String directory = JOptionPane.showInputDialog("Enter directory path", utils.getMinecraftDirectory());
            if (directory == null)
                return;

            ActionResult response = AddNewProfile.addNewProfile(name, directory);
            if (response != ActionResult.PASS) {
                JOptionPane.showMessageDialog(this, response.getDescription());
            }
        });
    }
}
