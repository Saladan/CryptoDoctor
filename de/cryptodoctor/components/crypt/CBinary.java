package de.cryptodoctor.components.crypt;

import de.cryptodoctor.components.CContent;
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
 *
 * @author Saladan
 */
public class CBinary extends CContent {

    private static final Logger LOG = getLogger(CBinary.class.getName());
    private static final long serialVersionUID = 1L;
    private final JTextField editC;
    private final JLabel labelC;
    private final JCheckBox checkC;

    /**
     *
     */
    public CBinary() {
        editC = new JTextField();
        labelC = new JLabel();
        checkC = new JCheckBox();

        editC.setDocument(new BinaryDocument());

        labelC.setText("Binärer Schlüssel:");

        checkC.setText("16 Bit Zeichen nutzen");
        checkC.setSelected(true);

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
        super.initSize(27 + labelC.getPreferredSize().height + editC.getPreferredSize().height + checkC.getPreferredSize().height);
    }

    /**
     * Encrypts the given text width specific Encryption rules.
     *
     * @param text The text to be encrypted
     * @return The encrypted text
     */
    @Override
    public String encrypt(String text) {
        return crypt(text.toCharArray(), editC.getText());
    }

    /**
     * Decrypts the given text with specific Decryption rules.
     *
     * @param text The text to be decrypted
     * @return The decrypted text
     */
    @Override
    public String decrypt(String text) {
        return crypt(text.toCharArray(), editC.getText());
    }

    String crypt(char[] raw, String key) {
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

    char getChar(char[] raw) {
        char c = 0;
        for (int i = 0; i < raw.length; i++) {
            c += raw[raw.length - 1 - i] == '1' ? 1 << i : 0;
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
