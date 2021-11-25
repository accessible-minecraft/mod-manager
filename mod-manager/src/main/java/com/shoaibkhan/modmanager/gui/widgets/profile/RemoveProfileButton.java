/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shoaibkhan.modmanager.gui.widgets.profile;

import com.shoaibkhan.modmanager.gui.panels.ModsListPanel;
import com.shoaibkhan.modmanager.profiles.RemoveCurrentProfile;
import com.shoaibkhan.modmanager.utils.ActionResult;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author shoaib
 */
public class RemoveProfileButton extends JButton {

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

            // Update modsList panel
            ModsListPanel modsListPanel = new ModsListPanel();
            modsListPanel.start();
        });
    }
}
