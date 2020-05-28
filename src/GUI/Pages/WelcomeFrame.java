package src.GUI.Pages;

/*
* Date: May 21, 2020
* Authors: Shahrukh Qureshi
* Description: This class creates the Welcome Frame (First Page)
*
* Method List: 
* 1. void actionPerformed(final ActionEvent e) = Handles all events on JComponents
*/

// Import Statements
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.GUI.Methods.GUIMethods;

public class WelcomeFrame implements ActionListener {

    private JButton btnWelcome;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem[] items;
    private JFrame frameWelcome;

    // Void Constructor
    public WelcomeFrame() {

        // JFrame
        frameWelcome = new JFrame(); // Initializing the JFrame
        frameWelcome = GUIMethods.frameSetter(frameWelcome, 300, 300); // Altering it

        // JButton
        btnWelcome = new JButton("Welcome");
        btnWelcome = GUIMethods.btnSetter(btnWelcome); // Altering the JButton

        // JMenuBar/JMenu/JMenuItem
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        items = new JMenuItem[2];
        items[0] = new JMenuItem("Settings");
        items[0].addActionListener(this);
        items[1] = new JMenuItem("Exit");
        items[1].addActionListener(this);
        menuBar = GUIMethods.barSetter(menuBar, menu, items);
        frameWelcome.setJMenuBar(menuBar);

        // Location set with (x, y, width, height)

        // JButton
        btnWelcome.setBounds(45, 70, 200, 90);
        btnWelcome.addActionListener(this);
        frameWelcome.add(btnWelcome);

        // Making the window visible
        frameWelcome.setVisible(true);

    }// Constructor

    @Override
    public void actionPerformed(ActionEvent e) {

        // If the user goes to File -> Exit the following will occur
        if (e.getSource() == items[1]) {
            frameWelcome.dispose();
            GUIMethods.showMsg("Thank you for using File Converter");
            System.exit(0);
        }
        // If the user presses the welcome button the following will occur
        else if (e.getSource() == btnWelcome) {
            frameWelcome.dispose();
            new ChooserFrame();
        }

    } // actionPerformed Method

} // WelcomeFrame Class