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
package org.iremake.client.utils;

import java.awt.Image;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Loads from within the application jar file.
 * 
 * @author Trilarion 2012
 */
public class Resources {
    
    public static final String UI = "/data/game/art/graphics/ui";
    
    private Resources() {}

    public static Icon getAsIcon(String resource) {
        URL url = Resources.getAsURL(resource);
        if (url == null) {
            return null;
        }
        ImageIcon icon = new ImageIcon(url);
        return icon;
    }

    public static Image getAsImage(String resource) {
        URL url = Resources.getAsURL(resource);
        if (url == null) {
            return null;
        }        
        ImageIcon icon = new ImageIcon(url);
        return icon.getImage();
    }

    /**
     *
     * @param resource
     * @return The URL or null if not existing.
     */
    public static URL getAsURL(String resource) {
        return Resources.class.getResource(resource);
    }
}
