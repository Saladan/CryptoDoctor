package de.saladan.cryptodoctor;

import static de.saladan.cryptodoctor.Info.frame;
import de.saladan.cryptodoctor.components.CFrame;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Saladan
 * @version planning-1.1-3
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
