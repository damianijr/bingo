package com.bingo.core.listener.events;

import com.bingo.core.BingoBall;

import java.util.List;

/**
 * Created by damianijr on 1/11/17.
 */
public class RaffledEvent {

    private BingoBall raffledBall;
    private List<BingoBall> noRaffledNumbers;

    public RaffledEvent(final BingoBall raffledBall, final List<BingoBall> noRaffledNumbers) {
        this.raffledBall = raffledBall;
        this.noRaffledNumbers = noRaffledNumbers;
    }

    public BingoBall getRaffledBall() {
        return raffledBall;
    }

    public List<BingoBall> getNoRaffledNumbers() {
        return noRaffledNumbers;
    }
}
