/*
 * The MIT License
 *
 * Copyright 2016 David Ehnert (Saladan).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package de.cryptodoctor;

import static de.cryptodoctor.Application.FATAL_MESSAGE;
import static de.cryptodoctor.Application.NORMAL_MESSAGE;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.toHexString;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.List;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author Saladan
 */
public class MainRoutine implements Runnable {

    private static final Logger LOG = getLogger(MainRoutine.class.getName());
    private static final int NO_ERROR = 0;
    private static final int ERROR_USER = 1;
    private static final int ERROR_NORMAL = 2;
    private static final int ERROR_FATAL = 3;

    private final Application application;

    private List<Exception> exceptions;

    /**
     * @todo Javadoc
     * @param a
     */
    public MainRoutine(Application a) {
        application = a;
    }

    /**
     * @todo Javadoc
     */
    @Override
    public void run() {
        application.getFrame().setVisible(true);
        exceptions = new ArrayList<>(0);
        while (application.isRunning()) {
            printExceptions();
        }
        application.getFrame().setVisible(false);
        exit(1);
    }

    private void printExceptions() {
        if (exceptions.isEmpty()) {
            return;
        }
        try {
            throw exceptions.remove(0);
        } catch (Exception e) {
            switch (getType(e)) {
                case NO_ERROR:
                    break;
                case ERROR_USER:
                    LOG.log(SEVERE, "{0}\nat: {1}", new Object[]{e.getMessage(), e.getStackTrace()[0]});
                    handleException(e);
                    break;
                case ERROR_NORMAL:
                    LOG.log(SEVERE, NORMAL_MESSAGE, e);
                    break;
                case ERROR_FATAL:
                    LOG.log(SEVERE, FATAL_MESSAGE, e);
                    application.setRunning(false);
                    break;
            }
        }

    }

    private int getType(Exception e) {
        if (e.getMessage().startsWith("ERROR_USER")) {
            return ERROR_USER;
        }
        if (e.getMessage().startsWith("ERROR_FATAL")) {
            return ERROR_FATAL;
        }
        if (e.getMessage().startsWith("ERROR_NORMAL")) {
            return ERROR_NORMAL;
        }
        return NO_ERROR;
    }

    private void handleException(Exception e) {
        int i = parseInt(e.getMessage().split(" ")[1].split(":")[0], 16);
        switch (i) {
            case 0x0:
                showMessageDialog(application.getFrame(), "Es gibt einen Fehler in der Verschlüsselungsdefinition!\n\nBreche ab.", "Achtung.", ERROR_MESSAGE, null/*Icon*/);
                break;
            default:
                logException(new UnsupportedOperationException("ERROR_NORMAL 0: no handler specified for ERROR_USER " + toHexString(i)));
        }

    }

    /**
     * Adds an exception to the list of pending exceptions. This list is then
     * being logged.
     *
     * @param e the exception to be logged
     */
    public void logException(Exception e) {
        exceptions.add(e);
    }
}
