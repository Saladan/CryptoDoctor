package de.cryptodoctor.components;

import de.cryptodoctor.Application;
import static de.cryptodoctor.Application.FRAME_HEIGHT;
import static de.cryptodoctor.Application.FRAME_WIDTH;
import static de.cryptodoctor.Application.SYMMETRIC_CIPHERS;
import static de.cryptodoctor.components.CCipher.getCipherName;
import static de.cryptodoctor.graphic.GraphicLoader.createIcon;
import static java.awt.Color.black;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Short.MAX_VALUE;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import org.jdesktop.layout.GroupLayout;
import static org.jdesktop.layout.GroupLayout.DEFAULT_SIZE;
import static org.jdesktop.layout.GroupLayout.LEADING;
import static org.jdesktop.layout.GroupLayout.PREFERRED_SIZE;
import org.jdesktop.layout.GroupLayout.ParallelGroup;
import org.jdesktop.layout.GroupLayout.SequentialGroup;
import static org.jdesktop.layout.GroupLayout.TRAILING;
import sun.lwawt.macosx.CWrapper;

/**
 * @todo Javadoc
 * @author Saladan
 */
public class CFrame extends JFrame {

    private static final Logger LOG = getLogger(CFrame.class.getName());
    private static final long serialVersionUID = 1L;
    private final Application application;
    private final JButton bNew, bEnter;
    private final JMenu mNew;
    private final JPopupMenu popup;
    private final JToggleButton tEnc, tDec;
    private final JPanel tabs, crypt, list;
    private final JTextArea encText, decText;
    private final JSplitPane split;
    private final JScrollPane sEnc, sDec, sList;
    private final List<CPanel> encrypts, decrypts;
    private final GroupLayout layout;

    /**
     * @todo Javadoc
     * @param a the application definition
     */
    public CFrame(Application a) {
        application = a;
        bNew = new JButton();
        bEnter = new JButton();
        tEnc = new JToggleButton();
        tDec = new JToggleButton();
        tabs = new JPanel();
        crypt = new JPanel();
        list = new JPanel();
        encText = new JTextArea();
        decText = new JTextArea();
        split = new JSplitPane();
        sEnc = new JScrollPane();
        sDec = new JScrollPane();
        sList = new JScrollPane();
        encrypts = new ArrayList<>(0);
        decrypts = new ArrayList<>(0);
        layout = new GroupLayout(list);
        mNew = new JMenu();
        popup = new JPopupMenu();
        initObjects();
    }

    private void initObjects() {
        //frame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("CryptoDoctor - Die einfache Ver- und Entschlüsselung");
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setMaximumSize(new Dimension(MAX_VALUE, MAX_VALUE));
        setLocationRelativeTo(null);
        //menu
        popup.setLabel("Neue Verschlüsselung");
        JPopupMenu sym = new JPopupMenu("Symmetrische Verschlüsselungsverfahren");
        for (Class<? extends CCipher> c : SYMMETRIC_CIPHERS) {
            JMenuItem i = new JMenuItem(getCipherName(c));
            i.addActionListener(new AddAction(c));
            sym.add(i);
            popup.add(i);
        }
        //popup.add(sym);
        mNew.add(popup);
        //buttons
        bNew.setText("Neue Verschlüsselung");
        bNew.addActionListener(new ShowNewAction());
        bEnter.setIcon(createIcon("icons:enter"));
        bEnter.addActionListener(new ExecuteAction());
        //list
        sList.setViewportView(list);
        sList.setBorder(null);
        layout.setHorizontalGroup(layout.createParallelGroup().add(0, 0, 0));
        layout.setVerticalGroup(layout.createSequentialGroup().add(0, 0, 0));
        list.setLayout(layout);
        //textfields
        encText.setText("Originaltext");
        sEnc.setViewportView(encText);
        decText.setText("Geheimtext");
        decText.setEditable(false);
        sDec.setViewportView(decText);
        //split
        split.setLeftComponent(sEnc);
        split.setRightComponent(sDec);
        split.setResizeWeight(0.5);
        split.setDividerSize(6);
        split.setBorder(null);
        //crypt
        GroupLayout lCrypt = new GroupLayout(crypt);
        crypt.setLayout(lCrypt);
        lCrypt.setHorizontalGroup(lCrypt.createParallelGroup()
                .add(split, 0, 468, MAX_VALUE));
        lCrypt.setVerticalGroup(lCrypt.createParallelGroup()
                .add(split, 0, 260, MAX_VALUE));
        //tabs
        ActionListener changeMode = new ChangeModeAction();
        ButtonGroup group = new ButtonGroup();
        tEnc.setText("Verschlüsseln");
        tEnc.setSelected(true);
        tEnc.addActionListener(changeMode);
        group.add(tEnc);
        tDec.setText("Entschlüsseln");
        tDec.addActionListener(changeMode);
        group.add(tDec);
        GroupLayout lTabs = new GroupLayout(tabs);
        tabs.setLayout(lTabs);
        lTabs.setHorizontalGroup(lTabs.createSequentialGroup()
                .add(tEnc, DEFAULT_SIZE, 234, MAX_VALUE)
                .add(tDec, DEFAULT_SIZE, 234, MAX_VALUE));
        lTabs.setVerticalGroup(
                lTabs.createParallelGroup()
                .add(tEnc)
                .add(tDec));
        //frame
        GroupLayout lFrame = new GroupLayout(getContentPane());
        getContentPane().setLayout(lFrame);
        lFrame.setHorizontalGroup(lFrame.createSequentialGroup()//720
                .add(6, 6, 6)//6
                .add(lFrame.createParallelGroup()//708
                        .add(tabs, 0, 708, MAX_VALUE)//708
                        .add(lFrame.createSequentialGroup()//708
                                .add(crypt, 0, 468, MAX_VALUE)//468
                                .add(6, 6, 6)//6
                                .add(lFrame.createParallelGroup(LEADING, false)//234
                                        .add(bNew, PREFERRED_SIZE, PREFERRED_SIZE, PREFERRED_SIZE)
                                        .add(lFrame.createParallelGroup(TRAILING)//234
                                                .add(sList, 234, 234, 234)
                                                .add(bEnter, 20, 20, 20)))))
                .add(6, 6, 6));//6
        lFrame.setVerticalGroup(lFrame.createSequentialGroup()//360
                .add(6, 6, 6)//6
                .add(tabs, 29, 29, 29)//29
                .add(6, 6, 6)//6
                .add(lFrame.createParallelGroup()//313
                        .add(crypt)//313
                        .add(lFrame.createSequentialGroup()//313
                                .add(bNew, 20, 20, 20)
                                .add(sList, 0, 266, MAX_VALUE)//288
                                .add(bEnter, 20, 20, 20)))//25
                .add(6, 6, 6));//6
        pack();
    }

