package de.cryptodoctor.components;

import de.cryptodoctor.Application;
import de.cryptodoctor.components.cipher.Cipher;
import static de.cryptodoctor.components.cipher.Cipher.getNameOf;
import static de.cryptodoctor.graphic.GraphicLoader.createIcon;
import static java.awt.Color.black;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Short.MAX_VALUE;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.Border;
import org.jdesktop.layout.GroupLayout;
import static org.jdesktop.layout.GroupLayout.CENTER;

/**
 * @todo Javadoc
 * @author Saladan
 */
public class CPanel extends JPanel {

    private static final Logger LOG = getLogger(CPanel.class.getName());
    private static final long serialVersionUID = 1L;
    private final Application application;
    private final JPanel menu;
    private final JButton hide;
    private final ImageIcon iClose, iOpen;
    private final CCipher content;
    private boolean opened;

    /**
     * @todo Javadoc
     * @param a
     * @param Type
     */
    public CPanel(Application a, Class<? extends CCipher> Type) {
        application = a;
        menu = new JPanel();
        try {
            content = Type.newInstance();
        } catch (InstantiationException ex) {
            InstantiationException n = new InstantiationException("ERROR_FATAL 0: Class object cannot be instantiated");
            n.setStackTrace(ex.getStackTrace());
            application.getMainRoutine().logException(n);
            while (true) {
            }
        } catch (IllegalAccessException ex) {
            IllegalAccessException n = new IllegalAccessException("ERROR_FATAL 1: No access to constructor");
            n.setStackTrace(ex.getStackTrace());
            application.getMainRoutine().logException(n);
            while (true) {
            }
        }
        hide = new JButton();
        opened = true;
        iClose = createIcon("icons:left");
        iOpen = createIcon("icons:right");
        initObjects();
    }

    private void initObjects() {
        setBorder(new CPanelBorder());
        JLabel name = new JLabel(getNameOf(content.getClass()));
        JButton moveUp = new JButton();
        JButton moveDown = new JButton();
        JButton close = new JButton();
        close.addActionListener(new CloseAction(this));
        hide.addActionListener(new HideAction());
        Dimension size = new Dimension(16, 16);
        moveUp.setIcon(createIcon("icons:up"));
        moveUp.setMinimumSize(size);
        moveUp.setPreferredSize(size);
        moveUp.setMaximumSize(size);
        moveDown.setIcon(createIcon("icons:down"));
        moveDown.setMinimumSize(size);
        moveDown.setPreferredSize(size);
        moveDown.setMaximumSize(size);
        close.setIcon(createIcon("icons:close"));
        close.setMinimumSize(size);
        close.setPreferredSize(size);
        close.setMaximumSize(size);
        hide.setMinimumSize(size);
        hide.setPreferredSize(size);
        hide.setMaximumSize(size);
        JToolBar control = new JToolBar();
        control.setFloatable(false);
        control.setBorder(null);
        control.add(moveUp);
        control.add(moveDown);
        control.add(hide);
        control.add(close);
        size = new Dimension(0, 20);
        menu.setMinimumSize(size);
        menu.setPreferredSize(size);
        size = new Dimension(MAX_VALUE, 20);
        menu.setMaximumSize(size);
        GroupLayout lMenu = new GroupLayout(menu);
        menu.setLayout(lMenu);
        lMenu.setHorizontalGroup(
                lMenu.createSequentialGroup()
                .add(2, 2, 2)
                .add(name, 0, 0, MAX_VALUE)
                .add(2, 2, 2)
                .add(control, 64, 64, 64)
                .add(2, 2, 2));
        lMenu.setVerticalGroup(
                lMenu.createParallelGroup(CENTER)
                .add(name, 20, 20, 20)
                .add(control, 16, 16, 16));
        GroupLayout layout = new GroupLayout(this);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                .add(menu)
                .add(content));
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .add(menu)
                .add(content));
        setLayout(layout);
        setOpened(true);
    }

    /**
     *
     * @param opened
     */
    public void setOpened(boolean opened) {
        this.opened = opened;
        content.setVisible(opened);
        hide.setIcon(opened ? iClose : iOpen);
    }

    /**
     * Indicates weather the Encryption Field is valid or invalid
     *
     * @return true if field is valid, false otherwise
     */
    public boolean cryptIsValid() {
        return content.cryptIsValid();
    }

    /**
     * Encrypts the given text width specific Encryption rules.
     *
     * @param text The text to be encrypted
     * @return The encrypted text
     */
    public String encrypt(String text) {
        return content.encrypt(text);
    }

    /**
     * Decrypts the given text with specific Decryption rules.
     *
     * @param text The text to be decrypted
     * @return The decrypted text
     */
    public String decrypt(String text) {
        return content.decrypt(text);
    }

    private static class CPanelBorder implements Border {

        private final Insets insets;

        CPanelBorder() {
            insets = new Insets(0, 1, 1, 1);
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(black);
            g.drawLine(x, y, x, y + height - 1);
            g.drawLine(x, y + height - 1, x + width - 1, y + height - 1);
            g.drawLine(x + width - 1, y + height - 1, x + width - 1, y);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return insets;
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }
    }

    private class HideAction implements ActionListener {

        HideAction() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            setOpened(!opened);
        }
    }

    private class CloseAction implements ActionListener {

        private final CPanel self;

        CloseAction(CPanel self) {
            this.self = self;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            application.getFrame().removeCrypt(self);
        }
    }

}
