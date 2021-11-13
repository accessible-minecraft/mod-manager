package com.shoaibkhan.modmanager.gui.widgets.profile;

import javax.swing.JOptionPane;
import javax.swing.plaf.DimensionUIResource;

import com.shoaibkhan.modmanager.gui.panels.ModsListPanel;
import com.shoaibkhan.modmanager.gui.widgets.base.BaseButton;
import com.shoaibkhan.modmanager.gui.widgets.common.ButtonLabel;
import com.shoaibkhan.modmanager.profiles.CurrentProfile;
import com.shoaibkhan.modmanager.profiles.RemoveCurrentProfile;
import com.shoaibkhan.modmanager.utils.ActionResult;

public class RemoveProfileButton extends BaseButton {
    public RemoveProfileButton(String text, ButtonLabel forLabel) {
        super(text);
        this.setPreferredSize(new DimensionUIResource(150, 50));

        this.addActionListener(e -> {
            // Confirm again
            int res = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this profile??");
            if (res != 0)
                return;

            ActionResult removeCurrentProfileResponse = RemoveCurrentProfile.removeCurrentProfile();
            if (removeCurrentProfileResponse != ActionResult.PASS) {
                JOptionPane.showMessageDialog(this, removeCurrentProfileResponse.getDescription());
            }

            forLabel.setText("Selected Profile: " + CurrentProfile.getCurrentProfileName());
            forLabel.refreshSize();

            // Update modsList panel
            ModsListPanel modsListPanel = new ModsListPanel();
            modsListPanel.start();
        });
    }
}
