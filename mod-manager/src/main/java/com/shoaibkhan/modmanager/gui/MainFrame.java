/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shoaibkhan.modmanager.gui;

import com.shoaibkhan.modmanager.configs.ModsJSON;
import com.shoaibkhan.modmanager.gui.panels.ModsListPanel;
import com.shoaibkhan.modmanager.gui.widgets.base.BaseLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author shoaib
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrane
     */
    public MainFrame() {
        super("Mod Manager");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                ModsJSON.DeleteTempFolder();
            }
        });

        // Add the file menu
//        FileMenu fileMenu = new FileMenu();
//        this.setJMenuBar(fileMenu);



        // Load mods list panel
        ModsListPanel modsListPanel = new ModsListPanel();
        modsListPanel.start();

        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        tabbedPane = new javax.swing.JTabbedPane();
        modsPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        loadingLabel = new javax.swing.JLabel();
        profilesPanel = new com.shoaibkhan.modmanager.gui.panels.ProfilesPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabbedPane.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N

        modsPanel.setLayout(new javax.swing.BoxLayout(modsPanel, javax.swing.BoxLayout.Y_AXIS));

        loadingLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        loadingLabel.setText("Loading...");
        jPanel1.add(loadingLabel);

        modsPanel.add(jPanel1);

        tabbedPane.addTab("Mods Tab", modsPanel);
        tabbedPane.addTab("Profiles Tab", profilesPanel);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPane)
                .addGap(14, 14, 14))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(tabbedPane)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel loadingLabel;
    private javax.swing.JPanel mainPanel;
    public static javax.swing.JPanel modsPanel;
    private com.shoaibkhan.modmanager.gui.panels.ProfilesPanel profilesPanel;
    private javax.swing.JTabbedPane tabbedPane;
    // End of variables declaration//GEN-END:variables
}
