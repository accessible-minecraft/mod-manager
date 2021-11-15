package com.shoaibkhan.modmanager.gui.widgets.base;

import javax.swing.JLabel;
import javax.swing.plaf.DimensionUIResource;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class BaseLabel extends JLabel {
    public BaseLabel(String text) {
        super(text);
        this.setFocusable(true);
        // this.setToolTipText(text);

        refreshSize();

        setForeground(Color.BLACK);

        this.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                setForeground(Color.RED);
            }

            @Override
            public void focusLost(FocusEvent e) {
                setForeground(Color.BLACK);
            }

        });
    }

    @Override
    public void setText(String text) {
        super.setText(text);
    }

    public void refreshSize() {
        DimensionUIResource dimensionUIResource = new DimensionUIResource(
                this.getFontMetrics(this.getFont()).stringWidth(this.getText()), 50);
        this.setPreferredSize(new DimensionUIResource(dimensionUIResource.width + 20, 50));
    }
}
