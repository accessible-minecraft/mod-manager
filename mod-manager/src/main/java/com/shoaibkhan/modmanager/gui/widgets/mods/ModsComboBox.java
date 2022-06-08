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
package com.shoaibkhan.modmanager.gui.widgets.mods;

import com.shoaibkhan.modmanager.configs.ModsJSON;
import com.shoaibkhan.modmanager.gui.panels.ModsPanel;
import com.shoaibkhan.modmanager.profiles.CurrentProfile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

/**
 * @author shoaib
 */
public class ModsComboBox extends JComboBox<Object> {

    int filterer = 0;
    boolean isRemovingItems = false;

    public ModsComboBox() {
        refresh();

        this.setFont(new java.awt.Font("Arial", 1, 18));

        setForeground(new Color(187, 187, 187));

        this.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                setForeground(Color.decode("#38ABFF"));
            }

            @Override
            public void focusLost(FocusEvent e) {
                setForeground(new Color(187, 187, 187));
            }

        });

        this.addItemListener(event -> {
            // Skips this method when removing a profile
            if (isRemovingItems) {
                return;
            }

            // For some reasons, this method runs 2 times when selecting an item so with
            // this it skips the second step
            filterer++;

            if (filterer == 1) {
                // Update mods panel
                if (ModsPanel.installUnistallButton != null) {
                    ModsPanel.installUnistallButton.refresh();
                    ModsPanel.changeVersionButton.refresh();
                }
            } else {
                filterer = 0;
            }
        });
    }

    public final void refresh() {
        isRemovingItems = true;
        this.removeAllItems(); // Remove current items from the combo box

        // Get supported mods list for the current profile
        double minecraftVersion = CurrentProfile.getCurrentProfileVersion();
        List<String> list = ModsJSON.getSupportedModsList(minecraftVersion);

        // Add mods to the combo box
        assert list != null;
        list.forEach(this::addItem);

        // Update modsList panel
        if (ModsPanel.installUnistallButton != null) {
            ModsPanel.installUnistallButton.refresh();
            ModsPanel.changeVersionButton.refresh();
        }

        // Refresh label
        ModsPanel.profileLabel.setText("Selected Profile: " + CurrentProfile.getCurrentProfileName() + " for version " + CurrentProfile.getCurrentProfileVersion());
        isRemovingItems = false;
    }
}
