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
    public static final String[] CIPHER_NAMES = new String[]{"<bitte wählen>", "Cäsar-Verschlüsselung", "Vigenère-Verschlüsselung", "Binäre Verchlüsselung (XOR)"};
    /**
     * The Error Message in case of an Exception
     */
    public static final String ERROR = "\nBitte melden Sie die Fehlermeldung an die Leitung des Projektes, damit der Fehler behoben werden kann.\n\nFehlermeldung:";
    /**
     * The width of the GUI
     */
    public static final int WIDTH = 720;
    /**
     * The height of the GUI
     */
    public static final int HEIGHT = 360;
    /**
     * Indicates weather the program is running. Set to false to stop execution.
     */
    public static boolean running = true;
    /**
     * GUI Frame
     */
    public static CFrame frame;

    private Info() {
    }
}
