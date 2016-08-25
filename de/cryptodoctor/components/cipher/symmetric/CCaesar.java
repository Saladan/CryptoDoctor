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
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
                .addGap(6)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(labelC)
                        .addComponent(sliderC))
                .addGap(6));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(12)
                .addComponent(labelC, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(3)
                .addComponent(sliderC, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(6));
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
