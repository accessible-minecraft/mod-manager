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

import com.shoaibkhan.modmanager.gui.panels.ModsPanel;
import com.shoaibkhan.modmanager.profiles.ProfilesArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * @author shoaib
 */
public class ProfilesComboBox extends JComboBox<Object> {

    int filterer = 0;
    boolean isRemovingItems = false;

    public ProfilesComboBox() {
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
                    ModsPanel.modsComboBox.refresh();
                }
            } else {
                filterer = 0;
            }
        });
    }

    public final void refresh() {
        isRemovingItems = true;
        this.removeAllItems();
        Object[] list = ProfilesArrayList.profilesArrayList();
        for (Object object : list) {
            this.addItem(object);
        }
        isRemovingItems = false;
    }
}
