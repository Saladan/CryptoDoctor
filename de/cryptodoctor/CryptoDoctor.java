package de.cryptodoctor;

import static de.cryptodoctor.Info.setFrame;
import de.cryptodoctor.components.CFrame;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Saladan
 * @version v4.0.2-pre-alpha
 */
public class CryptoDoctor {

    private static final Logger LOG = getLogger(CryptoDoctor.class.getName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        setFrame(new CFrame());
        new Thread(new Run()).start();
    }
}
