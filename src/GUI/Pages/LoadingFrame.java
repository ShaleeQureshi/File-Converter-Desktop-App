package src.GUI.Pages;

/*
* Date: May 21, 2020
* Authors: Shahrukh Qureshi
* Description: This class creates loading screen which is displayed to the user while their request is being processed
*/

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import src.GUI.Methods.GUIMethods;

public class LoadingFrame {

    public static JFrame frameLoading;
    private final JLabel lblBuffer;
    private final JLabel lblProcess;

    // Void Constructor
    public LoadingFrame() {

        // JFrame
        frameLoading = new JFrame();
        frameLoading = GUIMethods.frameSetter(frameLoading, 300, 300);

        // JLabels
        lblBuffer = new JLabel(new ImageIcon("./Assets/Visuals/loading.gif"));
        lblProcess = new JLabel("<html><u>Processing your request");

        // Location set with (x, y, width, height)

        // JLabels
        lblBuffer.setBounds(20, 20, 250, 250);
        frameLoading.add(lblBuffer);
        lblProcess.setBounds(75, 20, 150, 30);
        frameLoading.add(lblProcess);

        frameLoading.setVisible(true);

    } // Constructor

} // LoadingFrame Class
