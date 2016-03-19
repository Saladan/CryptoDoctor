package de.cryptodoctor.components.crypt;

import de.cryptodoctor.components.CContent;
import static java.lang.Short.MAX_VALUE;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import static org.jdesktop.layout.GroupLayout.LEADING;

/**
 *
 * @author Saladan
 */
public class CVigenere extends CContent {

    private static final Logger LOG = getLogger(CVigenere.class.getName());
    JTextField editC;
    JLabel labelC;

    /**
     *
     */
    public CVigenere() {
        editC = new JTextField();
        labelC = new JLabel();

        editC.setDocument(new UpperCaseDocument());

        labelC.setText("Verschlüsselungswort:");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .add(6, 6, 6)
                .add(layout.createParallelGroup(LEADING)
                        .add(labelC)
                        .add(editC))
                .add(6, 6, 6));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .add(12, 12, 12)
                .add(labelC)
                .add(3, 3, 3)
                .add(editC)
                .add(0, 0, MAX_VALUE));
    }

    /**
     * Encrypts the given text width specific Encryption rules.
     *
     * @param text The text to be encrypted
     * @return The encrypted text
     */
    @Override
    public String encrypt(String text) {
        char[] t = editC.getText().toCharArray();
        int[] c = new int[t.length];
        for (int i = 0; i < t.length; i++) {
            c[i] = t[i] - 65;
        }
        return crypt(text.toCharArray(), c);
    }

    /**
     * Decrypts the given text with specific Decryption rules.
     *
     * @param text The text to be decrypted
     * @return The decrypted text
     */
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

    /**
     * Indicates weather the Encryption Field is valid or invalid
     *
     * @return true if field is valid, false otherwise
     */
    @Override
    public boolean cryptIsValid() {
        if (editC.getText().trim().isEmpty()) {
            return false;
        }
        List<Character> a = new ArrayList<>();
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

        UpperCaseDocument() {
        }

        @Override
        public void insertString(int offset, String s, AttributeSet attributeSet) throws BadLocationException {
            s = s.toUpperCase();
            for (char c : s.toCharArray()) {
                if (c < 'A' || c > 'Z') {
                    return;
                }
            }
            super.insertString(offset, s, attributeSet);
        }
    }

}
