package com.shoaibkhan.modmanager.gui.panels;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import com.shoaibkhan.modmanager.gui.widgets.common.ButtonLabel;
import com.shoaibkhan.modmanager.gui.widgets.common.HeadingLabel;
import com.shoaibkhan.modmanager.gui.widgets.profile.AddProfileButton;
import com.shoaibkhan.modmanager.gui.widgets.profile.NextProfileButton;
import com.shoaibkhan.modmanager.gui.widgets.profile.RemoveProfileButton;
import com.shoaibkhan.modmanager.profiles.CurrentProfile;

public class ProfilesPanel extends JPanel {
    public ProfilesPanel() {
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);

        // Add Heading Before Profile panel list
        HeadingLabel headingLabel = new HeadingLabel("Profiles:-");
        JPanel headingPanel = new JPanel();
        headingPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        headingPanel.add(headingLabel);
        this.add(headingPanel);

        JPanel profilePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        ButtonLabel curProfile = new ButtonLabel("Selected Profile: " + CurrentProfile.getCurrentProfileName());
        profilePanel.add(curProfile);

        NextProfileButton nextProfileButton = new NextProfileButton("Next Profile", curProfile);
        nextProfileButton.setPreferredSize(new DimensionUIResource(150, 50));
        profilePanel.add(nextProfileButton);

        AddProfileButton addProfileButton = new AddProfileButton("Add Profile");
        addProfileButton.setPreferredSize(new DimensionUIResource(150, 50));
        profilePanel.add(addProfileButton);

        RemoveProfileButton removeProfileButton = new RemoveProfileButton("Remove Profile", curProfile);
        removeProfileButton.setPreferredSize(new DimensionUIResource(150, 50));
        profilePanel.add(removeProfileButton);

        this.add(profilePanel);
        layout.minimumLayoutSize(this);
    }
}
