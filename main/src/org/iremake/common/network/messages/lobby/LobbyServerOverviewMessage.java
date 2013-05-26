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
package org.iremake.common.network.messages.lobby;

import java.util.List;
import org.iremake.common.network.messages.Channel;
import org.iremake.common.network.messages.Message;

/**
 *
 */
public class LobbyServerOverviewMessage extends Message {

    private List<LobbyClientEntry> clients;
    private String chatHistory;

    private LobbyServerOverviewMessage() {
    }

    public LobbyServerOverviewMessage(List<LobbyClientEntry> clients, String chatHistory) {
        super(Channel.LOBBY);
        if (clients == null) {
            throw new IllegalArgumentException("Arguments clients cannot be null!");
        }

        this.clients = clients;
        this.chatHistory = chatHistory;
    }

    public List<LobbyClientEntry> getClients() {
        return clients;
    }

    public String getChatHistory() {
        return chatHistory;
    }

    @Override
    public String toString() {
        return String.format("LobbyOverviewMessage [Clients : %d, %s]", clients.size(), getChannel());
    }
}