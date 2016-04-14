package de.cryptodoctor;

import static de.cryptodoctor.Application.ERROR_MESSAGE;
import static de.cryptodoctor.Application.FATAL_MESSAGE;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.List;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Saladan
 */
public class MainRoutine implements Runnable {

    private static final Logger LOG = getLogger(MainRoutine.class.getName());
    
    private final Application application;


    private List<Exception> exceptions;
    
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
            printFirstException();
        }
        application.getFrame().setVisible(false);
        exit(1);
    }

    private void printFirstException() {
        if (exceptions.isEmpty()) {
            return;
        }
        try {
            throw exceptions.remove(0);
        } catch (Exception e) {
            if (isFatal(e)) {
                LOG.log(SEVERE, FATAL_MESSAGE, e);
                application.setRunning(false);
            } else {
                LOG.log(SEVERE, ERROR_MESSAGE, e);
            }
        }

    }

    private boolean isFatal(Exception e) {
        return false;
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
