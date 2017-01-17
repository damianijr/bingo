package com.bingo.gui.components;

import com.bingo.core.BingoBall;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by damianijr on 1/7/17.
 */
public class BingoContent extends JPanel {

    private final BingoHighlightedBall bingoBigNumber;
    private final BingoTable bingoTable;

    public BingoContent(final List<BingoBall> bingoNumbers) {

        // GRID - One line, Two Columns
        setLayout(new GridLayout(1, 2));

        // Collumn One: Last Number
        bingoBigNumber = new BingoHighlightedBall();
        add(bingoBigNumber);

        // Collumn Two: Table of raffled numbers
        bingoTable = new BingoTable(bingoNumbers);
        JScrollPane scrollpane = new JScrollPane(bingoTable);
        scrollpane.setBorder(BorderFactory.createEmptyBorder());
        scrollpane.getViewport().setBackground(bingoBigNumber.getBackground());
        add(scrollpane);
    }

    public void raffledNumber(final Integer number, final List<Integer> noRaffledNumbers) {
        this.bingoBigNumber.setNumber(number, noRaffledNumbers);
        this.bingoTable.repaint();
        this.bingoTable.revalidate();
    }
}
