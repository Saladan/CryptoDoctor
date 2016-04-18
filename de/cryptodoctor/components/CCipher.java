package de.cryptodoctor.components;

import de.cryptodoctor.components.crypt.CBinary;
import de.cryptodoctor.components.crypt.CCaesar;
import de.cryptodoctor.components.crypt.CVigenere;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author Saladan
 */
public abstract class CCipher extends JPanel {

    private static final long serialVersionUID = 1L;

    /**
     * Returns the name of the cipher that is given.
     *
     * @param T the cipher class
     * @return the name of the cipher
     */
    public static String getCipherName(Class<?> T) throws IllegalArgumentException {
        if (T.isAssignableFrom(CCaesar.class)) {
            return CCaesar.CIPHER_NAME;
        }
        if (T.isAssignableFrom(CBinary.class)) {
            return CBinary.CIPHER_NAME;
        }
        if (T.isAssignableFrom(CVigenere.class)) {
            return CVigenere.CIPHER_NAME;
        }
        throw new IllegalArgumentException("No such cipher");
    }

    /**
     * Encrypts the given text width specific Encryption rules.
     *
     * @param text The text to be encrypted
     * @return The encrypted text
     */
    public abstract String encrypt(String text);

    /**
     * Decrypts the given text with specific Decryption rules.
     *
     * @param text The text to be decrypted
     * @return The decrypted text
     */
    public abstract String decrypt(String text);

    /**
     * Indicates weather the Encryption Field is valid or invalid
     *
     * @return true if field is valid, false otherwise
     */
    public abstract boolean cryptIsValid();

    /**
     * @todo Javadoc
     * @param i
     */
    public void initHeight(int i) {
        Dimension size = new Dimension(0, i);
        setMinimumSize(size);
        setPreferredSize(size);
        size = new Dimension(250, i);
        setMaximumSize(size);
    }
}
