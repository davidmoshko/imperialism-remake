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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.iremake.common.network.messages.Message;

/**
 *
 */
public class ClientContext {

    private final ExecutorService threadPool = Executors.newFixedThreadPool(1);
    private final NodeContext root;

    public ClientContext(NodeContext root) {
        this.root = root;
    }

    public void process(final Message message) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                root.process(message);
            }
        });
    }

}