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
package org.iremake.xml;

import java.io.IOException;
import nu.xom.Attribute;
import nu.xom.Element;
import org.tools.io.Resource;
import org.tools.io.ResourceUtils;
import org.tools.xml.XMLHelper;

/**
 *
 */
public class MusicXMLGenerator {

    /**
     * @param args the command line arguments
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Element parent = new Element("Music");
        parent.appendChild(makeBackgroundMusicList());
        Resource resource = ResourceUtils.asResource("music.xml");
        XMLHelper.write(resource, parent);
    }

    public static Element makeBackgroundMusicList() {
        Element parent = new Element("Background");
        parent.appendChild(addPiece(1, "Awakening.ogg", "Awakening", "Veneteaou"));
        return parent;
    }

    public static Element addPiece(int id, String file, String title, String composer) {
        Element child = new Element("Piece");
        child.addAttribute(new Attribute("id", String.valueOf(id)));
        child.addAttribute(new Attribute("location", file));
        child.addAttribute(new Attribute("title", title));
        child.addAttribute(new Attribute("composer", composer));
        return child;
    }
}
