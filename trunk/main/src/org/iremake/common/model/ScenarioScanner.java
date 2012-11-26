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
package org.iremake.common.model;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nu.xom.ParsingException;
import org.iremake.client.resources.Loader;
import org.iremake.client.resources.Places;
import org.iremake.common.resources.Resource;
import org.iremake.common.resources.ResourceUtils;
import org.iremake.common.xml.XMLHelper;

/**
 * Scans for scenarios in the scenario folder and returns a list of file names
 * and corresponding titles.
 */
public class ScenarioScanner {

    private static final Logger LOG = Logger.getLogger(ScenarioScanner.class.getName());
    private List<Resource> scenarios = new LinkedList<>();
    private List<String> titles = new LinkedList<>();

    public ScenarioScanner() {
    }

    /**
     * 
     */
    public void doScan() {
        Resource dir = null;
        try {
            dir = ResourceUtils.asResource(Loader.getPath(Places.Scenarios, ""));
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        
        scenarios.clear();
        try {
            scenarios = dir.list("scenario\\.[a-zA-z0-9]+\\.xml");
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        
        for (Resource resource: scenarios) {
            try {
                // load it completely
                Scenario scenario = new Scenario();
                XMLHelper.read(resource, scenario);
                titles.add(scenario.getTitle());                
            } catch (IOException | ParsingException ex) {
                LOG.log(Level.SEVERE, null, ex);
                // TODO a scenario cannot be loaded, delete it from the list later on
            }
        }
    }

    /**
     * 
     * @return 
     */
    public List<String> getScenarios() {
        return Collections.unmodifiableList(titles);
    }

    /**
     * 
     * @param index
     * @return 
     */
    public Resource getScenarioResource(int index) {
        if (index < 0 || index >= scenarios.size()) {
            return null;
        }
        return scenarios.get(index);
    }
}
