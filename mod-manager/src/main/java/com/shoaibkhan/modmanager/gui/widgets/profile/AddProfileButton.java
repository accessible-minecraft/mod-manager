package com.shoaibkhan.modmanager.gui.widgets.profile;

import javax.swing.JOptionPane;
import javax.swing.plaf.DimensionUIResource;

import com.shoaibkhan.modmanager.gui.panels.ProfilesPanel;
import com.shoaibkhan.modmanager.gui.widgets.base.BaseButton;
import com.shoaibkhan.modmanager.profiles.AddNewProfile;
import com.shoaibkhan.modmanager.profiles.utils;
import com.shoaibkhan.modmanager.utils.ActionResult;

public class AddProfileButton extends BaseButton {
	public AddProfileButton(String text) {
		super(text);
		this.setPreferredSize(new DimensionUIResource(150, 50));

		this.addActionListener(e -> {
			String name = JOptionPane.showInputDialog("Enter name of the profile", "Enter profile name");
			if (name == null)
				return;

			String directory = JOptionPane.showInputDialog("Enter directory path", utils.getMinecraftDirectory());
			if (directory == null)
				return;

			ActionResult response = AddNewProfile.addNewProfile(name, directory);
			if (response != ActionResult.PASS) {
				JOptionPane.showMessageDialog(this, response.getDescription());
			}

			// Check if options.txt is present in the profile
			if (!utils.isOptionsTxtPresent(directory)) {
				JOptionPane.showMessageDialog(null,
						"Profile added but there was no options.txt file found!\n You can generate it by changing a setting from inside minecraft,\n like the render distance, graphics, remapping a key.",
						"Important", JOptionPane.WARNING_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Profile added", "Success", JOptionPane.DEFAULT_OPTION);
			}

			ProfilesPanel.profilesComboBox.refresh();
		});
	}
}
