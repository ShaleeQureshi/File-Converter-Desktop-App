package GUI.Pages;

/*
* Date: May 21, 2020
* Authors: Shahrukh Qureshi and Jalal Qureshi
* Description: This class creates the Conversion Chooser Frame (Second Page)
*/

// Import Statements
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import GUI.GUIMethods;

public class ChooserFrame implements ActionListener {

    private JButton btnContinue;
    private JLabel lblSelect;
    private JMenuBar menuBar;
    private final JMenu menu;
    private final JMenuItem[] items;
    private JFrame frameChooser;
    private final JComboBox<Object> boxOptions;
    private ArrayList<String> listOptions;
    private final File file;

    // Void Constructor
    public ChooserFrame() {

        // JFrame
        frameChooser = new JFrame();
        frameChooser = GUIMethods.frameSetter(frameChooser, 300, 300);

        // JButton
        btnContinue = new JButton("Continue");
        btnContinue = GUIMethods.btnSetter(btnContinue);

        // JLabel 
        lblSelect = new JLabel("<html><u>What File Conversion do you need?");

        // JMenuBar/JMenu/JMenuItem
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        items = new JMenuItem[2];
        items[0] = new JMenuItem("Settings");
        items[0].addActionListener(this);
        items[1] = new JMenuItem("Exit");
        items[1].addActionListener(this);
        menuBar = GUIMethods.barSetter(menuBar, menu, items);
        frameChooser.setJMenuBar(menuBar);

        // JComboBox
        file = new File("./Documents/options.txt");
        listOptions = new ArrayList<String>();
        listOptions = GUIMethods.readFile(listOptions, file);
        boxOptions = new JComboBox <Object> (listOptions.toArray());

        // Location set with (x, y, width, height)

        // JButton
        btnContinue.setBounds(45, 100, 200, 90);
        btnContinue.addActionListener(this);
        frameChooser.add(btnContinue);

        // JLabel 
        lblSelect.setBounds(45, 10, 220, 30);
        frameChooser.add(lblSelect);

        // JComboBox
        boxOptions.setBounds(45, 40, 200, 50);
        frameChooser.add(boxOptions);

        // Making the window visible
        frameChooser.setVisible(true);

    } // Constructor

    @Override
    public void actionPerformed(final ActionEvent e) {

        // If the user goes to File -> Exit the following will occur
        if (e.getSource() == items[1]) {
            frameChooser.dispose();
            GUIMethods.thankYouMsg();
            System.exit(0);
        }

    } // actionPerformed Method

} // Class Chooser