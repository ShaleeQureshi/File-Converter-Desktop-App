package src.GUI.Pages;

/*
* Date: May 21, 2020
* Authors: Shahrukh Qureshi
* Description: This class creates the Frame that will allow the user to import a file to convert (Third Page)
*
* Method List:
* 1. void actionPerformed(final ActionEvent e) = Handles all events on JComponents
*/

// Import Statements
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import src.GUI.MenuFrames.SettingsFrame;
import src.GUI.Methods.GUIMethods;
import src.ConversionCode.Conversions;

public class ConverterFrame implements ActionListener {

    private JFrame frameConvert;
    private final JFileChooser fileChooser;
    private final JLabel lblChoose, lblSelectedItem;
    private JButton btnConvert, btnSelectFile;
    private JMenuBar menuBar;
    private final JMenu menu;
    private final JMenuItem[] items;

    public final String[] type;
    public final String selectedItem;

    // Void Constructor
    public ConverterFrame() {

        // JFrame
        frameConvert = new JFrame();
        frameConvert = GUIMethods.frameSetter(frameConvert, 300, 300);

        // JButton
        btnConvert = new JButton("Convert");
        btnConvert = GUIMethods.btnSetter(btnConvert);
        btnSelectFile = new JButton("Select File");
        btnSelectFile = GUIMethods.btnSetter(btnSelectFile);

        // JMenuBar/JMenu/JMenuItem
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        items = new JMenuItem[4];
        items[0] = new JMenuItem("Settings");
        items[0].addActionListener(this);
        items[1] = new JMenuItem("Back");
        items[1].addActionListener(this);
        items[2] = new JMenuItem("Remove Selected Item");
        items[2].addActionListener(this);
        items[3] = new JMenuItem("Exit");
        items[3].addActionListener(this);
        menuBar = GUIMethods.barSetter(menuBar, menu, items);
        frameConvert.setJMenuBar(menuBar);

        // JLabel
        selectedItem = ChooserFrame.getSelectedItem(); // Getting the item the user chose
        type = selectedItem.split(" ", 3); // Spliting it by the spaces
        lblChoose = new JLabel("<html><u>Please select a " + type[0] + " file to convert it to " + type[2]);
        lblSelectedItem = new JLabel();

        // JFileChooser
        fileChooser = new JFileChooser();

        // Location set with (x, y, width, height)

        // JLabels
        lblChoose.setBounds(30, 20, 300, 30);
        frameConvert.add(lblChoose);
        lblSelectedItem.setBounds(45, 110, 200, 30);
        frameConvert.add(lblSelectedItem);

        // JButtons
        btnSelectFile.setBounds(45, 50, 200, 50);
        btnSelectFile.addActionListener(this);
        frameConvert.add(btnSelectFile);
        btnConvert.setBounds(45, 150, 200, 50);
        btnConvert.addActionListener(this);
        frameConvert.add(btnConvert);

        frameConvert.setVisible(true);

    } // Constructor

    @Override
    public void actionPerformed(final ActionEvent e) {

        // If the user goes to File -> Settings the following will occur
        if (e.getSource() == items[0]) {
            new SettingsFrame();
        }
        // If the user goes to File -> Back the following will occur
        else if (e.getSource() == items[1]) {

            frameConvert.dispose();
            new ChooserFrame();

        }
        // If the user goes to File -> Remove Selected Item the following will occur
        else if (e.getSource() == items[2]) {

            // Checking to see if the user selected an item

            if (lblSelectedItem.getText() == null) {
                GUIMethods.showMsg("You have not selected an item to remove!");
            } else {

                final Object[] options = { "Yes", "No" }; // Options for the JOptionDialog

                // Prompting user
                final int choice = JOptionPane.showOptionDialog(null,
                        "Are you sure you want to remove " + fileChooser.getSelectedFile().getName(), "File Converter",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);

                // If the user presses Yes the following will occur
                if (choice == JOptionPane.YES_OPTION) {
                    GUIMethods.showMsg("The item has successfully been removed!"); // Letting the user know it has been
                                                                                   // deleted
                    lblSelectedItem.setText(""); // Clearing the label
                    fileChooser.setSelectedFile(null); // Making the fileChooser = null
                }
            }

        }
        // If the user goes to File -> Exit the following will occur
        else if (e.getSource() == items[3]) {

            frameConvert.dispose();
            GUIMethods.showMsg("Thank you for using File Converter!");
            System.exit(0);

        }
        // If the user wants to import a file the following will occur
        else if (e.getSource() == btnSelectFile) {

            final int value = fileChooser.showOpenDialog(null);

            if (value == JFileChooser.APPROVE_OPTION) {
                if (fileChooser.getSelectedFile().getName().contains(("." + type[0].toLowerCase()))) {
                    JOptionPane.showMessageDialog(null, fileChooser.getSelectedFile().getName() + " has been selected");
                    lblSelectedItem.setText("Selected: " + fileChooser.getSelectedFile().getName());
                } else {
                    GUIMethods.showMsg("Invalid item selected!\nPlease select a " + type[0] + " file");
                    fileChooser.setSelectedFile(null);
                }
            } else {
                GUIMethods.showMsg("File not selected!");
            }
        }
        // If the user wants to convert the file the following will occur
        else if (e.getSource() == btnConvert && lblSelectedItem.getText().length() != 0) {

            frameConvert.dispose();
            // type[0] represents the type of file we are converting from
            Conversions.conversion(type[0], fileChooser.getSelectedFile().toString(),
                    fileChooser.getCurrentDirectory().getAbsolutePath());

        }

    } // actionPerformed Method

} // ConverterFrame Class