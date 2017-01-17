package com.bingo.gui.components;

import com.bingo.gui.components.actions.RestartBingoActionListener;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Bingo context menu.
 * Created by damianijr on 1/13/17.
 */
public class BingoContextMenu extends JPopupMenu {

    private BingoFrame bingoFrame;

    public BingoContextMenu(BingoFrame bingoFrame) {

        this.bingoFrame = bingoFrame;

        add(nextBallItem());
        add(resetItem());
        add(exitItem());

        setBorder(new BevelBorder(BevelBorder.RAISED));

    }

    private JMenuItem nextBallItem() {
        final JMenuItem item = new JMenuItem("Next");
        item.addActionListener(action -> {
            Robot robot = null;
            try {
                robot = new Robot();
                robot.keyPress(KeyEvent.VK_ENTER);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        });
        return item;
    }

    private JMenuItem resetItem() {
        final JMenuItem item = new JMenuItem("Reset");
        item.addActionListener(new RestartBingoActionListener(bingoFrame));
        return item;
    }

    private JMenuItem exitItem() {
        final JMenuItem item = new JMenuItem("Exit");
        item.addActionListener(action -> {
            if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Confirm exit?")) {
                System.exit(0);
            }
        });
        return item;
    }
}
