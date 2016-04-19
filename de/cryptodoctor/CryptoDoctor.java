/*
 * Copyright 2016 David Ehnert.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.cryptodoctor;

import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;

/**
 * @todo Javadoc
 * @author Saladan
 * @version v4.5.0-pre-alpha
 */
public class CryptoDoctor {

    private static final Logger LOG = getLogger(CryptoDoctor.class.getName());

    /**
     * This is the main routine. It is called automatically by the Java Virtual
     * Machine (JVM).
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application application = new Application();
        new Thread(application.getMainRoutine()).start();
    }
}
