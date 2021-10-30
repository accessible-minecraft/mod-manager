package com.shoaibkhan.modmanager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.shoaibkhan.modmanager.profiles.AddNewProfile;
import com.shoaibkhan.modmanager.profiles.CurrentProfile;
import com.shoaibkhan.modmanager.profiles.utils;
import com.shoaibkhan.modmanager.utils.ActionResult;

/**
 * Hello world!
 *
 */

public class Main {
    public static void main(String[] args) {
        JFrame f = new JFrame("Mod Manager");// creating instance of JFrame
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel curProfile = new JLabel("Selected Profile: " + CurrentProfile.getCurrentProfileName());
        curProfile.setBounds(10, 10, 200, 50);
        f.add(curProfile);

        JButton nextProfileButton = new JButton("Next Profile");
        nextProfileButton.addActionListener(e -> {
        });
        nextProfileButton.setBounds(220, 10, 150, 50);
        f.add(nextProfileButton);

        JButton addProfileButton = new JButton("Add Profile");
        addProfileButton.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Enter name of the profile");
            String directory = JOptionPane.showInputDialog("Enter directory path", utils.getMinecraftDirectory());

            ActionResult response = AddNewProfile.addNewProfile(name, directory);
            if(response!=ActionResult.PASS){
                JOptionPane.showMessageDialog(addProfileButton, response.getDescription());
            }
        });
        addProfileButton.setBounds(380, 10, 150, 50);
        f.add(addProfileButton);

        JButton removeProfileButton = new JButton("Remove Button");
        removeProfileButton.setBounds(540, 10, 150, 50);
        f.add(removeProfileButton);

        f.setSize(700, 100);// 400 width and 500 height
        f.setLayout(null);// using no layout managers
        f.setVisible(true);// making the frame visible
    }

}
