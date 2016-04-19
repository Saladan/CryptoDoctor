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
