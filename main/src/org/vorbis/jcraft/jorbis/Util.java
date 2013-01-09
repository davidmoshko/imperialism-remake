/*
 * Copyright (C) 2000 ymnk, JCraft,Inc.
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
package org.vorbis.jcraft.jorbis;

class Util {

    /**
     * No instantiation
     */
    private Util() {
    }

    static int ilog(int v) {
        int ret = 0;
        while (v != 0) {
            ret++;
            v >>>= 1;
        }
        return ret;
    }

    static int ilog2(int v) {
        int ret = 0;
        while (v > 1) {
            ret++;
            v >>>= 1;
        }
        return ret;
    }

    static int icount(int v) {
        int ret = 0;
        while (v != 0) {
            ret += v & 1;
            v >>>= 1;
        }
        return ret;
    }
}
