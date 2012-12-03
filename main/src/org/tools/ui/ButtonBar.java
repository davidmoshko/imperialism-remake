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
package org.tools.ui;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 * JToolBar has the disadvantage that it cannot be made invisible, because it
 * contains at least one internal JPanel (tested on Linux). Therefore we need a
 * replacement that holds together some buttons in a line with some reasonable
 * gaps.
 */
public class ButtonBar {

    private JPanel panel = new JPanel();

    public ButtonBar() {
        panel.setOpaque(true);
        panel.setBackground(new Color(0.8f, 0.8f, 0.8f, 0.2f));
        panel.setLayout(new MigLayout("gap related"));
    }

    public void add(JButton... buttons) {
        for (JButton button : buttons) {
            panel.add(button);
        }
    }

    public JPanel get() {
        return panel;
    }
}
