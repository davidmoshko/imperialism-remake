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
package org.iremake.server.network.clients;

import org.iremake.common.network.messages.Message;
import org.iremake.server.network.ServerClientHandler;

/**
 * A server client - that is the counterpart of a client, but on the server
 * side. It can consist of different state/specializations and at least has a
 * boss (ServerClientHandler) and can consume and send Messages.
 */
public abstract class ServerClient {

    protected final ServerClientHandler boss;

    public ServerClient(ServerClientHandler boss) {
        this.boss = boss;
    }

    public abstract void consume(Message message);
}