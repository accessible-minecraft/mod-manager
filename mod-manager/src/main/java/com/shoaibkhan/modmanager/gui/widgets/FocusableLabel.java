package com.shoaibkhan.modmanager.gui.widgets;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class FocusableLabel extends JLabel {
    public FocusableLabel(String text) {
        super(text);
        this.setFocusable(true);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setToolTipText(text);

        this.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                setBorder(BorderFactory.createLineBorder(Color.RED));
            }

            @Override
            public void focusLost(FocusEvent e) {
                setBorder(BorderFactory.createLineBorder(Color.BLACK));
            }

        });

                
    }
}
