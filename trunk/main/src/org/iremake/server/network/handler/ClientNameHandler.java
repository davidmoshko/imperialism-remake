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
package org.iremake.server.network.handler;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.iremake.common.network.messages.Message;
import org.iremake.common.network.messages.TextMessage;
import org.iremake.common.network.messages.MessageType;
import org.iremake.server.network.ServerContext;

/**
 * This is a stopper, either you transmit a name or you get kicked out.
 */
public class ClientNameHandler implements ServerHandler {

    private static final Logger LOG = Logger.getLogger(ClientNameHandler.class.getName());

    @Override
    public boolean process(Message message, ServerContext context) {
        if (message instanceof TextMessage) {
            TextMessage msg = (TextMessage) message;
            if (MessageType.ClientName.equals(msg.getType())) {
                // context.setName(msg.getText());
                LOG.log(Level.FINE, "Client {0} transmitted name: {1}", new Object[]{context.getName(), msg.getText()});
                // context.remove();
                // tell all others
                // context.broadcast("handler.chat", message);
                return true;
            }
        }
        // otherwise disconnect with error message
        context.disconnect("Expected client name.");
        return true;
    }
}