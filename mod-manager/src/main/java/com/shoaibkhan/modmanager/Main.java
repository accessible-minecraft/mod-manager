package com.shoaibkhan.modmanager;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.shoaibkhan.modmanager.profiles.currentProfile;

/**
 * Hello world!
 *
 */

public class Main {
    public static void main(String[] args) {
        JFrame f = new JFrame("Mod Manager");// creating instance of JFrame
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton b1 = new JButton(currentProfile.getCurrentProfileName());// creating instance of JButton
        b1.setBounds(10, 10, 100, 50);// x axis, y axis, width, height
        f.add(b1);// adding button in JFrame

        f.setSize(250, 100);// 400 width and 500 height
        f.setLayout(null);// using no layout managers
        f.setVisible(true);// making the frame visible
    }

}
