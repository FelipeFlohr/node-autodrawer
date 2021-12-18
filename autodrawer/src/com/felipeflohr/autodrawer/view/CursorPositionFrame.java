package com.felipeflohr.autodrawer.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.MouseInfo;

public class CursorPositionFrame extends JFrame {

    public CursorPositionFrame() {
        JLabel positionLabel = new JLabel();
        setLayout(new GridLayout());

        new Thread(() -> {
            while (true) {
                positionLabel.setText("XY Position -> x: " + (int) MouseInfo.getPointerInfo().getLocation().getX() + ", y: "
                        + (int) MouseInfo.getPointerInfo().getLocation().getY());
            }
        }).start();

        positionLabel.setVerticalAlignment(SwingConstants.CENTER);
        positionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(positionLabel);

        setSize(210, 100);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
