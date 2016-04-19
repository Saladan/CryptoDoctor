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
import static java.lang.Math.min;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.swing.JCheckBox;
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
public class CBinary extends CCipher {

    /**
     *
     */
    public static final String CIPHER_NAME = "Binäre Verschlüsselung (XOR)";

    private static final Logger LOG = getLogger(CBinary.class.getName());
    private static final long serialVersionUID = 1L;
    private final JTextField editC;
    private final JLabel labelC;
    private final JCheckBox checkC;

    /**
     * @todo Javadoc
     */
    public CBinary() {
        editC = new JTextField();
        labelC = new JLabel();
        checkC = new JCheckBox();
        initObjects();
    }

    private void initObjects() {
        editC.setDocument(new BinaryDocument());
        labelC.setText("Binärer Schlüssel:");
        checkC.setText("16 Bit Zeichen nutzen");
        checkC.setSelected(false);
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .add(6, 6, 6)
                .add(layout.createParallelGroup(LEADING)
                        .add(labelC)
                        .add(editC)
                        .add(checkC))
                .add(6, 6, 6));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .add(12, 12, 12)
                .add(labelC, PREFERRED_SIZE, PREFERRED_SIZE, PREFERRED_SIZE)
                .add(3, 3, 3)
                .add(editC, PREFERRED_SIZE, PREFERRED_SIZE, PREFERRED_SIZE)
                .add(6, 6, 6)
                .add(checkC, PREFERRED_SIZE, PREFERRED_SIZE, PREFERRED_SIZE)
                .add(6, 6, 6));
        super.initHeight(27 + labelC.getPreferredSize().height + editC.getPreferredSize().height + checkC.getPreferredSize().height);
    }

    @Override
    public String encrypt(String text) {
        return crypt(text.toCharArray(), editC.getText());
    }

    @Override
    public String decrypt(String text) {
        return crypt(text.toCharArray(), editC.getText());
    }

    private String crypt(char[] raw, String key) {
        int length = checkC.isSelected() ? 16 : 8;
        for (int i = 0; i < raw.length; i++) {
            int pos = (length * i) % key.length();
            String k = key;
            while (pos + length > k.length()) {
                k += k;
            }
            raw[i] ^= getChar(k.substring(pos, pos + length).toCharArray());
        }
        return new String(raw);
    }

    private char getChar(char[] raw) {
        char c = 0;
        for (int i = 0; i < raw.length; i++) {
            c += raw[raw.length - 1 - i] == '1' ? 1 << i : 0;
        }
        return c;
    }

    @Override
    public boolean cryptIsValid() {
        return !editC.getText().isEmpty();
    }

    private class BinaryDocument extends PlainDocument {

        private static final long serialVersionUID = 1L;

        BinaryDocument() {
        }

        @Override
        public void insertString(int offset, String s, AttributeSet attributeSet) throws BadLocationException {
            String x = s.substring(0, min(64 - editC.getText().length(), s.length()));
            if (x.length() == 0) {
                return;
            }
            for (char c : x.toCharArray()) {
                if (c < '0' || c > '1') {
                    return;
                }
            }
            super.insertString(offset, x, attributeSet);
        }
    }

}
