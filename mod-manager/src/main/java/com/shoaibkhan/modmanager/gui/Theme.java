package com.shoaibkhan.modmanager.gui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Theme {
	public Theme(JFrame mainFrame) {
		// Dark LAF
		try {
		    UIManager.setLookAndFeel(new NimbusLookAndFeel());
		    UIManager.put("control", new Color(82, 82, 82));
		    UIManager.put("info", new Color(82, 82, 82));
		    UIManager.put("nimbusBase", new Color(18, 30, 49));
		    UIManager.put("nimbusAlertYellow", new Color(248, 187, 0));
		    UIManager.put("nimbusDisabledText", new Color(82, 82, 82));
		    UIManager.put("nimbusFocus", new Color(23, 2, 12));
		    UIManager.put("nimbusGreen", new Color(176, 179, 50));
		    UIManager.put("nimbusInfoBlue", new Color(66, 139, 221));
		    UIManager.put("nimbusLightBackground", new Color(18, 30, 49));
		    UIManager.put("nimbusOrange", new Color(191, 98, 4));
		    UIManager.put("nimbusRed", new Color(169, 46, 34));
		    UIManager.put("nimbusSelectedText", new Color(255, 255, 255));
		    UIManager.put("nimbusSelectionBackground", new Color(104, 93, 156));
		    UIManager.put("text", new Color(230, 230, 230));
		    
		    SwingUtilities.updateComponentTreeUI(mainFrame);
		} catch (UnsupportedLookAndFeelException exc) {
		    System.err.println("Nimbus: Unsupported Look and feel!");
		}
	}
}
