package com.shoaibkhan.modmanager.gui.widgets.base;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;

public class BaseButton extends JButton {
    public BaseButton(String text){
        super(text);
        this.setToolTipText(text + " Button");

        this.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                setForeground(Color.RED);
                setBorder(BorderFactory.createLineBorder(Color.RED));
            }

            @Override
            public void focusLost(FocusEvent e) {
                setForeground(Color.BLACK);
                setBorder(BorderFactory.createLineBorder(Color.BLACK));
            }

        });
    }
}
