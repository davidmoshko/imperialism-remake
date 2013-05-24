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
package org.iremake.common.network.messages;

import java.util.logging.Logger;

/**
 * A message consisting of some text and a type (given by an enum).
 */
public class TextMessage extends Message {

    private static final Logger LOG = Logger.getLogger(TextMessage.class.getName());

    private String text;
    private TextMessageType type;

    private TextMessage() {
    }

    /**
     *
     * @param type
     * @param text
     */
    public TextMessage(TextMessageType type, String text, Channel channel) {
        super(channel);
        this.type = type;
        this.text = text;
    }

    /**
     *
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     *
     * @return
     */
    public TextMessageType getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("TextMessage [%s, Text : %s, %s]", type, text, getChannel());
    }
}
