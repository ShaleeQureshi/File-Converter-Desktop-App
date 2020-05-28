package src.GUI.MenuFrames;

/*
* Date: May 21, 2020
* Authors: Shahrukh Qureshi
* Description: This class creates the Welcome Frame (First frame)
*
* Method List:
* 1. void browse(String uri) = This method browses the internet to a specified URI
* 2. void valueChanged(ListSelectionEvent e) = This method will change the displayed information when a change occurs on the JList (ScrollPane)
* 3. void actionPerformed(final ActionEvent e) = Handles all events on JComponents
*/

// Import Statements
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.net.URI;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Desktop;
import src.GUI.Methods.GUIMethods;

public class SettingsFrame implements ListSelectionListener, ActionListener {

    private JFrame frameSettings;
    private final JSplitPane splitPane;
    private final JScrollPane scrollPaneLeft;
    private final String[] listItems = { "About", "Help" };
    private final JPanel[] panels;
    private final JLabel lblPanel0, lblPanel1;
    private JButton btnGitHubPanel0, btnHowVidPanel1;
    private final Font lblFont = new Font("Arial", Font.PLAIN, 10);
    private final JList<String> list;

    // Void Constructor
    public SettingsFrame() {

        // JFrame
        frameSettings = new JFrame();
        frameSettings.getContentPane().setBackground(new Color(50, 197, 216)); // Changing the color of the Frame
        frameSettings.setSize(400, 200); // Setting window size
        frameSettings.setLayout(null); // Setting layout to null so we can decide the JComponents location
        frameSettings.setLocationRelativeTo(null); // Setting the windows location to the center of the display
        frameSettings.setResizable(false); // Making it so the user cannot resize the JFrame windows
        frameSettings.setTitle("Settings");

        // JSplitPane, ScrollPane, JPanels
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        list = new JList<String>(listItems);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        scrollPaneLeft = new JScrollPane(list);

        // Array of JPanels
        panels = new JPanel[2];
        panels[0] = new JPanel();
        panels[1] = new JPanel();

        // panels0 components
        // JLabels
        lblPanel0 = new JLabel("<html><u>Developed by Shahrukh (Shalee) Qureshi");
        lblPanel0.setFont(lblFont);
        // JButtons
        btnGitHubPanel0 = new JButton("GitHub");
        btnGitHubPanel0 = GUIMethods.btnSetter(btnGitHubPanel0);
        btnGitHubPanel0.addActionListener(this);

        // Location set with (x, y, width, height)
        panels[0].setLayout(null);
        lblPanel0.setBounds(10, 0, 200, 30);
        panels[0].add(lblPanel0);
        btnGitHubPanel0.setBounds(37, 60, 150, 50);
        panels[0].add(btnGitHubPanel0);

        // panels[1] components
        // JLabels
        lblPanel1 = new JLabel("<html><u>How to use File Converter");
        lblPanel1.setFont(lblFont);
        // JButtons
        btnHowVidPanel1 = new JButton("How to Convert");
        btnHowVidPanel1 = GUIMethods.btnSetter(btnHowVidPanel1);

        // Location set with (x, y, width, height)
        panels[1].setLayout(null);
        lblPanel1.setBounds(50, 0, 200, 30);
        panels[1].add(lblPanel1);
        btnHowVidPanel1.setBounds(15, 60, 200, 50);
        btnHowVidPanel1.addActionListener(this);
        panels[1].add(btnHowVidPanel1);

        // Adjusting the splitPane
        splitPane.setLeftComponent(scrollPaneLeft);
        splitPane.setRightComponent(panels[0]);
        splitPane.setDividerLocation(150);
        splitPane.setDividerSize(10);
        splitPane.setSize(400, 200);
        frameSettings.add(splitPane); // Adding it to the JFrame

        frameSettings.setVisible(true);

    } // Constructor

    // This method browses the internet to a specified URI
    void browse(final String uri) {
        try {
            final Desktop openDesktop = Desktop.getDesktop();
            openDesktop.browse(new URI(uri));
        } catch (final Exception error) {
            GUIMethods.showMsg("An unexpected error has occured");
        }
    } // browse Method

    @Override
    public void valueChanged(final ListSelectionEvent e) {

        final int index = list.getSelectedIndex();
        list.setSelectedIndex(index);
        splitPane.setDividerLocation(150);
        splitPane.setDividerSize(10);
        splitPane.setRightComponent(panels[index]);

    } // valueChanged Method

    @Override
    public void actionPerformed(final ActionEvent e) {

        // If the user presses the GitHub button on panel0 (About page) the following
        // will occur
        if (e.getSource() == btnGitHubPanel0) {
            browse("https://github.com/ShaleeQureshi");
        }
        // If the user presses the How to Convert button on panel1 (Help page) the
        // following will occur
        else if (e.getSource() == btnHowVidPanel1) {
            browse("https://www.youtube.com/watch?v=0LxDqWcNLIw");
        }

    } // actionPerformed Method


} // SettinsFrame Class