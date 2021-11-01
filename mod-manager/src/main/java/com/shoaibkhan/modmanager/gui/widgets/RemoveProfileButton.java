package com.shoaibkhan.modmanager.gui.widgets;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.shoaibkhan.modmanager.profiles.CurrentProfile;
import com.shoaibkhan.modmanager.profiles.RemoveCurrentProfile;
import com.shoaibkhan.modmanager.utils.ActionResult;

public class RemoveProfileButton extends JButton {
    public RemoveProfileButton(String text, FocusableLabel forLabel){
        super(text);
        this.setToolTipText("Remoce Current Profile Button");
        this.addActionListener(e -> {
            ActionResult removeCurrentProfileResponse = RemoveCurrentProfile.removeCurrentProfile();
            if (removeCurrentProfileResponse != ActionResult.PASS) {
                JOptionPane.showMessageDialog(this, removeCurrentProfileResponse.getDescription());
            }

            forLabel.setText("Selected Profile: " + CurrentProfile.getCurrentProfileName());
            forLabel.setToolTipText("Selected Profile: " + CurrentProfile.getCurrentProfileName());
        });
    }
}
