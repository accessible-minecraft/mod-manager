package com.shoaibkhan.modmanager;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Hello world!
 *
 */

public class Main {
    public static void main(String[] args) {
        JFrame f = new JFrame("Mod Manager");// creating instance of JFrame
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton b = new JButton("click");// creating instance of JButton
        b.setBounds(130, 100, 100, 40);// x axis, y axis, width, height

        JButton b1 = new JButton(profileManager.getMinecraftDirectory());// creating instance of JButton
        b1.setBounds(130, 150, 100, 40);// x axis, y axis, width, height
        f.add(b);// adding button in JFrame
        f.add(b1);// adding button in JFrame

        f.setSize(400, 500);// 400 width and 500 height
        f.setLayout(null);// using no layout managers
        f.setVisible(true);// making the frame visible
    }

}
