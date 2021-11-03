package com.shoaibkhan.modmanager.gui;

import javax.swing.JFrame;

import com.shoaibkhan.modmanager.gui.panels.ProfilesPanel;

public class Gui {
    public static void init() {

        JFrame f = new JFrame("Mod Manager");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ProfilesPanel profilesPanel = new ProfilesPanel();
        f.add(profilesPanel);

        f.setResizable(false);
        f.pack();
        f.setVisible(true);
    }
}
