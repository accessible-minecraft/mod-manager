package com.shoaibkhan.modmanager.gui;

import java.awt.GridLayout;

import javax.swing.JFrame;

import com.shoaibkhan.modmanager.gui.panels.ProfilesPanel;

public class Gui {
    public static void init() {

        JFrame f = new JFrame("Mod Manager");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ProfilesPanel profilesPanel = new ProfilesPanel(new GridLayout(0, 4, 10, 10));
        f.add(profilesPanel);

        f.setResizable(false);
        f.pack();
        f.setVisible(true);
    }
}
