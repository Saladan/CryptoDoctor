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
package de.cryptodoctor.components.cipher.symmetric;

import de.cryptodoctor.components.CCipher;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import org.jdesktop.layout.GroupLayout;
import static org.jdesktop.layout.GroupLayout.LEADING;
import static org.jdesktop.layout.GroupLayout.PREFERRED_SIZE;

/**
 * @todo Javadoc
 * @author Saladan
 */
public class CVigenere extends CCipher {

    /**
     *
     */
    public static final String CIPHER_NAME = "Vigenère-Verschlüsselung";

    private static final Logger LOG = getLogger(CVigenere.class.getName());
    private static final long serialVersionUID = 1L;
    private final JTextField editC;
    private final JLabel labelC;

    /**
     * @todo Javadoc
     */
    public CVigenere() {
        editC = new JTextField();
        labelC = new JLabel();
        initObjects();
    }

    private void initObjects() {
        editC.setDocument(new UpperCaseDocument());
        labelC.setText("Verschlüsselungswort:");
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .add(6, 6, 6)
                .add(layout.createParallelGroup(LEADING)
                        .add(labelC)
                        .add(editC))
                .add(6, 6, 6));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .add(12, 12, 12)
                .add(labelC, PREFERRED_SIZE, PREFERRED_SIZE, PREFERRED_SIZE)
                .add(3, 3, 3)
                .add(editC, PREFERRED_SIZE, PREFERRED_SIZE, PREFERRED_SIZE)
                .add(6, 6, 6));
        super.initHeight(21 + labelC.getPreferredSize().height + editC.getPreferredSize().height);
    }

    @Override
    public String encrypt(String text) {
        char[] t = editC.getText().toCharArray();
        int[] c = new int[t.length];
        for (int i = 0; i < t.length; i++) {
            c[i] = t[i] - 65;
        }
        return crypt(text.toCharArray(), c);
    }

    @Override
    public String decrypt(String text) {
        char[] t = editC.getText().toCharArray();
        int[] c = new int[t.length];
        for (int i = 0; i < t.length; i++) {
            c[i] = 91 - t[i];
        }
        return crypt(text.toCharArray(), c);
    }

    private String crypt(char[] raw, int[] c) {
        int a = 0;
        for (int i = 0; i < raw.length; i++) {
            if (raw[i] >= 65 && raw[i] <= 90) {
                raw[i] = (char) (((raw[i] - 65 + c[a]) % 26) + 65);
            }
            if (raw[i] >= 97 && raw[i] <= 122) {
                raw[i] = (char) (((raw[i] - 97 + c[a]) % 26) + 97);
            }
            a += 1;
            if (a == c.length) {
                a = 0;
            }
        }
        return new String(raw);
    }

    @Override
    public boolean cryptIsValid() {
        if (editC.getText().trim().isEmpty()) {
            return false;
        }
        List<Character> a = new ArrayList<>(0);
        for (char c = 'A'; c <= 'Z'; c++) {
            a.add(c);
        }
        for (int i = 0; i < editC.getText().length(); i++) {
            if (!a.contains(editC.getText().toCharArray()[i])) {
                return false;
            }
        }
        return true;
    }

    private class UpperCaseDocument extends PlainDocument {

        private static final long serialVersionUID = 1L;

        UpperCaseDocument() {
        }

        @Override
        public void insertString(int offset, String s, AttributeSet attributeSet) throws BadLocationException {
            String newS = s.toUpperCase();
            for (char c : newS.toCharArray()) {
                if (c < 'A' || c > 'Z') {
                    return;
                }
            }
            super.insertString(offset, newS, attributeSet);
        }
    }

}
