/*
 * Copyright (c) 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.com.kimae;

import java.io.IOException;
import java.util.logging.LogManager;

import javax.enterprise.inject.se.SeContainerInitializer;

import io.helidon.config.Config;
import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerConfiguration;
import io.helidon.webserver.WebServer;
import io.helidon.webserver.json.JsonSupport;

/**
 * Simple Hello World rest application.
 */
public final class Main {

    /**
     * Cannot be instantiated.
     */
    private Main() { }



    /**
     * Application main entry point.
     * @param args command line arguments.
     * @throws IOException if there are problems reading logging properties
     */
    public static void main(final String[] args) throws IOException {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        initializer.initialize();
        //initializer.addPackages(Main.class.getPackage()).initialize();
    }


}
