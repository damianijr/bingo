package com.bingo.core.listener.events;

import com.bingo.core.BingoBall;

import java.util.List;

/**
 * Created by damianijr on 1/11/17.
 */
public class StartEvent {

    private List<BingoBall> numbers;

    public StartEvent(final List<BingoBall> numbers) {
        this.numbers = numbers;
    }

    public List<BingoBall> getNumbers() {
        return numbers;
    }
}
