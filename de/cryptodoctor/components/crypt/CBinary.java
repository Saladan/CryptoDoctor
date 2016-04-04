package de.cryptodoctor.components.crypt;

import de.cryptodoctor.components.CContent;
import static java.lang.Math.min;
import static java.lang.Math.pow;
import static java.lang.Short.MAX_VALUE;
import static java.lang.System.out;
import static java.util.logging.Level.SEVERE;
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
public class CBinary extends CContent {

    private static final Logger LOG = getLogger(CBinary.class.getName());
    JTextField editC;
    JLabel labelC;

    /**
     *
     */
    public CBinary() {
        editC = new JTextField();
        labelC = new JLabel();

        editC.setDocument(new BinaryDocument());

        labelC.setText("Binärer Schlüssel:");

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
        return crypt(text.toCharArray(), getByte(editC.getText().toCharArray()));
    }

    /**
     * Decrypts the given text with specific Decryption rules.
     *
     * @param text The text to be decrypted
     * @return The decrypted text
     */
    @Override
    public String decrypt(String text) {
        return crypt(text.toCharArray(), getByte(editC.getText().toCharArray()));
    }

    String crypt(char[] raw, byte c) {
        for (int i = 0; i < raw.length; i++) {
            raw[i] ^= c;
        }
        return new String(raw);
    }
    
    byte getByte(char[] raw) {
        byte c = 0;
        for (int i = 0; i < 8; i++) {
            c += raw[7 - i] == '1' ? pow(2, i) : 0;
        }
        return c;
    }

    /**
     * Indicates weather the Encryption Field is valid or invalid
     *
     * @return true if field is valid, false otherwise
     */
    @Override
    public boolean cryptIsValid() {
        try {
            editC.getDocument().insertString(0, "00000000", null);
        } catch (BadLocationException ex) {
            getLogger(CBinary.class.getName()).log(SEVERE, null, ex);
        }
        return true;
    }

    private class BinaryDocument extends PlainDocument {

        BinaryDocument() {
        }

        @Override
        public void insertString(int offset, String s, AttributeSet attributeSet) throws BadLocationException {
            s = s.substring(0, min(8 - editC.getText().length(), s.length()));
            for (char c : s.toCharArray()) {
                if (c < '0' || c > '1') {
                    return;
                }
            }
            super.insertString(offset, s, attributeSet);
        }
    }

}
