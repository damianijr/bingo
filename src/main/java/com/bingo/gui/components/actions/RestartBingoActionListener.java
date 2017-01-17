package com.bingo.gui.components.actions;

import com.bingo.core.TimerActionExecutor;
import com.bingo.gui.components.BingoFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by damianijr on 1/16/17.
 */
public class RestartBingoActionListener implements ActionListener {

    private BingoFrame bingoFrame;

    public RestartBingoActionListener(final BingoFrame bingoFrame) {
        this.bingoFrame = bingoFrame;
    }

    @Override
    public void actionPerformed(ActionEvent action) {
        TimerActionExecutor.execute(thread -> {
            if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Confirm reset bingo?")) {
                bingoFrame.restart();
            }
        });
    }

}
