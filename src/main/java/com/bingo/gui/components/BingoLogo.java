package com.bingo.gui.components;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

/**
 * Created by damianijr on 1/7/17.
 */
public class BingoLogo extends JLabel {

    private static final String RESOURCE_LOGO = "/bingo.png";

    public BingoLogo() {

        // Load image
        Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource(RESOURCE_LOGO));
        Icon icon = new ImageIcon(image);

        // Configure component
        setIcon(icon);
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.TOP);

    }
}
