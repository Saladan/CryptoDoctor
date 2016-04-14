package de.cryptodoctor;

import static de.cryptodoctor.Info.ERROR_MESSAGE;
import static de.cryptodoctor.Info.getFrame;
import static de.cryptodoctor.Info.isRunning;
import static java.lang.System.exit;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Saladan
 */
public class Run implements Runnable {

    private static final Logger LOG = getLogger(Run.class.getName());

    /**
     *
     */
    @Override
    public void run() {
        try {
            getFrame().setVisible(true);
            while (isRunning()) {
            }
        } catch (Exception e) {
            LOG.log(SEVERE, ERROR_MESSAGE, e);
            LOG.log(SEVERE, "Stopping all activities...");
            exit(1);
        }
    }
}
