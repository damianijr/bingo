package com.bingo.gui.listeners;

import com.bingo.core.listener.BingoListener;
import com.bingo.core.listener.BingoListenerAdapter;
import com.bingo.core.listener.events.StartEvent;
import com.bingo.gui.components.BingoContent;

import java.util.stream.Collectors;

/**
 * Created by damianijr on 1/13/17.
 */
public class InitialState extends BingoListenerAdapter {

    private BingoContent content;

    public InitialState(BingoContent content) {
        this.content = content;
    }

    @Override
    public void onStart(StartEvent event) {
        this.content.raffledNumber(null, event.getNumbers()
                .stream()
                .mapToInt(b -> b.getNumber())
                .boxed()
                .collect(Collectors.toList()));
    }
}
