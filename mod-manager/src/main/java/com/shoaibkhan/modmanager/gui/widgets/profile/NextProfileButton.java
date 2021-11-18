package com.shoaibkhan.modmanager.gui.widgets.profile;

import javax.swing.JOptionPane;
import javax.swing.plaf.DimensionUIResource;

import com.shoaibkhan.modmanager.gui.panels.ModsListPanel;
import com.shoaibkhan.modmanager.gui.widgets.base.BaseButton;
import com.shoaibkhan.modmanager.gui.widgets.base.BaseLabel;
import com.shoaibkhan.modmanager.profiles.CurrentProfile;
import com.shoaibkhan.modmanager.profiles.SelectNextProfile;
import com.shoaibkhan.modmanager.utils.ActionResult;

public class NextProfileButton extends BaseButton {
    public NextProfileButton(String text, BaseLabel forLabel) {
        super(text);
        this.setPreferredSize(new DimensionUIResource(150, 50));

        this.addActionListener(e -> {
            ActionResult response = SelectNextProfile.selectNextProfiResult();
            if (response != ActionResult.PASS) {
                JOptionPane.showMessageDialog(this, response.getDescription());
            }

            forLabel.setText("Selected Profile: " + CurrentProfile.getCurrentProfileName());
            forLabel.refreshSize();

            // Update modsList panel
            ModsListPanel modsListPanel = new ModsListPanel();
            modsListPanel.start();
        });
    }
}
