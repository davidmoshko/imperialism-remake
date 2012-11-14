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
package org.iremake.server.network;

import com.esotericsoftware.kryonet.Server;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.iremake.common.network.messages.KryoRegistration;

/**
 *
 */
public class ServerManager {

    private static final Logger LOG = Logger.getLogger(ServerManager.class.getName());
    private static final int PORT = 19876;
    private Server server;

    public boolean start() {
        server = new Server();
        
        KryoRegistration.register(server.getKryo());

        server.start();
        
        try {
            server.bind(PORT);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);

            stop();

            return false;
        }

        server.addListener(new Handler());

        return true;
    }

    public void stop() {
        server.stop();
        server = null;
    }
}