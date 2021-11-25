/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shoaibkhan.modmanager.gui.widgets.base;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JLabel;

/**
 *
 * @author shoaib
 */
public class BaseLabel extends JLabel {

    public BaseLabel() {
        this.setFocusable(true);
        this.setFont(new java.awt.Font("Arial", 1, 18));

        setForeground(new Color(187,187,187));

        this.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                setForeground(new Color(23, 2, 12));
            }

            @Override
            public void focusLost(FocusEvent e) {
                setForeground(new Color(187,187,187));
            }

        });
    }
}
