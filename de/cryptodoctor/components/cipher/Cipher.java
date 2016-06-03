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
package de.cryptodoctor.components.cipher;

import de.cryptodoctor.components.CCipher;
import de.cryptodoctor.components.cipher.symmetric.CBinary;
import de.cryptodoctor.components.cipher.symmetric.CCaesar;
import de.cryptodoctor.components.cipher.symmetric.CVigenere;

/**
 *
 * @author Saladan
 */
public enum Cipher {

    /**
     *
     */
    Caesar(CCaesar.class, CCaesar.CIPHER_NAME, 0),

    /**
     *
     */
    Vigenere(CVigenere.class, CVigenere.CIPHER_NAME, 0),

    /**
     *
     */
    Binary(CBinary.class, CBinary.CIPHER_NAME, 0);
    
    /**
     *
     */
    public static final int TYPE_SYMMETRIC = 0;

    /**
     *
     */
    public static final int TYPE_ASYMMETRIC = 1;
    
    private final Class<? extends CCipher> T;
    private final String name;
    private final int type;
    
    Cipher(Class<?extends CCipher> T, String name, int type) {
        this.T = T;
        this.name = name;
        this.type = type;
    }
    
    /**
     *
     * @return
     */
    public Class<? extends CCipher> getT() {
        return T;
    }
    
    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }
    
    /**
     *
     * @return
     */
    public int getType() {
        return type;
    }
    
    /**
     *
     * @param cipher
     * @return
     */
    public static String getNameOf(Class<? extends CCipher> cipher) {
        for (Cipher a : values()) {
            if (a.getT().equals(cipher)) {
                return a.getName();
            }
        }
        return null;
    }
}
