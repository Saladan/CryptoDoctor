package de.cryptodoctor;

import de.cryptodoctor.components.CFrame;
import de.cryptodoctor.components.crypt.CBinary;
import de.cryptodoctor.components.crypt.CCaesar;
import de.cryptodoctor.components.crypt.CVigenere;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Saladan
 */
public class Info {

    private static final Logger LOG = getLogger(Info.class.getName());

    /**
     * Classes of all Ciphers that have been implemented
     */
    public static final Class[] CIPHER_CLASSES = new Class[]{null, CCaesar.class, CVigenere.class, CBinary.class};

    /**
     * Names of all Ciphers that have been implemented
     */
    public static final String[] CIPHER_NAMES = new String[]{"<bitte wählen>", "Cäsar-Verschlüsselung", "Vigenère-Verschlüsselung", "Binäre Verschlüsselung (XOR)"};
    /**
     * The Error Message in case of an Exception
     */
    public static final String ERROR_MESSAGE = "\nBitte melden Sie die Fehlermeldung an die Leitung des Projektes, damit der Fehler behoben werden kann.\n\nFehlermeldung:";
    /**
     * The width of the GUI
     */
    public static final int FRAME_WIDTH = 720;
    /**
     * The height of the GUI
     */
    public static final int FRAME_HEIGHT = 360;
    
    private static boolean running = true;
    private static CFrame frame;

    /**
     * Indicates weather the program is running.
     * 
     * @return true if the Application is running, false otherwise
     */
    public static boolean isRunning() {
        return running;
    }

    /**
     * @param r the Run-Status to be set
     */
    public static void setRunning(boolean r) {
        running = r;
    }

    /**
     * @return the main frame
     */
    public static CFrame getFrame() {
        return frame;
    }

    /**
     * @param f the frame to set
     */
    public static void setFrame(CFrame f) {
        frame = f;
    }

    private Info() {
    }
}
