/*
 * The MIT License
 *
 * Copyright 2016 David Ehnert (Saladan).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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
