package com.bingo.core;

import com.bingo.BingoConstants;
import com.bingo.core.exceptions.InvalidNumberOfBallsException;
import com.bingo.core.listener.BingoListener;
import com.bingo.core.listener.events.EndEvent;
import com.bingo.core.listener.events.RaffledEvent;
import com.bingo.core.listener.events.StartEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a bingo.
 *
 * Created by damianijr on 1/7/17.
 */
public class Bingo {

    private final List<BingoBall> numbers;
    private final List<BingoListener> listeners = new ArrayList<>();
    private Long startTime;


    /**
     * Bingo constructor.
     * @param ballAmount Number of balls in cards.
     * @throws InvalidNumberOfBallsException Balls amout is invalid.
     */
    public Bingo(final Integer ballAmount) {
        validateBallAmount(ballAmount);
        numbers = Collections.unmodifiableList(createNumbers(ballAmount));
    }

    /**
     * Start bingo.
     * @return Bingo.
     */
    public Bingo start() {
        this.startTime = System.currentTimeMillis();
        listeners.forEach(listener -> listener.onStart(new StartEvent(getBalls())));
        return this;
    }

    /**
     * Restart bingo.
     * @return Bingo.
     */
    public Bingo restart() {
        getRaffledBalls().forEach(ball -> ball.unraffled());
        start();
        return this;
    }

    /**
     * Raffle a ball.
     * @return New ball.
     * @throws IndexOutOfBoundsException If all balls are raffled.
     */
    public BingoBall raffle() {
        if (startTime == null) {
            throw new IllegalStateException("Bingo dont started.");
        }

        if (getNoRaffledNumbers().isEmpty()) {
            throw new IndexOutOfBoundsException("All balls are raffled. Restart bingo.");
        }

        // Random number
        final List<BingoBall> numbersCopy = new ArrayList<>(getNoRaffledNumbers());
        Collections.shuffle(numbersCopy);
        final BingoBall bingoNumber = numbersCopy.stream().findFirst().get();

        // Mark number as raffled
        bingoNumber.raffled();

        // Raffled listener
        listeners.forEach(listener -> listener.onRaffled(new RaffledEvent(bingoNumber, getNoRaffledNumbers())));

        if (getNoRaffledNumbers().isEmpty()) {
            // TODO: Empty envet? remove?
            listeners.forEach(listener -> listener.onEnd(new EndEvent()));
        }
         return bingoNumber;
    }

    /**
     * Returns all balls in bingo.
     * @return Balls in bingo.
     */
    public List<BingoBall> getBalls() {
        return numbers;
    }

    /**
     * Returns balls raffleds.
     * @return Balls raffleds.
     */
    public List<BingoBall> getRaffledBalls() {
        return this.numbers.stream().filter(n -> n.isRaffled()).collect(Collectors.toList());
    }

    /**
     * Returns balls not raffled.
     * @return Balls not raffled.
     */
    public List<BingoBall> getNoRaffledNumbers() {
        return this.numbers.stream().filter(n -> !n.isRaffled()).collect(Collectors.toList());
    }

    /**
     * Register listener in bingo.
     * @param listener Listener to register.
     */
    public void registerListener(final BingoListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Listener param cant be null");
        }
        this.listeners.add(listener);
    }

    /**
     * Remove listener from bingo.
     * @param listener Listener to remove.
     */
    public void removeListener(final BingoListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Listener param cant be null");
        }
        this.listeners.remove(listener);
    }

    /**
     * Create balls using in bingo.
     * @param ballsAmount
     * @return List of balls.
     */
    private List<? extends BingoBall> createNumbers(final Integer ballsAmount) {
        List<BingoBall> numbers = new ArrayList<BingoBall>();
        for (int i = 1; i <= ballsAmount; i++) {
            numbers.add(new BingoBall(i, Boolean.FALSE));
        }
        return numbers;
    }

    /**
     * Validate ball amount.
     * @param ballAmount Ball`s amount.
     */
    private void validateBallAmount(Integer ballAmount) {
        if (ballAmount == null) {
            throw new InvalidNumberOfBallsException("Amount cant be null.");
        }
        else if (new Integer("0").compareTo(ballAmount) >= 0) {
            throw new InvalidNumberOfBallsException("Amount cant be zero or less.");
        }
        else if (ballAmount % 5 != 0) {
            throw new InvalidNumberOfBallsException("Amount needs to be mutiply 5.");
        }
        else if (BingoConstants.MAX_CARD_NUMBERS.compareTo(ballAmount) <= 0) {
            throw new InvalidNumberOfBallsException("Balls amount exceeded the limit.");
        }
    }

}
