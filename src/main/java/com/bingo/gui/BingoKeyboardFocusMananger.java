package com.bingo.gui;

import com.bingo.core.Bingo;
import com.bingo.gui.components.BingoFrame;
import com.bingo.core.TimerActionExecutor;
import com.bingo.gui.components.actions.RestartBingoActionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by damianijr on 1/11/17.
 */
public class BingoKeyboardFocusMananger implements KeyEventDispatcher {

    private BingoFrame bingoFrame;
    private Bingo bingo;

    public BingoKeyboardFocusMananger(final BingoFrame bingoFrame, final Bingo bingo) {
        this.bingoFrame = bingoFrame;
        this.bingo = bingo;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getID() != KeyEvent.KEY_PRESSED) {
            return true;
        }

        // Enter
        if (e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            if (!TimerActionExecutor.isExecuting()) {
                this.bingo.raffle();
            }
        }

        // Esc
        if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
            if (!TimerActionExecutor.isExecuting()) {
                new RestartBingoActionListener(bingoFrame).actionPerformed(null);
            }
        }

        return true;
    }

}
