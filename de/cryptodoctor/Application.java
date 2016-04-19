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

import de.cryptodoctor.components.CFrame;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;

/**
 * @todo Javadoc
 * @author Saladan
 */
public class Application {

    private static final Logger LOG = getLogger(Application.class.getName());

    /**
     * The error message in case of an exception
     */
    public static final String NORMAL_MESSAGE = "An unexpected error occured. Please send the error message to project contributors.";
    /**
     * The error message in case of a fatal exception
     */
    public static final String FATAL_MESSAGE = "A fatal error occured. The application is being stopped. Please send the error message to project contributors.";
    /**
     * The width of the GUI
     */
    public static final int FRAME_WIDTH = 720;
    /**
     * The height of the GUI
     */
    public static final int FRAME_HEIGHT = 360;

    private boolean running = true;
    private final CFrame frame;
    private final MainRoutine mainroutine;

    /**
     * Construct a new application frame. Initialize all neccessary items.
     */
    public Application() {
        frame = new CFrame(this);
        mainroutine = new MainRoutine(this);
    }

    /**
     * Indicates weather the program is running or not.
     *
     * @return true if the Application is running, false otherwise
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * @todo Javadoc
     * @param running the MainRoutine-Status to be set
     */
    public void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * @todo Javadoc
     * @return the main frame
     */
    public CFrame getFrame() {
        return frame;
    }

    /**
     * @todo Javadoc
     * @return the main routine of the application
     */
    public MainRoutine getMainRoutine() {
        return mainroutine;
    }

}
