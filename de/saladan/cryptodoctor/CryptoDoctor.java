package de.saladan.cryptodoctor;

import static de.saladan.cryptodoctor.Info.frame;
import de.saladan.cryptodoctor.components.CFrame;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Saladan
 * @version v1.1.2-pre-alpha
 */
public class CryptoDoctor {

    private static final Logger LOG = getLogger(CryptoDoctor.class.getName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        frame = new CFrame();
        new Thread(new Run()).start();
    }
}
