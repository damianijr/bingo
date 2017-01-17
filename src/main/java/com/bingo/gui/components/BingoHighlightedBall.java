package com.bingo.gui.components;

import com.bingo.BingoConstants;
import com.bingo.core.TimerActionExecutor;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

/**
 * Created by damianijr on 1/7/17.
 */
public class BingoHighlightedBall extends JLabel {

    public BingoHighlightedBall() {
        setVerticalAlignment(0);
        setHorizontalAlignment(0);
        setForeground(Color.BLACK);
        setFont(new Font(BingoConstants.FONT_NAME, 1, 500));
    }

    public void setNumber(final Integer number, final List<Integer> ballsNoRaffled) {
        if (number == null) {
            setText("-");
            return;
        }

        TimerActionExecutor.execute(thread -> {
            if (ballsNoRaffled.size() <= BingoConstants.BALLS_THRILLING) {
                animateLastNumbers(number, ballsNoRaffled);
            }
            else {
                setText(number != null ? String.valueOf(number) : "-");
                Thread.sleep(1000);
            }
        });


    }

    @Override
    public String getText() {
        final String text = super.getText();
        return text == null || text.isEmpty() ? "-" : text;
    }

    private void animateLastNumbers(Integer numberRuffled, final List<Integer> ballsNoRaffled) throws InterruptedException {
        if (numberRuffled == null) {
            setText("");
            return;
        }

        long timeStart = System.currentTimeMillis();
        while (timeStart > System.currentTimeMillis() - 9000L)
        {
            BingoHighlightedBall.this.setText((ballsNoRaffled.get(new Random().nextInt(ballsNoRaffled.size()))).toString());
            BingoHighlightedBall.this.repaint();
            BingoHighlightedBall.this.revalidate();

            Thread.sleep(150L);
        }

        setText(String.valueOf(numberRuffled));

        timeStart = System.currentTimeMillis();
        while (timeStart > System.currentTimeMillis() - 2000L)
        {
            BingoHighlightedBall.this.setForeground(Color.ORANGE);
            BingoHighlightedBall.this.setForeground(Color.GRAY);
        }
    }
}
