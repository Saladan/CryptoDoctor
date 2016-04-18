package de.cryptodoctor.components.cipher.symmetric;

import de.cryptodoctor.components.CCipher;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jdesktop.layout.GroupLayout;
import static org.jdesktop.layout.GroupLayout.LEADING;
import static org.jdesktop.layout.GroupLayout.PREFERRED_SIZE;

/**
 * @todo Javadoc
 * @author Saladan
 */
public class CCaesar extends CCipher {

    /**
     *
     */
    public static final String CIPHER_NAME = "Cäsar-Verschlüsselung";

    private static final Logger LOG = getLogger(CCaesar.class.getName());
    private static final long serialVersionUID = 1L;
    private final JSlider sliderC;
    private final JLabel labelC;

    /**
     * @todo Javadoc
     */
    public CCaesar() {
        sliderC = new JSlider();
        labelC = new JLabel();
        initObjects();
    }

    private void initObjects() {
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
        GroupLayout layout = new GroupLayout(this);
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
        super.initHeight(21 + labelC.getPreferredSize().height + sliderC.getPreferredSize().height);
    }

    @Override
    public String encrypt(String text) {
        return crypt(text.toCharArray(), sliderC.getValue() - 1);
    }

    @Override
    public String decrypt(String text) {
        return crypt(text.toCharArray(), 27 - sliderC.getValue());
    }

    private String crypt(char[] raw, int c) {
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

    @Override
    public boolean cryptIsValid() {
        return true;
    }
}
