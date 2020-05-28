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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import src.GUI.Methods.GUIMethods;

public class ConversionFailure implements ActionListener {

    JFrame frameFailure;
    JLabel lblFailure;
    JButton btnExit;

    // Void Constructor
    public ConversionFailure() {

        // JFrames
        frameFailure = new JFrame();
        frameFailure = GUIMethods.frameSetter(frameFailure, 200, 150);

        // JLabels
        lblFailure = new JLabel("An error has occured");

        // JButtons
        btnExit = new JButton("Exit");
        btnExit = GUIMethods.btnSetter(btnExit);
        btnExit.addActionListener(this);

        // Location set with (x, y, width, height)

        // JLabels
        lblFailure.setBounds(30, 20, 150, 30);
        frameFailure.add(lblFailure);

        // JButtons
        btnExit.setBounds(-10, 70, 200, 50);
        frameFailure.add(btnExit);

        frameFailure.setVisible(true);

    } // Constructor

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnExit) {
            frameFailure.dispose();
            GUIMethods.showMsg("Thank you for using File Converter!");
            System.exit(0);
        }

    } // actionPerformed Method

} // class ConversionFailure