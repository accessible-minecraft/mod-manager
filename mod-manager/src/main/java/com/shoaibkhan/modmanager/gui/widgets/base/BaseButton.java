package com.shoaibkhan.modmanager.gui.widgets.base;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;

public class BaseButton extends JButton {
    public BaseButton(String text){
        super(text);
        this.setFont(new Font ("Arial", Font.PLAIN, 18));
        // this.setToolTipText(text + " Button");

        this.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                setForeground(new Color(23, 2, 12));
            }

            @Override
            public void focusLost(FocusEvent e) {
                setForeground(new Color(210, 210, 210));
            }

        });
    }
}
