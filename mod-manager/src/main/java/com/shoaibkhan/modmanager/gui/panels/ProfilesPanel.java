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
package com.shoaibkhan.modmanager.gui.panels;

import com.shoaibkhan.modmanager.gui.widgets.base.BaseLabel;
import com.shoaibkhan.modmanager.gui.widgets.profile.AddProfileButton;
import com.shoaibkhan.modmanager.gui.widgets.profile.EditProfileButton;
import com.shoaibkhan.modmanager.gui.widgets.profile.ProfilesComboBox;
import com.shoaibkhan.modmanager.gui.widgets.profile.RemoveProfileButton;

import javax.swing.*;

public class ProfilesPanel extends javax.swing.JPanel {
    public static BaseLabel hLabel;
    public static ProfilesComboBox profilesComboBox;

    public ProfilesPanel() {
        initComponents();
    }

    private void initComponents() {
        JPanel row0 = new JPanel();
        JPanel row1 = new JPanel();

        hLabel = new BaseLabel();
        profilesComboBox = new ProfilesComboBox();
        AddProfileButton addProfileButton = new AddProfileButton();
        EditProfileButton editProfileButton = new EditProfileButton();
        RemoveProfileButton removeProfileButton = new RemoveProfileButton();

        setNextFocusableComponent(hLabel);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        hLabel.setLabelFor(profilesComboBox);
        hLabel.setText("Select Profile:-   ");
        hLabel.setNextFocusableComponent(profilesComboBox);
        row0.add(hLabel);

        profilesComboBox.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        profilesComboBox.setNextFocusableComponent(editProfileButton);
        row0.add(profilesComboBox);

        editProfileButton.setText("Edit Current Profile");
        editProfileButton.setToolTipText("");
        editProfileButton.setNextFocusableComponent(addProfileButton);
        row0.add(editProfileButton);

        add(row0);

        addProfileButton.setText("Add New Profile");
        addProfileButton.setToolTipText("");
        addProfileButton.setNextFocusableComponent(removeProfileButton);
        row1.add(addProfileButton);

        removeProfileButton.setText("Remove Current Profile");
        row1.add(removeProfileButton);

        add(row1);
    }
}
