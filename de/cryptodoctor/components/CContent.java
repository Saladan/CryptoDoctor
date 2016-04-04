package de.cryptodoctor.components;

import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author Saladan
 */
public abstract class CContent extends JPanel {

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
     *
     * @param i
     */
    public void initSize(int i) {
        Dimension size = new Dimension(0, i);
        setMinimumSize(size);
        setPreferredSize(size);
        size = new Dimension(250, i);
        setMaximumSize(size);
    }
}
