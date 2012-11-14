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
package org.iremake.client.network;

import com.esotericsoftware.kryonet.Client;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.iremake.common.network.messages.KryoRegistration;
import org.iremake.common.network.messages.Message;

/**
 *
 */
public class ClientManager {

    private static final int PORT = 19876;
    private static final int TIMEOUT = 5000;
    private static final Logger LOG = Logger.getLogger(ClientManager.class.getName());
    private Client client;

    public boolean start() {
        if (client != null) {
            return false;
        }

        client = new Client();

        KryoRegistration.register(client.getKryo());

        client.start();

        try {
            client.connect(TIMEOUT, "localhost", PORT);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);

            stop();

            return false;
        }

        client.addListener(new Handler());

        return true;
    }

    public void send(Message message) {
        if (client != null && client.isConnected()) {
            client.sendTCP(message);
        }
    }

    public void stop() {
        // TODO what happens to operations being sent
        if (client != null) {
            client.stop();
            client = null;
        }
    }
}
