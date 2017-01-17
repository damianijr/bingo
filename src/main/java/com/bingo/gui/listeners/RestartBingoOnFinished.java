package com.bingo.gui.listeners;

import com.bingo.core.listener.BingoListener;
import com.bingo.core.listener.BingoListenerAdapter;
import com.bingo.core.listener.events.EndEvent;
import com.bingo.gui.components.BingoFrame;

import javax.swing.*;

/**
 * Created by damianijr on 1/11/17.
 */
public class RestartBingoOnFinished extends BingoListenerAdapter {

    private BingoFrame bingoFrame;

    public RestartBingoOnFinished(BingoFrame bingoFrame) {
        this.bingoFrame = bingoFrame;
    }

    @Override
    public void onEnd(EndEvent event) {
        int response = JOptionPane.showConfirmDialog(null, "Bingo finished! I wish that nobody eats bronha!! Can restart?");
        if (response == JOptionPane.YES_OPTION) {
            bingoFrame.restart();
        }
    }
}
