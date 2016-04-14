package de.cryptodoctor.components;

import de.cryptodoctor.Application;
import static de.cryptodoctor.Application.CIPHER_CLASSES;
import static de.cryptodoctor.Application.CIPHER_NAMES;
import static de.cryptodoctor.Application.ERROR_MESSAGE;
import static de.cryptodoctor.graphic.GraphicLoader.createIcon;
import static java.awt.Color.black;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import static java.lang.Short.MAX_VALUE;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;
import org.jdesktop.layout.GroupLayout;
import static org.jdesktop.layout.GroupLayout.CENTER;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Saladan
 */
public class CPanel extends JPanel {

    private static final Logger LOG = getLogger(CPanel.class.getName());
    private static final long serialVersionUID = 1L;
    private final Application application;
    private final JPanel menu;
    private CContent content;
    private JButton hide;
    private final ImageIcon iClose, iOpen;
    private boolean opened;

    /**
     *
     * @param a
     */
    public CPanel(Application a) {
        application = a;
        setBorder(new LineBorder(black));
        final JComboBox<String> combo = new JComboBox<>(CIPHER_NAMES);
        combo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    try {
                        if (content != null) {
                            remove(content);
                        }
                        int index = combo.getSelectedIndex();
                        boolean exists = index > 0;
                        content = exists ? (CContent) CIPHER_CLASSES[index].newInstance() : null;
                        hide.setEnabled(exists);
                        update(exists);
                    } catch (ClassCastException | InstantiationException | IllegalAccessException ex) {
                        
                        LOG.log(SEVERE, ERROR_MESSAGE, ex);
                    }
                }
            }
        });
        JButton moveUp = new JButton();
        JButton moveDown = new JButton();
        JButton close = new JButton();
        final CPanel myself = this;
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                application.getFrame().removeCrypt(myself);
            }
        });
        hide = new JButton();
        hide.setEnabled(false);
        hide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setOpened(!opened);
            }
        });
        Dimension size = new Dimension(20, 20);
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
        iClose = createIcon("icons:left");
        iOpen = createIcon("icons:right");
        JToolBar control = new JToolBar();
        control.setFloatable(false);
        control.setBorder(null);
        control.add(moveUp);
        control.add(moveDown);
        control.add(hide);
        control.add(close);
        menu = new JPanel();
        size = new Dimension(0, 25);
        menu.setMinimumSize(size);
        menu.setPreferredSize(size);
        size = new Dimension(MAX_VALUE, 25);
        menu.setMaximumSize(size);
        GroupLayout lMenu = new GroupLayout(menu);
        menu.setLayout(lMenu);
        lMenu.setHorizontalGroup(
                lMenu.createSequentialGroup()
                .add(combo, 0, 0, MAX_VALUE)
                .add(2, 2, 2)
                .add(control, 80, 80, 80));
        lMenu.setVerticalGroup(
                lMenu.createParallelGroup(CENTER)
                .add(combo)
                .add(control, 20, 20, 20));
        content = null;
        opened = false;
        hide.setIcon(iOpen);
        GroupLayout layout = new GroupLayout(this);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                .add(menu));
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .add(menu));
        setLayout(layout);
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
     *
     * @param enabled
     */
    public void update(boolean enabled) {
        opened = enabled;
        hide.setIcon(enabled ? iClose : iOpen);
        if (enabled) {
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
        } else {
            GroupLayout layout = new GroupLayout(this);
            layout.setHorizontalGroup(
                    layout.createParallelGroup()
                    .add(menu));
            layout.setVerticalGroup(
                    layout.createSequentialGroup()
                    .add(menu));
            setLayout(layout);
        }
    }
    
    /**
     * Indicates weather the Encryption Field is valid or invalid
     *
     * @return true if field is valid, false otherwise
     */
    public boolean cryptIsValid() {
        return content == null ? false : content.cryptIsValid();
    }
    
    /**
     * Indicates weather the Encryption Field exists or does not exist.
     *
     * @return true if field exists, false otherwise
     */
    public boolean cryptExists() {
        return content != null;
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
}
