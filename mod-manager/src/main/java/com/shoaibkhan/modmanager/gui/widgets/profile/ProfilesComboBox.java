package com.shoaibkhan.modmanager.gui.widgets.profile;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;

import com.shoaibkhan.modmanager.profiles.ProfilesArrayList;

public class ProfilesComboBox extends JComboBox<Object> {
	public ProfilesComboBox() {
		super(ProfilesArrayList.profilesArrayList());
		this.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		this.setFont(new Font("Arial", Font.PLAIN, 16));

	}

	public void refresh() {
		this.removeAllItems();
		Object[] list = ProfilesArrayList.profilesArrayList();
		for (Object object : list) {
			this.addItem(object);
		}
	}
}
