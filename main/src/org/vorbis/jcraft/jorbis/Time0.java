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

import org.vorbis.jcraft.jogg.Buffer;

class Time0 extends FuncTime {

    void pack(Object i, Buffer opb) {
    }

    Object unpack(Info vi, Buffer opb) {
        return "";
    }

    Object look(DspState vd, InfoMode mi, Object i) {
        return "";
    }

    void free_info(Object i) {
    }

    void free_look(Object i) {
    }

    int inverse(Block vb, Object i, float[] in, float[] out) {
        return 0;
    }
}
