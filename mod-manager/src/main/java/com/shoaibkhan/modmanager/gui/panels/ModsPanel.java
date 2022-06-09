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
import com.shoaibkhan.modmanager.gui.widgets.mods.ChangeVersionButton;
import com.shoaibkhan.modmanager.gui.widgets.mods.InstallOrUninstallButton;
import com.shoaibkhan.modmanager.gui.widgets.mods.ModsComboBox;
import com.shoaibkhan.modmanager.gui.widgets.mods.NvdaDllButton;
import com.shoaibkhan.modmanager.profiles.CurrentProfile;

import javax.swing.*;

public class ModsPanel extends JPanel {
    public static ChangeVersionButton changeVersionButton;
    public static BaseLabel profileLabel;
    public static InstallOrUninstallButton installUnistallButton;
    public static ModsComboBox modsComboBox;

    public ModsPanel() {
        initComponents();
    }

    private void initComponents() {
        JPanel row0 = new JPanel();
        JPanel row1 = new JPanel();
        JPanel row2 = new JPanel();

        BaseLabel headingLabel = new BaseLabel();
        profileLabel = new BaseLabel();
        modsComboBox = new ModsComboBox();
        installUnistallButton = new InstallOrUninstallButton();
        changeVersionButton = new ChangeVersionButton();
        NvdaDllButton installNVDAButton = new NvdaDllButton();

        setNextFocusableComponent(profileLabel);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        headingLabel.setText("Selected Profile: " + CurrentProfile.getCurrentProfileName() + " for version " + CurrentProfile.getCurrentProfileVersion());
        headingLabel.setNextFocusableComponent(headingLabel);
        row0.add(profileLabel);

        add(row0);

        headingLabel.setLabelFor(modsComboBox);
        headingLabel.setText("Select Mod :-");
        headingLabel.setNextFocusableComponent(modsComboBox);
        row1.add(headingLabel);

        modsComboBox.setNextFocusableComponent(installUnistallButton);
        row1.add(modsComboBox);

        add(row1);

        installUnistallButton.setNextFocusableComponent(changeVersionButton);
        row2.add(installUnistallButton);

        changeVersionButton.setText("Change Version");
        row2.add(changeVersionButton);

        installNVDAButton.setText("Install Nvda Dll");
        row2.add(installNVDAButton);

        add(row2);
    }
}
