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
package com.shoaibkhan.modmanager.gui.widgets.profile;

import com.shoaibkhan.modmanager.gui.panels.ProfilesPanel;
import com.shoaibkhan.modmanager.gui.widgets.base.BaseButton;
import com.shoaibkhan.modmanager.profiles.AddNewProfile;
import com.shoaibkhan.modmanager.profiles.utils;
import com.shoaibkhan.modmanager.utils.ActionResult;
import javax.swing.JOptionPane;

/**
 *
 * @author shoaib
 */
public class AddProfileButton extends BaseButton {

    public AddProfileButton() {
        this.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Enter name of the profile", "Enter profile name");
            if (name == null) {
                return;
            }

            String directory = JOptionPane.showInputDialog("Enter directory path", utils.getMinecraftDirectory());
            if (directory == null) {
                return;
            }

            ActionResult response = AddNewProfile.addNewProfile(name, directory);
            if (response != ActionResult.PASS) {
                JOptionPane.showMessageDialog(this, response.getDescription());
            }

            // Check if options.txt is present in the profile
            if (!utils.isOptionsTxtPresent(directory)) {
                JOptionPane.showMessageDialog(null,
                        "Profile added but there was no options.txt file found!\n You can generate it by changing a setting from inside minecraft,\n like the render distance, graphics, remapping a key.",
                        "Important", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Profile added", "Success", JOptionPane.DEFAULT_OPTION);
            }

            ProfilesPanel.profilesComboBox.refresh();
        });
    }
}
