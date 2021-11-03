package com.shoaibkhan.modmanager.gui.panels;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import com.shoaibkhan.modmanager.gui.widgets.AddProfileButton;
import com.shoaibkhan.modmanager.gui.widgets.FocusableLabel;
import com.shoaibkhan.modmanager.gui.widgets.NextProfileButton;
import com.shoaibkhan.modmanager.gui.widgets.RemoveProfileButton;
import com.shoaibkhan.modmanager.profiles.CurrentProfile;

public class ProfilesPanel extends JPanel {
    public ProfilesPanel() {
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT);

        this.setLayout(layout);

        FocusableLabel curProfile = new FocusableLabel("Selected Profile: " + CurrentProfile.getCurrentProfileName());

        // To get the width of the text  to make the panel's width dynamic
        DimensionUIResource curProfileDimensions = new DimensionUIResource(
                curProfile.getFontMetrics(curProfile.getFont()).stringWidth(curProfile.getText()), 50);
        curProfile.setPreferredSize(new DimensionUIResource(curProfileDimensions.width + 20, 50));
        
        this.add(curProfile);

        NextProfileButton nextProfileButton = new NextProfileButton("Next Profile", curProfile);
        nextProfileButton.setPreferredSize(new DimensionUIResource(150, 50));
        this.add(nextProfileButton);

        AddProfileButton addProfileButton = new AddProfileButton("Add Profile");
        addProfileButton.setPreferredSize(new DimensionUIResource(150, 50));
        this.add(addProfileButton);

        RemoveProfileButton removeProfileButton = new RemoveProfileButton("Remove Profile", curProfile);
        removeProfileButton.setPreferredSize(new DimensionUIResource(150, 50));
        this.add(removeProfileButton);

        layout.minimumLayoutSize(this);
    }
}
