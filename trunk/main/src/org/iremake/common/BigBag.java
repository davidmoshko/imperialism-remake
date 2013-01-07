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
package org.iremake.common;

import org.iremake.client.network.ClientManager;
import org.iremake.server.network.ServerManager;

/**
 * Convenience solution for having some global storage of variables. This must be
 * replaced at some point.
 */
// TODO needs to be replaced!, currently just a big holder of static global variables
public class BigBag {

    public static ClientManager clientManager;
    public static ServerManager serverManager;
}
