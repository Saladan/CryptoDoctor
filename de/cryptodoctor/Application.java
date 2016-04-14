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
public class Application {
    

    private static final Logger LOG = getLogger(Application.class.getName());

    /**
     * Classes of all Ciphers that have been implemented
     */
    //public static final List<Class<CContent>> CIPHER_CLASSES = new ArrayList({null, CCaesar.class, CVigenere.class, CBinary.class});
    public static final Class[] CIPHER_CLASSES = new Class[]{null, CCaesar.class, CVigenere.class, CBinary.class};

    /**
     * Names of all Ciphers that have been implemented
     */
    public static final String[] CIPHER_NAMES = new String[]{"<bitte wählen>", "Cäsar-Verschlüsselung", "Vigenère-Verschlüsselung", "Binäre Verschlüsselung (XOR)"};
    /**
     * The error message in case of an exception
     */
    public static final String ERROR_MESSAGE = "\nAn unexpected error occured. Please send the error message to project contributors.\n\n";
    /**
     * The error message in case of a fatal exception
     */
    public static final String FATAL_MESSAGE = "\nA fatal error occured. The application is being stopped. Please send the error message to project contributors.\n\n";
    /**
     * The width of the GUI
     */
    public static final int FRAME_WIDTH = 720;
    /**
     * The height of the GUI
     */
    public static final int FRAME_HEIGHT = 360;
    
    private boolean running = true;
    private final CFrame frame;
    private final MainRoutine mainroutine;
    
    /**
     * Construct a new application frame. Initialize all neccessary items.
     */
    public Application() {
        frame = new CFrame(this);
        mainroutine = new MainRoutine(this);
    }

    /**
     * Indicates weather the program is running.
     * 
     * @return true if the Application is running, false otherwise
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * @param running the MainRoutine-Status to be set
     */
    public void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * @return the main frame
     */
    public CFrame getFrame() {
        return frame;
    }
    
    /**
     *
     * @return the main routine of the application
     */
    public MainRoutine getMainRoutine() {
        return mainroutine;
    }

}
