package com.shoaibkhan.modmanager.gui.widgets.common;

import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import javax.swing.border.EmptyBorder;
import javax.swing.plaf.DimensionUIResource;

import com.shoaibkhan.modmanager.gui.widgets.base.BaseLabel;

public class HeadingLabel extends BaseLabel {
    public HeadingLabel(String text) {
        super(text);
        Font font = new Font ("Arial", Font.BOLD, 22);
        Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        this.setFont(font.deriveFont(attributes));
        this.setBorder(new EmptyBorder(10, 30, 10, 10));
    }

    @Override
    public void refreshSize() {
        // Change the width
        DimensionUIResource dimensionUIResource = new DimensionUIResource(
                this.getFontMetrics(this.getFont()).stringWidth(this.getText()), 50);
        this.setPreferredSize(new DimensionUIResource(dimensionUIResource.width + 90, 50));
    }
}
