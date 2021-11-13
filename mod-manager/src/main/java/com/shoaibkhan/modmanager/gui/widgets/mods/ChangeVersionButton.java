package com.shoaibkhan.modmanager.gui.widgets.mods;

import javax.swing.JOptionPane;
import javax.swing.plaf.DimensionUIResource;

import com.shoaibkhan.modmanager.gui.panels.ModsListPanel;
import com.shoaibkhan.modmanager.gui.widgets.base.BaseButton;
import com.shoaibkhan.modmanager.mods.ChangeVersion;
import com.shoaibkhan.modmanager.utils.ActionResult;

public class ChangeVersionButton extends BaseButton {
    public ChangeVersionButton(String text, String forMod, double minecraftVersion) {
        super(text);
        this.setPreferredSize(new DimensionUIResource(150, 50));

        this.addActionListener(e -> {
            ActionResult response = ChangeVersion.changeVersion(forMod, minecraftVersion);
            if (response != ActionResult.PASS) {
                if (response != ActionResult.CLOSE)
                    // Skip if user closed the option dialogue
                    JOptionPane.showMessageDialog(this, response.getDescription());
                return;
            }

            JOptionPane.showMessageDialog(this, forMod + " updated", "Mod Version Changed Successfully",
                    JOptionPane.INFORMATION_MESSAGE);

            // Refresh mods list panel
            ModsListPanel modsListPanel = new ModsListPanel();
            modsListPanel.start();
        });
    }
}