    /**
     *
     * @param crypt The Encryption to be added
     */
    public void addCrypt(CPanel crypt) {
        encrypts.add(crypt);
        decrypts.add(0, crypt);
        updateList();
    }

    /**
     *
     * @param crypt The Encryption to be removed
     */
    public void removeCrypt(CPanel crypt) {
        encrypts.remove(crypt);
        decrypts.remove(crypt);
        updateList();
    }

    /**
     * Update the Encryption List on the GUI-Frame
     */
    public void updateList() {
        list.setLayout(null);
        ParallelGroup horiz = layout.createParallelGroup().add(0, 0, 0);
        SequentialGroup vertic = layout.createSequentialGroup().add(0, 0, 0);
        List<CPanel> crypts = tEnc.isSelected() ? encrypts : decrypts;
        list.removeAll();
        if (!crypts.isEmpty()) {
            JPanel panel = new JPanel();
            panel.setBackground(black);
            panel.setBorder(null);
            horiz = horiz.add(panel);
            vertic = vertic.add(panel, 1, 1, 1);
            list.add(panel);
        }
        for (CPanel c : crypts) {
            horiz = horiz.add(c);
            vertic = vertic.add(c);
            list.add(c);
        }
        layout.setHorizontalGroup(horiz);
        layout.setVerticalGroup(vertic);
        list.setLayout(layout);
    }

    /**
     * Execute En- or Decryption
     */
    public void execute() {
        for (CPanel c : encrypts) {
            if (!c.cryptIsValid()) {
                application.getMainRoutine().logException(new IllegalArgumentException("ERROR_USER 0: Crypt not valid"));
                return;
            }
        }
        if (tEnc.isSelected()) {
            decText.setText(encText.getText());
            for (CPanel c : encrypts) {
                decText.setText(c.encrypt(decText.getText()));
            }
        } else {
            encText.setText(decText.getText());
            for (CPanel c : decrypts) {
                encText.setText(c.decrypt(encText.getText()));
            }
        }
    }

    private class AddAction implements ActionListener {

        private final Class<? extends CCipher> c;

        AddAction(Class<? extends CCipher> c) {
            this.c = c;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            addCrypt(new CPanel(application, c));
        }
    }

    private class ShowNewAction implements ActionListener {

        ShowNewAction() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            popup.setVisible(false);
            popup.show(bNew, 0, 0);
        }
    }

    private class ExecuteAction implements ActionListener {

        ExecuteAction() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            execute();
        }
    }

    private class ChangeModeAction implements ActionListener {

        ChangeModeAction() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (tEnc.isSelected() == true) {
                encText.setEditable(true);
                decText.setEditable(false);
            } else {
                decText.setEditable(true);
                encText.setEditable(false);
            }
            updateList();
        }
    }
}
