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
import org.iremake.client.Option;
import org.iremake.common.network.ConnectedClient;
import org.iremake.common.network.handler.ErrorHandler;
import org.iremake.common.network.handler.Handler;
import org.iremake.common.network.messages.Message;
import org.iremake.common.network.messages.TextMessage;
import org.iremake.common.network.messages.TextMessageType;

/**
 *
 */
public class VerifyVersionHandler implements Handler {
    private static final Logger LOG = Logger.getLogger(ErrorHandler.class.getName());

    @Override
    public void process(Message message, ConnectedClient client) {
        if (message instanceof TextMessage) {
            TextMessage msg = (TextMessage) message;
            if (TextMessageType.Version.equals(msg.getType())) {
                if (Option.General_Version.get().equals(msg.getText()) {
                    // passed version test
                    LOG.log(Level.FINE, "Client transmitted correct version" + client.name());
                    // remove from chain
                    client.remove();
                }
            }
        }
        // disconnect with ErrorMessage
        TextMessageType.Error.create(String.format("Did not receive version message. Was waiting for version. Version is %s", Option.General_Version.get()));
    }

    @Override
    public String name() {
        return "handler.versioncheck";
    }

}
