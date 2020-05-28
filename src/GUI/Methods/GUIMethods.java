package src.GUI.Methods;

/*
* Date: May 21, 2020
* Authors: Shahrukh Qureshi
* Description: This class contains methods that are used in all of the other GUI classes
*
* Method List: 
* 1. void showMsg(final String msg) = This method displays a message in a dialog box
* 2. static ArrayList<String> readFile(final ArrayList<String> data, final File file) = This method reads from a textfile and returns an arraylist of strings 
* 3. static JFrame frameSetter(final JFrame frame, final int x, final int y) = This method alters the JFrames and returns it
* 4. static JButton btnSetter(final JButton btn) = This method alters the JButtons and returns it
* 5. static JMenuBar barSetter(final JMenuBar menuBar, final JMenu menu, final JMenuItem[] items) = This method creates a menubar by adding its items to the menu and returns it
*/

// Import Statements
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.awt.Cursor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class GUIMethods {

    // Global Variables 
    final private static Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);

    // This method displays a message in a dialog box
    public static void showMsg(final String msg) {

        JOptionPane.showMessageDialog(null, msg);

    } // errorMsg Method

    // This method reads from a textfile and returns an array of strings
    public static ArrayList<String> readFile(final ArrayList<String> data, final File file) {

        try {
            final FileReader fileR = new FileReader(file);
            final BufferedReader reader = new BufferedReader(fileR);

            String line = "";
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
            fileR.close();
            reader.close();

            return data;

        } catch (final Exception error) {
            showMsg(error.getMessage());
            System.exit(error.hashCode());
        }

        return null;

    } // readFile Method

    // This method alters the JFrames and returns it
    public static JFrame frameSetter(final JFrame frame, final int x, final int y) {

        final String title = "File Converter";

        frame.getContentPane().setBackground(new Color(50, 197, 216)); // Changing the color of the Frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Setting its close operation
        frame.setSize(x, y); // Setting window size
        frame.setLayout(null); // Setting layout to null so we can decide the JComponents location
        frame.setLocationRelativeTo(null); // Setting the windows location to the center of the display
        frame.setResizable(false); // Making it so the user cannot resize the JFrame windows
        frame.setTitle(title);

        return frame; // Returning the frame

    } // frameSetter Method

    // This method alters the JButtons and returns it
    public static JButton btnSetter(final JButton btn) {

        final Font btnFont = new Font("Arial", Font.BOLD, 20);
        btn.setCursor(handCursor);
        btn.setFont(btnFont);
        btn.setBackground(Color.GRAY);

        return btn; // Returning the Button

    } // btnSetter Method

    // This method creates a menubar by adding its items to the menu and returns it
    public static JMenuBar barSetter(final JMenuBar menuBar, final JMenu menu, final JMenuItem[] items) {

        // Loop to iterate through the array of items
        for (final JMenuItem mItem : items) {
            mItem.setCursor(handCursor); // Adding the cursor
            menu.add(mItem); // Adding the items to the menu
        }

        menuBar.add(menu); // Adding the menu to the menubar

        return menuBar; // Returning the menubar

    } // barSetter Method

}