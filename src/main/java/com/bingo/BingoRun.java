package com.bingo;

import com.bingo.core.Bingo;
import com.bingo.gui.components.BingoFrame;

import javax.swing.*;

/**
 * Created by damianijr on 1/7/17.
 */
public class BingoRun {

    public static void main(String[] args) {
        Bingo bingo = new Bingo(75);
        BingoFrame frame = new BingoFrame(bingo);
        frame.setVisible(true);
    }
}
