package com.shoaibkhan.modmanager.gui.widgets;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.plaf.DimensionUIResource;

import com.shoaibkhan.modmanager.gui.panels.ModsListPanel;
import com.shoaibkhan.modmanager.profiles.CurrentProfile;
import com.shoaibkhan.modmanager.profiles.SelectNextProfile;
import com.shoaibkhan.modmanager.utils.ActionResult;

public class NextProfileButton extends JButton {
    public NextProfileButton(String text, FocusableLabel forLabel) {
        super(text);
        this.setToolTipText("Select next profile button");

        this.addActionListener(e -> {
            ActionResult response = SelectNextProfile.selectNextProfiResult();
            if (response != ActionResult.PASS) {
                JOptionPane.showMessageDialog(this, response.getDescription());
            }

            forLabel.setText("Selected Profile: " + CurrentProfile.getCurrentProfileName());
            forLabel.setToolTipText("Selected Profile: " + CurrentProfile.getCurrentProfileName());
            DimensionUIResource curProfileDimensions = new DimensionUIResource(
                    forLabel.getFontMetrics(forLabel.getFont()).stringWidth(forLabel.getText()), 50);
            forLabel.setPreferredSize(new DimensionUIResource(curProfileDimensions.width + 20, 50));

            // Update modsList panel
            ModsListPanel modsListPanel = new ModsListPanel();
            modsListPanel.start();
        });
    }
}
