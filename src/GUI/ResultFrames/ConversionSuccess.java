package src.GUI.ResultFrames;

/*
* Date: May 21, 2020
* Authors: Shahrukh Qureshi
* Description: This class creates the Frame that will allow the user to import a file to convert (Third Page)
*
* Method List:
* 1. void actionPerformed(final ActionEvent e) = Handles all events on JComponents
*/

// Import Statements
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import src.GUI.Methods.GUIMethods;
import src.GUI.Pages.ChooserFrame;

public class ConversionSuccess implements ActionListener{

    JFrame frameSuccess;
    JLabel lblSuccess, lblFileName;
    JButton btnExit, btnConvertAgain;

    // Void Constructor
    public ConversionSuccess() {

        // JFrames
        frameSuccess = new JFrame();
        GUIMethods.frameSetter(frameSuccess, 300, 200);

        // JLabels
        lblSuccess = new JLabel("Converted Successfully!");
        lblFileName = new JLabel("File can be found in the same directory as the\n original");
        lblFileName.setFont(new Font("Arial", Font.PLAIN, 10));    

        // JButtons
        btnConvertAgain = new JButton("Again");
        btnConvertAgain = GUIMethods.btnSetter(btnConvertAgain);
        btnConvertAgain.addActionListener(this);
        btnExit = new JButton("Exit");
        btnExit = GUIMethods.btnSetter(btnExit);
        btnExit.addActionListener(this);

        // Location set with (x, y, width, height)

        // JLabels  
        lblSuccess.setBounds(75, 20, 150, 30);
        frameSuccess.add(lblSuccess);
        lblFileName.setBounds(20, 50, 250, 30);
        frameSuccess.add(lblFileName);

        // JButtons
        btnConvertAgain.setBounds(160, 110, 140, 60);
        frameSuccess.add(btnConvertAgain);
        btnExit.setBounds(0, 110, 140, 60);
        frameSuccess.add(btnExit);

        frameSuccess.setVisible(true);

    } // Constructor

    @Override
    public void actionPerformed(ActionEvent e) {
       
        if (e.getSource() == btnExit) {
            frameSuccess.dispose();
            GUIMethods.showMsg("Thank you for using File Converter!");
            System.exit(0);
        }
        else {
            frameSuccess.dispose();
            new ChooserFrame();
        }

    } // actionPerformed Method


} // class ConversionSuccess