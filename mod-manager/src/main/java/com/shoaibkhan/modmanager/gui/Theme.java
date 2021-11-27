/*
 * The MIT License
 *
 * Copyright 2021 shoaib.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.shoaibkhan.modmanager.gui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Theme {
	public Theme(JFrame mainFrame) {
		// Dark LAF
		try {
		    UIManager.setLookAndFeel(new NimbusLookAndFeel());
		    UIManager.put("control", new Color(82, 82, 82));
		    UIManager.put("info", new Color(82, 82, 82));
		    UIManager.put("nimbusBase", new Color(18, 30, 49));
		    UIManager.put("nimbusAlertYellow", new Color(248, 187, 0));
		    UIManager.put("nimbusDisabledText", new Color(82, 82, 82));
		    UIManager.put("nimbusFocus", new Color(23, 2, 12));
		    UIManager.put("nimbusGreen", new Color(176, 179, 50));
		    UIManager.put("nimbusInfoBlue", new Color(66, 139, 221));
		    UIManager.put("nimbusLightBackground", new Color(18, 30, 49));
		    UIManager.put("nimbusOrange", new Color(191, 98, 4));
		    UIManager.put("nimbusRed", new Color(169, 46, 34));
		    UIManager.put("nimbusSelectedText", new Color(255, 255, 255));
		    UIManager.put("nimbusSelectionBackground", new Color(104, 93, 156));
		    UIManager.put("text", new Color(230, 230, 230));
		    
		    SwingUtilities.updateComponentTreeUI(mainFrame);
		} catch (UnsupportedLookAndFeelException exc) {
		    System.err.println("Nimbus: Unsupported Look and feel!");
		}
	}
}
