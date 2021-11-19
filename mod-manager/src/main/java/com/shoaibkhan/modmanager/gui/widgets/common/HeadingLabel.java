package com.shoaibkhan.modmanager.gui.widgets.common;

import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import javax.swing.border.EmptyBorder;

import com.shoaibkhan.modmanager.gui.widgets.base.BaseLabel;

public class HeadingLabel extends BaseLabel {
	public HeadingLabel(String text) {
		super(text);
		Font font = new Font("Arial", Font.BOLD, 22);
		Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		this.setFont(font.deriveFont(attributes));
		this.setBorder(new EmptyBorder(10, 30, 10, 10));
	}
}
