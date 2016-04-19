/*
 * Copyright 2016 David Ehnert.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
