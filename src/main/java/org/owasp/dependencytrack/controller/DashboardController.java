/*
 * This file is part of Dependency-Track.
 *
 * Dependency-Track is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * Dependency-Track is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Dependency-Track. If not, see http://www.gnu.org/licenses/.
 *
 * Copyright (c) Axway. All Rights Reserved.
 */
package org.owasp.dependencytrack.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.owasp.dependencytrack.model.Application;
import org.owasp.dependencytrack.service.ApplicationService;
import org.owasp.dependencytrack.service.ApplicationVersionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Controller logic for all Dashboard-related requests.
 */
@Controller
public class DashboardController extends AbstractController {

    /**
     * Setup logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardController.class);

    /**
     * The Dependency-Track ApplicationService.
     */
    @Autowired
    private ApplicationService applicationService;

    /**
     * The Dependency-Track ApplicationVersionService.
     */
    @Autowired
    private ApplicationVersionService applicationVersionService;


    /**
     * Mapping to dashboard which gives vulnerability overview.
     */
    @RequiresPermissions("dashboard")
    @RequestMapping(value = "/chartdata/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String chartdata(Map<String, Object> map, @PathVariable("id") Integer applicationVersionId) {
        return applicationVersionService.chartdata(applicationVersionId);
    }

    /**
     * Mapping to dashboard which gives vulnerability overview.
     */
    @RequiresPermissions("dashboard")
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(Map<String, Object> map) {
        map.put("application", new Application());
        map.put("applicationList", applicationService.listApplications());
        return "dashboardPage";
    }

}
