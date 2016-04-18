package de.cryptodoctor.components;

import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author Saladan
 */
public abstract class CCipher extends JPanel {

    private static final long serialVersionUID = 1L;

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
