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

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.SwingUtilities;

/**
 *
 * @author shoaib
 */
public class DownloadDialog extends javax.swing.JDialog {

    /**
     * Creates new form DownloadDialog
     *
     * @param parent
     * @param modal
     */
    public DownloadDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public void startDownloading(URL source, String destination) {
        Thread t = new Thread(() -> {
            try {
                HttpURLConnection httpConnection = (HttpURLConnection) (source.openConnection());
                long completeFileSize = httpConnection.getContentLength();
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
                            jProgressBar1.setValue(percentageDownloaded);
                            jProgressBar1.setString(percentageDownloaded + "%");
                        });
                        bout.write(data, 0, x1);
                    }

                }
                // close dialog
                setVisible(false);
            } catch (FileNotFoundException e) {
            } catch (IOException e) {
            }
        });

        t.start();
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.FlowLayout());

        jProgressBar1.setValue(0);
        jProgressBar1.setPreferredSize(new java.awt.Dimension(200, 50));
        jProgressBar1.setStringPainted(true);
        getContentPane().add(jProgressBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}
