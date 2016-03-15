package de.saladan.cryptodoctor;

import static de.saladan.cryptodoctor.Info.ERROR;
import static de.saladan.cryptodoctor.Info.frame;
import static de.saladan.cryptodoctor.Info.running;
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
            frame.setVisible(true);
            while (running) {
            }
        } catch (Exception e) {
            LOG.log(SEVERE, ERROR, e);
            LOG.log(SEVERE, "Stopping all activities...");
        }
        exit(1);
    }
}
