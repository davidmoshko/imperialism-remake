/*
 * Copyright (C) 2012 Trilarion 2012
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
package org.iremake.tools;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import org.tools.ui.helper.LookAndFeel;
import org.tools.xml.XMLHelper;
import org.tools.xml.common.Property;

/**
 *
 * @author Trilarion 2012
 */
public class XMLCreator extends JFrame {
    private static final long serialVersionUID = 1L;

    /**
     * Creates new form XMLCreator
     */
    public XMLCreator() {
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

        toolBar1 = new JToolBar();
        terrainButton = new JButton();
        scrollPane1 = new JScrollPane();
        infoTextArea = new JTextArea();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("XML Creator Utility");
        setLocationByPlatform(true);
        setResizable(false);

        toolBar1.setRollover(true);

        terrainButton.setText("Terrain");
        terrainButton.setFocusable(false);
        terrainButton.setHorizontalTextPosition(SwingConstants.CENTER);
        terrainButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        terrainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                terrainButtonActionPerformed(evt);
            }
        });
        toolBar1.add(terrainButton);

        infoTextArea.setEditable(false);
        infoTextArea.setColumns(20);
        infoTextArea.setRows(5);
        scrollPane1.setViewportView(infoTextArea);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(toolBar1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(toolBar1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED, 457, Short.MAX_VALUE)
                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void terrainButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_terrainButtonActionPerformed
        Property map = new Property();

        // fill with connection
        map.put("forest", "terrain.forest.png");
        map.put("hills", "terrain.hills.png");
        map.put("mountains", "terrain.mountains.png");
        map.put("plains", "terrain.plains.png");
        map.put("sea", "terrain.sea.png");

        // write to file
        XMLHelper.write("terrain.xml", map);
    }//GEN-LAST:event_terrainButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // set look and feel
        LookAndFeel.setSystemLookAndFeel();

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new XMLCreator().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JTextArea infoTextArea;
    private JScrollPane scrollPane1;
    private JButton terrainButton;
    private JToolBar toolBar1;
    // End of variables declaration//GEN-END:variables
}
