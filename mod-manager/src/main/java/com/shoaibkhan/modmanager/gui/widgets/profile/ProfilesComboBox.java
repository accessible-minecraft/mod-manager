package com.shoaibkhan.modmanager.gui.widgets.profile;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;

import com.shoaibkhan.modmanager.gui.panels.ModsListPanel;
import com.shoaibkhan.modmanager.profiles.ProfilesArrayList;

public class ProfilesComboBox extends JComboBox<Object> {
	int filterer = 0;
	boolean isRemovingItems = false;

	public ProfilesComboBox() {
		super(ProfilesArrayList.profilesArrayList());
		this.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		this.setFont(new Font("Arial", Font.PLAIN, 16));

		this.addItemListener(event -> {
			// Skips this method when removing a profile
			if (isRemovingItems)
				return;

			// For some reasons, this method runs 2 times when selecting an item so with
			// this it skips the second step
			filterer++;

			if (filterer == 1) {
				// Update modsList panel
				ModsListPanel modsListPanel = new ModsListPanel();
				modsListPanel.start();
			} else
				filterer = 0;
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
