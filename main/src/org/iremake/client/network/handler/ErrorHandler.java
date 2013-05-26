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

import java.util.logging.Level;
import java.util.logging.Logger;
import org.iremake.common.network.messages.ErrorMessage;
import org.iremake.common.network.messages.Message;

/**
 * This is the first handler in every processing tree. It filters out error
 * messages, logs them and disconnect. All other messages are propagated.
 */
public class ErrorHandler extends ClientHandler {

    private static final Logger LOG = Logger.getLogger(ErrorHandler.class.getName());

    /**
     * Eat all the Error TextMessages.
     *
     * @param message
     * @param context
     * @return
     */
    @Override
    public boolean process(Message message) {
        if (message instanceof ErrorMessage) {
            ErrorMessage msg = (ErrorMessage) message;
            LOG.log(Level.SEVERE, "Received error message: {0}", msg.getText());
            disconnect(null);
            return true;
        }
        return false;
    }
}