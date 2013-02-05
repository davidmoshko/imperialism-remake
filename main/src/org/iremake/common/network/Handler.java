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
package org.iremake.common.network;

import org.iremake.common.network.messages.Message;
import org.iremake.common.network.messages.TextMessage;

/**
 *
 */
public interface Handler {

    public void send(Message message);

    public void disconnect(TextMessage message);

    public void consume(Message message);

    public void broadcastAll(Message message);

    public void broadcastSpecific(Message message, String name);

    public String name();
}
