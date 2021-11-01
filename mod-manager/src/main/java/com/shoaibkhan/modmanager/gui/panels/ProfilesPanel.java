package com.shoaibkhan.modmanager.gui.panels;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import com.shoaibkhan.modmanager.gui.widgets.AddProfileButton;
import com.shoaibkhan.modmanager.gui.widgets.FocusableLabel;
import com.shoaibkhan.modmanager.gui.widgets.NextProfileButton;
import com.shoaibkhan.modmanager.gui.widgets.RemoveProfileButton;
import com.shoaibkhan.modmanager.profiles.CurrentProfile;

public class ProfilesPanel extends JPanel {
    public ProfilesPanel(GridLayout gd) {
        this.setLayout(gd);
        FocusableLabel curProfile = new FocusableLabel("Selected Profile: " + CurrentProfile.getCurrentProfileName());
        this.add(curProfile);

        NextProfileButton nextProfileButton = new NextProfileButton("Next Profile", curProfile);
        this.add(nextProfileButton);

        AddProfileButton addProfileButton = new AddProfileButton("Add Profile");
        this.add(addProfileButton);

        RemoveProfileButton removeProfileButton = new RemoveProfileButton("Remove Profile", curProfile);
        this.add(removeProfileButton);

        this.setPreferredSize(new DimensionUIResource(700, 50));
        gd.minimumLayoutSize(this);
    }
}
