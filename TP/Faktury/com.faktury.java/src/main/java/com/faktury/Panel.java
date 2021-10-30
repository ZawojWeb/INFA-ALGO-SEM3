package com.faktury;

// import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Panel {

    // JFrame
    static JFrame f;

    // JButton
    static JButton b, b1, b2;

    // label to display text
    static JLabel l;

    public static void panel() {
        // ! GUI
        // create a new frame to store text field and button
        f = new JFrame("panel");

        // create a label to display text
        l = new JLabel("panel label");
        JPanel p = new JPanel();
        // setbackground of panel
        p.setBackground(Color.red);

        // add panel to frame
        f.add(p);

        // set the size of frame
        f.setSize(300, 300);

        f.show();
        // ! Switch case with funcionality
    }
}
