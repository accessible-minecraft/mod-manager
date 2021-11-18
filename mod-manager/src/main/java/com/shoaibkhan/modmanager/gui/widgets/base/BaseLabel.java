package com.shoaibkhan.modmanager.gui.widgets.base;

import javax.swing.JLabel;
import javax.swing.plaf.DimensionUIResource;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class BaseLabel extends JLabel {
    public BaseLabel(String text) {
        super(text);
        this.setFont(new Font ("Arial", Font.PLAIN, 18));
        this.setFocusable(true);
        // this.setToolTipText(text);

        refreshSize();

        setForeground(new Color(200, 200, 200));

        this.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                setForeground(new Color(23, 2, 12));
            }

            @Override
            public void focusLost(FocusEvent e) {
                setForeground(new Color(200, 200, 200));
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
