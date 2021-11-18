package com.shoaibkhan.modmanager.gui.menu;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.shoaibkhan.modmanager.Main;
import com.shoaibkhan.modmanager.configs.ModsJSON;
import com.shoaibkhan.modmanager.configs.PropertiesJSON;
import com.shoaibkhan.modmanager.manager.LatestFiles;
import com.shoaibkhan.modmanager.utils.ActionResult;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

public class FileMenu extends JMenuBar {
    public FileMenu() {
        JMenu fileMenu = new JMenu("File");

        // Menu Items
        JMenuItem checkForUpdateItem = new JMenuItem("Check for Updates");
        checkForUpdateItem.addActionListener(e -> {
            try {
                // Get the current manager version
                MavenXpp3Reader reader = new MavenXpp3Reader();
                Model model;
                if ((new File("pom.xml")).exists())
                    model = reader.read(new FileReader("pom.xml"));
                else
                    model = reader.read(new InputStreamReader(Main.class.getResourceAsStream(
                            "/META-INF/maven/com.shoaibkhan.modmanager/mod-manager/pom.xml")));

                Float currentVersion = Float.parseFloat(model.getVersion());

                // Get version from the mod-manager-config
                Float latestVersion = PropertiesJSON.getLatestVersion();
                if (latestVersion == null) {
                    JOptionPane.showMessageDialog(null, "An error occured!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (Float.compare(currentVersion, latestVersion) < 0) {
                    int res = JOptionPane.showConfirmDialog(null, "An update is available, do you want to install it?");
                    if (res != 0)
                        return;

                    // Install the latest update
                    ActionResult result = LatestFiles.updateFiles();
                    if (result == ActionResult.PASS) {
                        JOptionPane.showMessageDialog(null, "Mod Manager updated successfully! Closing the app now.", "Update Succcessful!",
                                JOptionPane.DEFAULT_OPTION);
                        
                        // Delete temporary files before closing
                        ModsJSON.DeleteTempFolder();
                        
                        // Close 
                        System.exit(ABORT);
                    }
                }
            } catch (IOException | XmlPullParserException e1) {
                JOptionPane.showMessageDialog(null, "An error occured!", "Error", JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            }
        });
        fileMenu.add(checkForUpdateItem);

        // JMenuItem aboutItem = new JMenuItem("About");
        // fileMenu.add(aboutItem);

        // Set mnemonic to open the menu
        fileMenu.setMnemonic(KeyEvent.VK_ALT);

        this.add(fileMenu);
    }
}
