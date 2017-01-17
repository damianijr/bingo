package com.bingo.core.listener;

import com.bingo.core.listener.events.EndEvent;
import com.bingo.core.listener.events.RaffledEvent;
import com.bingo.core.listener.events.StartEvent;

/**
 * Created by damianijr on 1/11/17.
 */
public interface BingoListener {


    /**
     * Method executed on bingo start.
     */
    public void onStart(final StartEvent event);

    /**
     * Method executed on bingo ends (all bals raffled or exit by user)
     */
    public void onEnd(final EndEvent event);

    /**
     * Method action on each ball raffled.
     */
    public void onRaffled(final RaffledEvent event);
}
