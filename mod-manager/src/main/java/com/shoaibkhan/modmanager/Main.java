package com.shoaibkhan.modmanager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.shoaibkhan.modmanager.profiles.AddNewProfile;
import com.shoaibkhan.modmanager.profiles.CurrentProfile;
import com.shoaibkhan.modmanager.profiles.RemoveCurrentProfile;
import com.shoaibkhan.modmanager.profiles.SelectNextProfile;
import com.shoaibkhan.modmanager.profiles.utils;
import com.shoaibkhan.modmanager.utils.ActionResult;

/**
 * Hello world!
 *
 */

public class Main {
    public static void main(String[] args) {
        JFrame f = new JFrame("Mod Manager");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel curProfile = new JLabel("Selected Profile: " + CurrentProfile.getCurrentProfileName());
        curProfile.setToolTipText("Selected Profile: " + CurrentProfile.getCurrentProfileName());
        curProfile.setBounds(10, 10, 200, 50);
        f.add(curProfile);

        JButton nextProfileButton = new JButton("Next Profile");
        nextProfileButton.setToolTipText("Select next profile button");
        nextProfileButton.addActionListener(e -> {
            ActionResult response = SelectNextProfile.selectNextProfiResult();
            if (response != ActionResult.PASS) {
                JOptionPane.showMessageDialog(nextProfileButton, response.getDescription());
            }

            curProfile.setText("Selected Profile: " + CurrentProfile.getCurrentProfileName());
            curProfile.setToolTipText("Selected Profile: " + CurrentProfile.getCurrentProfileName());
        });
        nextProfileButton.setBounds(220, 10, 150, 50);
        f.add(nextProfileButton);

        JButton addProfileButton = new JButton("Add Profile");
        addProfileButton.setToolTipText("Add Profile Button");
        addProfileButton.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Enter name of the profile");
            if (name == null)
                return;
            String directory = JOptionPane.showInputDialog("Enter directory path", utils.getMinecraftDirectory());
            if (directory == null)
                return;

            ActionResult response = AddNewProfile.addNewProfile(name, directory);
            if (response != ActionResult.PASS) {
                JOptionPane.showMessageDialog(addProfileButton, response.getDescription());
            }
        });
        addProfileButton.setBounds(380, 10, 150, 50);
        f.add(addProfileButton);

        JButton removeProfileButton = new JButton("Remove Profile");
        removeProfileButton.setToolTipText("Remoce Current Profile Button");
        removeProfileButton.addActionListener(e -> {
            ActionResult removeCurrentProfileResponse = RemoveCurrentProfile.removeCurrentProfile();
            if (removeCurrentProfileResponse != ActionResult.PASS) {
                JOptionPane.showMessageDialog(nextProfileButton, removeCurrentProfileResponse.getDescription());
            }

            ActionResult increaseCurrentProfileResponse = SelectNextProfile.selectNextProfiResult();
            if (increaseCurrentProfileResponse != ActionResult.PASS) {
                JOptionPane.showMessageDialog(nextProfileButton, increaseCurrentProfileResponse.getDescription());
            }

            curProfile.setText("Selected Profile: " + CurrentProfile.getCurrentProfileName());
            curProfile.setToolTipText("Selected Profile: " + CurrentProfile.getCurrentProfileName());
        });
        removeProfileButton.setBounds(540, 10, 150, 50);
        f.add(removeProfileButton);

        f.setSize(700, 100);
        f.setLayout(null);
        f.setVisible(true);

        // TODO add check for total profiles on startup
    }

}
