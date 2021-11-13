package com.shoaibkhan.modmanager.gui.widgets.common;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;

import com.shoaibkhan.modmanager.gui.widgets.base.BaseLabel;

public class ButtonLabel extends BaseLabel {
    public ButtonLabel(String text) {
        super(text);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));

        this.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
            }

            @Override
            public void focusLost(FocusEvent e) {
                setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
            }

        });

    }

}
