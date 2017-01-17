package com.bingo.gui.listeners;

import com.bingo.core.listener.BingoListener;
import com.bingo.core.listener.BingoListenerAdapter;
import com.bingo.core.listener.events.RaffledEvent;
import com.bingo.gui.components.BingoContent;

import java.util.stream.Collectors;

/**
 * Created by damianijr on 1/11/17.
 */
public class UpdateRaffledBall extends BingoListenerAdapter {

    private BingoContent bingoContent;

    public UpdateRaffledBall(final BingoContent bingoContent) {
        this.bingoContent = bingoContent;
    }

    @Override
    public void onRaffled(RaffledEvent event) {
        bingoContent.raffledNumber(
                event.getRaffledBall().getNumber(),
                event.getNoRaffledNumbers().stream().mapToInt(ball -> ball.getNumber()).boxed().collect(Collectors.toList())
        );
    }
}
