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

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nu.xom.Element;
import nu.xom.Elements;
import org.tools.xml.XMLable;
import org.tools.xml.common.XProperty;

/**
 *
 */
public class GeographicalMap implements XMLable {

    private static final Logger LOG = Logger.getLogger(GeographicalMap.class.getName());

    private int rows;
    private int columns;
    private String[][] map;

    private List<GeographicalMapChangedListener> listeners = new LinkedList<>();

    public GeographicalMap() {
    }

    /**
     * A sea(1) map.
     * @param rows
     * @param columns
     */
    public void setEmptyMap(int rows, int columns) {
        if (rows <= 0 || columns <= 0) {
            LOG.log(Level.INFO, "Zero or negative sizes!");
            return;
        }
        this.rows = rows;
        this.columns = columns;
        map = new String[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                map[row][column] = Settings.getDefaultTerrainID();
            }
        }
        fireMapChanged();
    }

    public boolean checkConsistency() {
        // TODO size and length of map are consistent, ids are valid and only 2 letters long
        return true;
    }

    private boolean contains(MapPosition p) {
        return p.row >= 0 && p.row < rows && p.column >= 0 && p.column < columns;
    }


    public void setTerrainAt(MapPosition p, String id) {
        if (!contains(p)) {
            LOG.log(Level.INFO, "Terrain position outside of map.");
            return;
        }
        map[p.row][p.column] = id;
        fireTileChanged(p);
    }

    public String getTerrainAt(MapPosition p) {
        if (!contains(p)) {
            LOG.log(Level.INFO, "Terrain position outside of map.");
            return null;
        }
        return map[p.row][p.column];
    }

    public int getNumberRows() {
        return rows;
    }

    public int getNumberColumns() {
        return columns;
    }

    public void addMapChangedListener(GeographicalMapChangedListener l) {
        listeners.add(l);
    }

    public void removeMapChangedListener(GeographicalMapChangedListener l) {
        listeners.remove(l);
    }

    private void fireTileChanged(MapPosition p) {
        String id = map[p.row][p.column];
        for (GeographicalMapChangedListener l: listeners) {
            l.tileChanged(p, id);
        }
    }

    private void fireMapChanged() {
        for (GeographicalMapChangedListener l: listeners) {
            l.mapChanged(this);
        }
    }

    private static final String NAME = "geographical-map";
    private static final String NAME_MAP = "map";

    @Override
    public Element toXML() {
        Element parent = new Element(NAME);

        // add size as list
        XProperty dimensions = new XProperty(2);
        dimensions.putInt("rows", rows);
        dimensions.putInt("columns", columns);
        parent.appendChild(dimensions.toXML());

        // assemble map as one big string
        int capacity = rows * columns * 2; // TODO check somewhere that ids have size 2
        StringBuilder builder = new StringBuilder(capacity);
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                builder.append(map[row][column]);
            }
        }
        Element child = new Element(NAME_MAP);
        child.appendChild(builder.toString());
        parent.appendChild(child);

        return parent;
    }

    @Override
    public void fromXML(Element parent) {

        if (parent == null || !NAME.equals(parent.getLocalName())) {
            LOG.log(Level.SEVERE, "Empty XML node or node name wrong.");
            return;
        }

        Elements children = parent.getChildElements();

        // get size
        XProperty dimensions = new XProperty(0);
        dimensions.fromXML(children.get(0));
        rows = dimensions.getInt("rows");
        columns = dimensions.getInt("columns");
        map = new String[rows][columns];

        // TODO test size of string with size
        // TODO more checks (positivity)
        String content = children.get(1).getValue();
        int p = 0;
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                map[row][column] = content.substring(p, p + 2);
                p += 2;
            }
        }

        fireMapChanged();
    }
}
