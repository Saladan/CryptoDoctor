package de.saladan.cryptodoctor.components;

import de.saladan.cryptodoctor.Info;
import de.saladan.cryptodoctor.components.crypt.*;
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
import static org.jdesktop.layout.GroupLayout.PREFERRED_SIZE;

/**
 *
 * @author Saladan
 */
public class CPanel extends JPanel {

    private static final Logger LOG = getLogger(CPanel.class.getName());
    JPanel menu;
    CContent content;
    JButton close;
    ImageIcon iClose, iOpen;
    boolean opened;
    final Class[] crypts = new Class[]{null, CCaesar.class, CVigenere.class};
    final String[] names = new String[]{"<bitte wählen>", "Cäsar-Verschlüsselung", "Vigenére-Verschlüsselung"};

    /**
     *
     */
    public CPanel() {
        setBorder(new LineBorder(black));
        final JComboBox<String> combo = new JComboBox<>(names);
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
                        content = exists ? (CContent) crypts[index].newInstance() : null;
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
        moveUp.setIcon(new ImageIcon(getClass().getClassLoader().getResource("de/saladan/cryptodoctor/graphic/up.png")));
        moveUp.setMinimumSize(size);
        moveUp.setPreferredSize(size);
        moveUp.setMaximumSize(size);
        moveDown.setIcon(new ImageIcon(getClass().getClassLoader().getResource("de/saladan/cryptodoctor/graphic/down.png")));
        moveDown.setMinimumSize(size);
        moveDown.setPreferredSize(size);
        moveDown.setMaximumSize(size);
        close.setMinimumSize(size);
        close.setPreferredSize(size);
        close.setMaximumSize(size);
        iClose = new ImageIcon(getClass().getClassLoader().getResource("de/saladan/cryptodoctor/graphic/left.png"));
        iOpen = new ImageIcon(getClass().getClassLoader().getResource("de/saladan/cryptodoctor/graphic/right.png"));
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
}
