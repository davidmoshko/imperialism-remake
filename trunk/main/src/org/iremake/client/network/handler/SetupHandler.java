/*
 * Copyright (C) 2013 Trilarion
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
package org.iremake.client.network.handler;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import org.iremake.client.network.ClientContext;
import org.iremake.client.ui.MinimalSetupDialog;
import org.iremake.common.network.messages.Message;
import org.iremake.common.network.messages.game.setup.SetupMessage;
import org.iremake.common.network.messages.game.setup.SetupScenarioInfo;
import org.iremake.common.network.messages.game.setup.SetupTitlesList;

/**
 *
 */
public class SetupHandler implements ClientHandler {
    
    private MinimalSetupDialog dialog;


    public SetupHandler(MinimalSetupDialog dialog) {
        if (dialog == null) {
            throw new IllegalArgumentException("Argument dialog cannot be null.");
        }
        this.dialog = dialog;
    }

    @Override
    public boolean process(Message message, ClientContext context) {
        if (message instanceof SetupMessage) {
            if (message instanceof SetupTitlesList) {
                SetupTitlesList msg = (SetupTitlesList) message;
                dialog.setTitles(msg.getTitles());
                return true;
            } else if (message instanceof SetupScenarioInfo) {
                SetupScenarioInfo msg = (SetupScenarioInfo) message;
                Dimension size = dialog.getMapSize();
                BufferedImage mapImage = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
                for (int x = 0; x < size.width; x++) {
                    for (int y = 0; y < size.height; y++) {
                        int column = msg.getNumberColumns() * x / size.width; // rounding down
                        int row = msg.getNumberRows() * y / size.height;
                        mapImage.setRGB(x, y, msg.getColor(row, column));
                    }
                }          
                dialog.setMap(mapImage);
            }
        }
        return false;
    }

}
