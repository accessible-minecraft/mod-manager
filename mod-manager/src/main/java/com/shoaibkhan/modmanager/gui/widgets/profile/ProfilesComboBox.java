/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shoaibkhan.modmanager.gui.widgets.profile;

import com.shoaibkhan.modmanager.gui.panels.ModsListPanel;
import com.shoaibkhan.modmanager.profiles.CurrentProfile;
import com.shoaibkhan.modmanager.profiles.ProfilesArrayList;
import com.shoaibkhan.modmanager.profiles.utils;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author shoaib
 */
public class ProfilesComboBox extends JComboBox<Object> {

    int filterer = 0;
    boolean isRemovingItems = false;

    public ProfilesComboBox() {
        refresh();
        
        this.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        this.setFont(new Font("Arial", Font.PLAIN, 16));

        this.addItemListener(event -> {
            // Skips this method when removing a profile
            if (isRemovingItems) {
                return;
            }

            // For some reasons, this method runs 2 times when selecting an item so with
            // this it skips the second step
            filterer++;

            if (filterer == 1) {
                // Check if options.txt is present in the profile
                if (!utils.isOptionsTxtPresent(CurrentProfile.getCurrentProfileDirectory())) {
                    JOptionPane.showMessageDialog(null,
                            "No options.txt file found!\n You can generate it by changing a setting from inside minecraft,\n like the render distance, graphics, remapping a key.",
                            "Important", JOptionPane.ERROR_MESSAGE);

                    // Select default profile
                    refresh();
                }

                // Update modsList panel
                ModsListPanel modsListPanel = new ModsListPanel();
                modsListPanel.start();
            } else {
                filterer = 0;
            }
        });
    }

    public void refresh() {
        isRemovingItems = true;
        this.removeAllItems();
        Object[] list = ProfilesArrayList.profilesArrayList();
        for (Object object : list) {
            this.addItem(object);
        }
        isRemovingItems = false;
    }    
}
