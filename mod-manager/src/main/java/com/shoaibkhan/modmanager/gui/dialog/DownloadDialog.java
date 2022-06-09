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

import com.shoaibkhan.modmanager.gui.widgets.base.BaseLabel;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadDialog extends JDialog {
    public DownloadDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setTitle("Downloading...");
        initComponents();
    }

    public void startDownloading(URL source, String destination) {
        Thread t = new Thread(() -> {
            try {
                HttpURLConnection httpConnection = (HttpURLConnection) (source.openConnection());
                long completeFileSize = httpConnection.getContentLength();

                // Focus progress bar
                downloadProgressBar.requestFocus();

                // Change label text and revalidate dialog
                double sizeInMB = ((double) completeFileSize) / (1024 * 1024);
                sizeInMB = (double) Math.round(sizeInMB * 100) / 100;
                downloadSizeLabel.setText("Download Size: " + sizeInMB + "mb");
                this.pack();
                this.revalidate();

                try (java.io.BufferedInputStream in = new java.io.BufferedInputStream(httpConnection.getInputStream());
                     java.io.FileOutputStream fos = new java.io.FileOutputStream(destination);
                     java.io.BufferedOutputStream bout = new BufferedOutputStream(
                             fos, 1024)) {

                    byte[] data = new byte[1024];
                    long downloadedFileSize = 0;
                    int x1;
                    while ((x1 = in.read(data, 0, 1024)) >= 0 && isVisible()) {
                        downloadedFileSize += x1;
                        // calculate progress
                        final int percentageDownloaded = (int) ((downloadedFileSize * 100) / completeFileSize);
                        // update progress bar
                        SwingUtilities.invokeLater(() -> {
                            downloadProgressBar.setValue(percentageDownloaded);
                            downloadProgressBar.setString(percentageDownloaded + "%");
                        });
                        bout.write(data, 0, x1);
                    }

                }
                // close dialog
                setVisible(false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        t.start();
        setVisible(true);
    }

    private void initComponents() {

        JPanel container = new JPanel();
        downloadProgressBar = new JProgressBar();
        downloadSizeLabel = new com.shoaibkhan.modmanager.gui.widgets.base.BaseLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Download File");
        setResizable(false);
        getContentPane().setLayout(new java.awt.FlowLayout());

        container.setLayout(new java.awt.BorderLayout());

        downloadProgressBar.setNextFocusableComponent(downloadSizeLabel);
        downloadProgressBar.setPreferredSize(new java.awt.Dimension(200, 50));
        downloadProgressBar.setStringPainted(true);
        downloadProgressBar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                downloadProgressBarFocusGained(evt);
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                downloadProgressBarFocusLost(evt);
            }
        });
        container.add(downloadProgressBar, java.awt.BorderLayout.NORTH);

        downloadSizeLabel.setText("Download Size : xxmb");
        downloadSizeLabel.setNextFocusableComponent(downloadProgressBar);
        container.add(downloadSizeLabel, java.awt.BorderLayout.SOUTH);

        getContentPane().add(container);

        pack();
    }

    private void downloadProgressBarFocusGained(java.awt.event.FocusEvent evt) {
        evt.getComponent().setForeground(Color.decode("#38ABFF"));
    }

    private void downloadProgressBarFocusLost(java.awt.event.FocusEvent evt) {
        evt.getComponent().setForeground(new Color(187, 187, 187));
    }

    public JProgressBar downloadProgressBar;
    private BaseLabel downloadSizeLabel;
}
