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

import org.iremake.client.network.ClientManager;
import org.iremake.common.network.messages.Message;
import org.iremake.common.network.messages.lobby.LobbyChatMessage;
import org.iremake.common.network.messages.lobby.LobbyMessage;
import org.iremake.common.network.messages.lobby.LobbyServerOverviewMessage;
import org.iremake.common.network.messages.lobby.LobbyServerUpdateMessage;

/**
 *
 */
public class LobbyHandler implements ClientHandler {

    @Override
    public boolean process(Message message, ClientManager manager) {
        if (message instanceof LobbyMessage) {
            if (message instanceof LobbyChatMessage) {
                // a new chat message, display it
            } else if (message instanceof LobbyServerOverviewMessage) {
                // a complete new overview, fill with data
            } else if (message instanceof LobbyServerUpdateMessage) {
                // somebody arrived or left, update
            }
            return true;
        }
        return false;
    }
}
