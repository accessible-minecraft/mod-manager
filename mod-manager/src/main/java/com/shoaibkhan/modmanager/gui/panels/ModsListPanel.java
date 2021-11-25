package com.shoaibkhan.modmanager.gui.panels;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JPanel;

import com.shoaibkhan.modmanager.configs.ModsJSON;
import com.shoaibkhan.modmanager.gui.Gui;
import com.shoaibkhan.modmanager.gui.MainFrame;
import com.shoaibkhan.modmanager.gui.widgets.base.BaseLabel;
import com.shoaibkhan.modmanager.gui.widgets.mods.ChangeVersionButton;
import com.shoaibkhan.modmanager.gui.widgets.mods.InstallOrUninstallButton;
import com.shoaibkhan.modmanager.gui.widgets.mods.NvdaDllButton;
import com.shoaibkhan.modmanager.mods.CheckIfInstalled;
import com.shoaibkhan.modmanager.profiles.CurrentProfile;
import com.shoaibkhan.modmanager.profiles.utils;

public class ModsListPanel extends Thread {
	public Boolean isRunning = null;

	public void run() {
		isRunning = true;


		ModsJSON.loadIfNotPresent();

		// Remove all components in modsListPanel
		MainFrame.modsPanel.removeAll();
                
                System.err.println("Here");

		// Get supported mods list and panels for each mod
		double minecraftVersion = CurrentProfile.getCurrentProfileVersion();

		// Add Heading Before mods list
		BaseLabel headingLabel = new BaseLabel();
                headingLabel.setText("Supported Mods for " + minecraftVersion + ":-");
		JPanel headingPanel = new JPanel();
		headingPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		headingPanel.add(headingLabel);

		MainFrame.modsPanel.add(headingPanel);

		List<String> modsList = ModsJSON.getSupportedModsList(minecraftVersion);

		for (String modName : modsList) {
			JPanel panel = new JPanel();
			panel.setLayout(new FlowLayout(FlowLayout.LEFT));

			boolean isInstalled = CheckIfInstalled.checkIfInstalled(modName);

			// Remove (-) from mod name
			String modifiedName = modName;
			if (modName.contains("-"))
				modifiedName = modifiedName.replaceAll("-", " ");

			BaseLabel modNameLabel = new BaseLabel();
                        modNameLabel.setText(modifiedName + (isInstalled ? " (installed)" : " (not installed)"));
			panel.add(modNameLabel);

			InstallOrUninstallButton installOrUninstall = new InstallOrUninstallButton(
					isInstalled ? "Uninstall" : "Install", isInstalled, modName, minecraftVersion);
			panel.add(installOrUninstall);

			ChangeVersionButton changeVersionButton = new ChangeVersionButton("Change Version", modName,
					minecraftVersion);
			// Disable if the mod is not installed
			changeVersionButton.setEnabled(true);
			if (!isInstalled) {
				changeVersionButton.setEnabled(false);
			}
			panel.add(changeVersionButton);

			MainFrame.modsPanel.add(panel);
		}

		// Add Nvda download button
		if (utils.getOS() == utils.OS.WINDOWS) {
			NvdaDllButton nvdaDllButton = new NvdaDllButton("Install NVDA Controller DLL");
			JPanel nvdaDllPanel = new JPanel();
			nvdaDllPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			nvdaDllPanel.add(nvdaDllButton);

			MainFrame.modsPanel.add(nvdaDllPanel);
		}

		MainFrame.modsPanel.setMinimumSize(MainFrame.modsPanel.getMinimumSize());

		Gui.mainFrame.pack();
		Gui.mainFrame.revalidate();
		isRunning = false;
	}

}
