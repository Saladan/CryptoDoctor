package de.cryptodoctor.components;

import de.cryptodoctor.Info;
import static de.cryptodoctor.Info.CIPHER_CLASSES;
import static de.cryptodoctor.Info.CIPHER_NAMES;
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
import static java.util.logging.Logger.getLogger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;
import org.jdesktop.layout.GroupLayout;
import static org.jdesktop.layout.GroupLayout.CENTER;

/**
 *
 * @author Saladan
 */
public class CPanel extends JPanel {

    private static final Logger LOG = getLogger(CPanel.class.getName());
    private static final long serialVersionUID = 1L;
    private final JPanel menu;
    private CContent content;
    private JButton close;
    private final ImageIcon iClose, iOpen;
    private boolean opened;

    /**
     *
     */
    public CPanel() {
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
                        close.setEnabled(exists);
                        update(exists);
                    } catch (ClassCastException | InstantiationException | IllegalAccessException ex) {
                        LOG.log(SEVERE, Info.ERROR, ex);
                    }
                }
            }
        });
        JButton moveUp = new JButton();
        JButton moveDown = new JButton();
        close = new JButton();
        close.setEnabled(false);
        close.addActionListener(new ActionListener() {
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
        close.setMinimumSize(size);
        close.setPreferredSize(size);
        close.setMaximumSize(size);
        iClose = createIcon("icons:left");
        iOpen = createIcon("icons:right");
        JToolBar control = new JToolBar();
        control.setFloatable(false);
        control.setBorder(null);
        control.add(moveUp);
        control.add(moveDown);
        control.add(close);
        menu = new JPanel();
        GroupLayout lMenu = new GroupLayout(menu);
        menu.setLayout(lMenu);
        lMenu.setHorizontalGroup(
                lMenu.createSequentialGroup()
                .add(combo, 0, 0, MAX_VALUE)
                .add(2, 2, 2)
                .add(control, 60, 60, 60));
        lMenu.setVerticalGroup(
                lMenu.createParallelGroup(CENTER)
                .add(combo)
                .add(control, 20, 20, 20));
        content = null;
        opened = false;
        close.setIcon(iOpen);
        GroupLayout layout = new GroupLayout(this);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                .add(menu));
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .add(menu, 25, 25, 25));
        setLayout(layout);
    }

    /**
     *
     * @param opened
     */
    public void setOpened(boolean opened) {
        this.opened = opened;
        content.setVisible(opened);
        close.setIcon(opened ? iClose : iOpen);
    }

    /**
     *
     * @param enabled
     */
    public void update(boolean enabled) {
        opened = enabled;
        close.setIcon(enabled ? iClose : iOpen);
        if (enabled) {
            GroupLayout layout = new GroupLayout(this);
            layout.setHorizontalGroup(
                    layout.createParallelGroup()
                    .add(menu)
                    .add(content));
            layout.setVerticalGroup(
                    layout.createSequentialGroup()
                    .add(menu, 25, 25, 25)
                    .add(content, 75, 75, 75));
            setLayout(layout);
        } else {
            GroupLayout layout = new GroupLayout(this);
            layout.setHorizontalGroup(
                    layout.createParallelGroup()
                    .add(menu));
            layout.setVerticalGroup(
                    layout.createSequentialGroup()
                    .add(menu, 25, 25, 25));
            setLayout(layout);
        }
    }
    
    public boolean cryptIsValid() {
        return content == null ? false : content.cryptIsValid();
    }
    
    public boolean cryptExists() {
        return content != null;
    }
    
    public String encrypt(String text) {
        return content.encrypt(text);
    }
    
    public String decrypt(String text) {
        return content.decrypt(text);
    }
}
