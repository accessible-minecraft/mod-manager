package com.shoaibkhan.modmanager.gui.widgets.base;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class BaseLabel extends JLabel {
	public BaseLabel(String text) {
		super(text);
		this.setFont(new Font("Arial", Font.PLAIN, 18));
		this.setFocusable(true);

		this.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

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
}
