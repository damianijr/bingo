package com.bingo.core;

import com.bingo.BingoConstants;
import com.bingo.core.exceptions.InvalidNumberOfBallsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Created by damianijr on 1/7/17.
 */
public class BingoTest {

    @Test
    @DisplayName("Number of balls can`t be null")
    public void testBallsNumberNull() {
        Assertions.assertThrows(InvalidNumberOfBallsException.class, () -> new Bingo(null));
    }

    @Test
    @DisplayName("Number of balls can`t be zero")
    public void testBallsNumberZero() {
        Assertions.assertThrows(InvalidNumberOfBallsException.class, () -> new Bingo(0));
    }

    @Test
    @DisplayName("Number of balls can`t be less then zero")
    public void testBallsNumberLessZero() {
        Assertions.assertThrows(InvalidNumberOfBallsException.class, () -> new Bingo(-1));
    }

    @Test
    @DisplayName("Numbers of ball exceeding limit")
    public void testBallsNumberLimitExceeded() {
        Assertions.assertThrows(InvalidNumberOfBallsException.class, () -> new Bingo(BingoConstants.MAX_CARD_NUMBERS + 1));
    }

    @Test
    @DisplayName("No balls raffleds")
    public void testNoBallsRaffleds() {
        Assertions.assertTrue(new Bingo(10).getRaffledBalls().isEmpty());
    }

    @Test
    @DisplayName("Raffling ball")
    public void testRaffledBall() {
        // arrange
        final Bingo bingo = new Bingo(90).start();

        // act
        BingoBall numberRaffled = bingo.raffle();

        // assert
        Assertions.assertEquals(numberRaffled, bingo.getRaffledBalls().iterator().next());
    }

    @Test
    @DisplayName("Raffling all balls and try raffle more one")
    public void testForceRaffled() {
        // arrange
        int ballsNumber = 5;
        final Bingo bingo = new Bingo(ballsNumber).start();

        // act
        while (ballsNumber > 0) {
            bingo.raffle();
            ballsNumber--;
        }

        // assert
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> bingo.raffle());
    }

    @Test
    @DisplayName("Restarting game")
    public void testRestartGame() {
        // arrange
        final Bingo bingo = new Bingo(10).start();

        // act
        bingo.raffle();
        bingo.restart();

        // assert
        Assertions.assertTrue(bingo.getRaffledBalls().isEmpty());
    }


}
