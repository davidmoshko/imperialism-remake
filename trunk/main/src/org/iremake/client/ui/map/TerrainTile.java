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
package org.iremake.client.ui.map;

import java.awt.Color;
import java.awt.Image;
import java.util.Objects;

/**
 *
 */
public class TerrainTile {

    private Image image;
    private Color color;

    public TerrainTile(Image image, Color color) {
        this.image = image;
        this.color = color;
    }

    public Image getImage() {
        return image;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public int hashCode() {
        return image.hashCode() * 13 + color.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TerrainTile other = (TerrainTile) obj;
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        return true;
    }
}