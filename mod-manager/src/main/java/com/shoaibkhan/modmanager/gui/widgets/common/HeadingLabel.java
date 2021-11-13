package com.shoaibkhan.modmanager.gui.widgets.common;

import javax.swing.border.EmptyBorder;
import javax.swing.plaf.DimensionUIResource;

import com.shoaibkhan.modmanager.gui.widgets.base.BaseLabel;

public class HeadingLabel extends BaseLabel {
    public HeadingLabel(String text) {
        super(text);
        this.setBorder(new EmptyBorder(10, 30, 10, 10));
    }

    @Override
    public void refreshSize() {
        // Change the width
        DimensionUIResource dimensionUIResource = new DimensionUIResource(
                this.getFontMetrics(this.getFont()).stringWidth(this.getText()), 50);
        this.setPreferredSize(new DimensionUIResource(dimensionUIResource.width + 70, 50));
    }
}
