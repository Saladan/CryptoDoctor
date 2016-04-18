/*
 * Copyright 2016 david.
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
