package com.bingo.gui.components;

import com.bingo.core.*;
import com.bingo.gui.BingoKeyboardFocusMananger;
import com.bingo.gui.listeners.InitialState;
import com.bingo.gui.listeners.PlaySoundOnBallRaffled;
import com.bingo.gui.listeners.RestartBingoOnFinished;
import com.bingo.gui.listeners.UpdateRaffledBall;

import javax.swing.*;
import java.awt.*;

/**
 * Created by damianijr on 1/7/17.
 */
public class BingoFrame extends JFrame {


    private Bingo bingo;
    private final BingoContent content;

    public BingoFrame(final Bingo bingo) {
        this.bingo = bingo;

        setTitle("Family Bingo!");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // NORTH
        getContentPane().add(new BingoLogo(), "North");

        // CENTER
        content = new BingoContent(bingo.getBalls());
        getContentPane().add(content);
        ((JComponent) getContentPane()).setComponentPopupMenu(new BingoContextMenu(this));

        // keyboard mananger
        initKeyboardMananger();

        // Start Bingo!
        this.bingo.registerListener(new InitialState(content));
        this.bingo.registerListener(new PlaySoundOnBallRaffled());
        this.bingo.registerListener(new UpdateRaffledBall(content));
        this.bingo.registerListener(new RestartBingoOnFinished(this));
        bingo.start();

    }

    public void restart() {
        this.bingo.restart();
    }

    private void initKeyboardMananger() {
        KeyboardFocusManager currentKeyboardFocusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        currentKeyboardFocusManager.addKeyEventDispatcher(new BingoKeyboardFocusMananger(this, bingo));
    }

}

