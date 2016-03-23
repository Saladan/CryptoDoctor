package de.cryptodoctor.components.crypt;

import de.cryptodoctor.components.CContent;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import static org.jdesktop.layout.GroupLayout.LEADING;
import static org.jdesktop.layout.GroupLayout.PREFERRED_SIZE;

/**
 *
 * @author Saladan
 */
public class CCaesar extends CContent {

    private static final Logger LOG = getLogger(CCaesar.class.getName());
    JSlider sliderC;
    JLabel labelC;

    /**
     *
     */
    public CCaesar() {

        sliderC = new JSlider();
        labelC = new JLabel();

        sliderC.setMaximum(26);
        sliderC.setMinimum(1);
        sliderC.setValue(1);
        sliderC.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                labelC.setText("Verschiebungsweite:   A = " + (char) (64 + sliderC.getValue()));
            }
        });

        labelC.setText("Verschiebungsweite:   A = A");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .add(6, 6, 6)
                .add(layout.createParallelGroup(LEADING)
                        .add(labelC)
                        .add(sliderC))
                .add(6, 6, 6));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .add(12, 12, 12)
                .add(labelC, PREFERRED_SIZE, PREFERRED_SIZE, PREFERRED_SIZE)
                .add(3, 3, 3)
                .add(sliderC, PREFERRED_SIZE, PREFERRED_SIZE, PREFERRED_SIZE)
                .add(6, 6, 6));
    }

    /**
     * Encrypts the given text width specific Encryption rules.
     *
     * @param text The text to be encrypted
     * @return The encrypted text
     */
    @Override
    public String encrypt(String text) {
        return crypt(text.toCharArray(), sliderC.getValue() - 1);
    }

    /**
     * Decrypts the given text with specific Decryption rules.
     *
     * @param text The text to be decrypted
     * @return The decrypted text
     */
    @Override
    public String decrypt(String text) {
        return crypt(text.toCharArray(), 27 - sliderC.getValue());
    }

    String crypt(char[] raw, int c) {
        for (int i = 0; i < raw.length; i++) {
            if (raw[i] >= 65 && raw[i] <= 90) {
                raw[i] = (char) (((raw[i] - 65 + c) % 26) + 65);
            }
            if (raw[i] >= 97 && raw[i] <= 122) {
                raw[i] = (char) (((raw[i] - 97 + c) % 26) + 97);
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
        return true;
    }
}
