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
import com.shoaibkhan.modmanager.gui.widgets.base.BaseButton;
import com.shoaibkhan.modmanager.profiles.RemoveCurrentProfile;
import com.shoaibkhan.modmanager.utils.ActionResult;
import javax.swing.JOptionPane;

/**
 *
 * @author shoaib
 */
public class RemoveProfileButton extends BaseButton {

    public RemoveProfileButton() {
        this.addActionListener(e -> {
            // Confirm again
            int res = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this profile??");
            if (res != 0) {
                return;
            }

            ActionResult removeCurrentProfileResponse = RemoveCurrentProfile.removeCurrentProfile();
            if (removeCurrentProfileResponse != ActionResult.PASS) {
                JOptionPane.showMessageDialog(this, removeCurrentProfileResponse.getDescription());
            }

            // Update mods panel
            if (ModsPanel.installUnistallButton != null) {
                ModsPanel.installUnistallButton.refresh();
                ModsPanel.changeVersionButton.refresh();
            }
        });
    }
}
