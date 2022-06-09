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
package com.shoaibkhan.modmanager.gui.dialog;

import com.shoaibkhan.modmanager.gui.widgets.base.BaseButton;
import com.shoaibkhan.modmanager.gui.widgets.base.BaseLabel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ChooseVersionDialog extends javax.swing.JDialog {
    private boolean hasChoosen = false;
    private JComboBox<String> versionComboBox;

    public ChooseVersionDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public int choose(Object[] versions) {

        // Remove all items in the combo box
        versionComboBox.removeAllItems();

        // Add items from versions array to the combo box
        for (int i = 0; i < versions.length; i++) {
            Object version = versions[i];
            if (i == 0) {
                versionComboBox.addItem(version.toString() + " (latest)");
            } else {
                versionComboBox.addItem(version.toString());
            }
        }

        // Resize after adding
        pack();
        revalidate();

        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL); // This stops the thread when setVisible(true) is called
        setVisible(true);

        if (hasChoosen) {
            // Get and return the choice
            return versionComboBox.getSelectedIndex();
        }
        return -1;
    }

    private void initComponents() {

        JPanel container = new JPanel();
        JPanel row1 = new JPanel();
        BaseLabel headingLabel = new BaseLabel();
        versionComboBox = new javax.swing.JComboBox<>();
        JPanel row2 = new JPanel();
        BaseButton confirmButton = new BaseButton();
        BaseButton cancelButton = new BaseButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Select Version");
        setResizable(false);
        getContentPane().setLayout(new java.awt.FlowLayout());

        container.setLayout(new javax.swing.BoxLayout(container, javax.swing.BoxLayout.Y_AXIS));

        headingLabel.setText("Select Version:-  ");
        headingLabel.setName("");
        row1.add(headingLabel);

        versionComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Default", "Default"}));
        row1.add(versionComboBox);

        container.add(row1);

        confirmButton.setText("Confirm");
        confirmButton.addActionListener(this::confirmButtonActionPerformed);
        row2.add(confirmButton);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(this::cancelButtonActionPerformed);
        row2.add(cancelButton);

        container.add(row2);

        getContentPane().add(container);

        pack();
    }

    private void confirmButtonActionPerformed(ActionEvent evt) {
        // Close dialog and set has chosen to true
        hasChoosen = true;
        dispose();
        setVisible(false);
    }

    private void cancelButtonActionPerformed(ActionEvent evt) {
        // Close dialog but don't set has chosen to true to set the response/return to -1
        dispose();
        setVisible(false);
    }
}
