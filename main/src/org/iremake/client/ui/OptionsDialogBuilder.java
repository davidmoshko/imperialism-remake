/*
 * Copyright (C) 2012 Trilarion
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.iremake.client.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;
import net.miginfocom.swing.MigLayout;
import org.iremake.client.Option;
import org.iremake.common.BigBag;
import org.iremake.server.network.ServerStatusListener;

/**
 * Options dialog wiring.
 */
public class OptionsDialogBuilder {

    /**
     * No instantiation.
     */
    private OptionsDialogBuilder() {
    }

    /**
     *
     * @param title
     * @param size
     * @return
     */
    public static void makeDialog() {

        // create JTabbedpane
        JTabbedPane pane = new JTabbedPane();
        pane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        // add panels for each tab
        pane.add(OptionsDialogBuilder.generalOptionsPanel(), "General");
        pane.add(OptionsDialogBuilder.serverOptionsPanel(), "Server");

        FrameManager.getInstance().startDialog(pane, "Options");
    }

    /**
     *
     * @return
     */
    private static JPanel generalOptionsPanel() {
        JPanel panel = new JPanel();

        JCheckBox fullscreen = new JCheckBox("Start in Full Screen");
        fullscreen.setSelected(Option.FullScreenMode.getBoolean());
        fullscreen.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                boolean selected = e.getStateChange() == ItemEvent.SELECTED;
                Option.FullScreenMode.putBoolean(selected);
                // TODO notification that it will be active after next start
            }
        });
        if (!Option.isOSWindows) {
            fullscreen.setEnabled(false);
        }


        JCheckBox controlLeftRight = new JCheckBox("Main Screen controls on right side");

        // layout
        panel.setLayout(new MigLayout("flowy"));
        panel.add(fullscreen);
        panel.add(controlLeftRight);

        return panel;
    }

    private static JPanel serverOptionsPanel() {
        JPanel panel = new JPanel();
        JToggleButton serverStart = new JToggleButton("Toggle Server");
        serverStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                boolean selected = abstractButton.getModel().isSelected();
                if (selected) {
                    BigBag.serverManager.start();
                } else {
                    BigBag.serverManager.stop();
                }
            }
        });
        JToolBar menuBar = new JToolBar();
        menuBar.add(serverStart);

        JPanel serverInfoPanel = new JPanel();
        serverInfoPanel.setBorder(new LineBorder(Color.black, 1));

        final JLabel status = new JLabel();
        BigBag.serverManager.addStatusListener(new ServerStatusListener() {
            @Override
            public void statusUpdate(String message) {
                status.setText(message);
            }
        });
        serverInfoPanel.add(status);

        JTextField networkAlias = new JTextField();
        networkAlias.setText(Option.NetworkAlias.get());

        // layout
        panel.setLayout(new MigLayout("wrap 2, fillx", "[][grow]"));
        panel.add(menuBar, "span");
        panel.add(serverInfoPanel, "height 100!, growx, span");
        panel.add(new JLabel("Default network alias"));
        panel.add(networkAlias, "wmin 200");

        return panel;

    }
}
