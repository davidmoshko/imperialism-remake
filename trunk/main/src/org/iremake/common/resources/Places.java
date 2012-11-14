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
package org.iremake.common.resources;

/**
 * Logical structure of the data folders, used for input/output.
 */
public enum Places {

    None(""),
    LOG("log/"),
    Help("data/help/"),
    
    Common("data/game/common/"),
    
    Scenarios("data/game/scenarios/"),
    ScenarioSettings(Scenarios + "settings/"),
    
    Graphics("data/game/artwork/graphics/"),
    GraphicsIcons(Graphics + "ui/"),
    GraphicsTerrain(Graphics + "terrain/");
    
    private String id;

    Places(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }
}