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
import com.shoaibkhan.modmanager.profiles.CurrentProfile;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * @author shoaib
 */
public class FolderChooserDialog extends JDialog {

    private JTextField folderPathInput;
    private String directory = null;
    private boolean hasChoosen = false;

    public FolderChooserDialog(Frame parent, boolean modal, boolean isEditing) {
        super(parent, modal);
        initComponents(isEditing);
    }

    public String choose() {
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setVisible(true);

        if (hasChoosen && directory != null) {
            return directory;
        }

        return null;
    }

    private void initComponents(boolean isEditing) {

        JPanel container = new JPanel();
        JPanel row1 = new JPanel();
        folderPathInput = new JTextField();
        BaseButton showDialogButton = new BaseButton();
        JPanel jPanel2 = new JPanel();
        BaseButton confirmButton = new BaseButton();
        BaseButton cancelButton = new BaseButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new FlowLayout());

        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        folderPathInput.setFont(new Font("Arial", Font.PLAIN, 18));
        if (isEditing) {
            folderPathInput.setText(CurrentProfile.getCurrentProfileDirectory());
            directory = CurrentProfile.getCurrentProfileDirectory();
        } else {
            folderPathInput.setText("Enter profile folder manually or use the browse button.");
        }

        // Listen for changes in the text
        folderPathInput.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                directory = folderPathInput.getText();
            }

            public void removeUpdate(DocumentEvent e) {
                directory = folderPathInput.getText();
            }

            public void insertUpdate(DocumentEvent e) {
                directory = folderPathInput.getText();
            }
        });
        row1.add(folderPathInput);

        showDialogButton.setText("Browse");
        showDialogButton.addActionListener(this::showDialogButtonActionPerformed);
        row1.add(showDialogButton);

        container.add(row1);

        confirmButton.setText("Confirm");
        confirmButton.addActionListener(this::confirmButtonActionPerformed);
        jPanel2.add(confirmButton);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(this::cancelButtonActionPerformed);
        jPanel2.add(cancelButton);

        container.add(jPanel2);

        getContentPane().add(container);

        pack();
    }

    private void confirmButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
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

    private void showDialogButtonActionPerformed(ActionEvent evt) {
        JFileChooser fileChooser;
        if (directory == null)
            fileChooser = new JFileChooser(CurrentProfile.getCurrentProfileDirectory());
        else
            fileChooser = new JFileChooser(directory);
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            folderPathInput.setText(file.getAbsolutePath());
            directory = file.getAbsolutePath();
        }
    }
}
