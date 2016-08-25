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
package de.cryptodoctor.components.cipher.symmetric;

import de.cryptodoctor.components.CCipher;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

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
                .addGap(6)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(labelC)
                        .addComponent(editC))
                .addGap(6));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(12)
                .addComponent(labelC, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(3)
                .addComponent(editC, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(6));
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
