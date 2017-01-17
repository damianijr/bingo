package com.bingo.gui.listeners;

import com.bingo.BingoConstants;
import com.bingo.core.listener.BingoListenerAdapter;
import com.bingo.core.listener.events.RaffledEvent;
import javazoom.jl.player.Player;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Play sounds for ball raffled.
 *
 * Insert new sounds in resources path ({@link #SOUNDS_PATH}/ballNumber), example:
 * /sounds/balls/20/sound1.mp3
 * /sounds/balls/20/sound2.mp3
 *
 * When ball number 20 will be raffled, a random sound in folder will be played.
 *
 * Created by damianijr on 1/14/17.
 */
public class PlaySoundOnBallRaffled extends BingoListenerAdapter {

    private static final String FOLDER_THRILLING = "thrilling";
    private static String SOUNDS_PATH = "/sounds/balls/";

    @Override
    public void onRaffled(RaffledEvent event) {
        // Play on thread for prevent gui freezing
        final String folder = retrieveFolder(event);
        new Thread(() -> play(SOUNDS_PATH + folder)).start();

    }

    /**
     * Play sound in folder (random if exist more then one sound).
     * @param path Path to play sounds.
     */
    private void play(final String path) {
        try {
            final URL resource = getClass().getResource(path);
            if (resource == null) {
                return;
            }
            final File[] sounds = new File(resource.getFile()).listFiles();
            if (sounds == null || sounds.length <= 0) {
                return;
            }
            final File sound = sounds.length > 1 ? sounds[new Random(0).nextInt(sounds.length + 1)] : sounds[0];
            final String soundResource = path + File.separator + sound.getName();
            new Player(getClass().getResourceAsStream(soundResource)).play();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).severe(e.getMessage());
        }
    }


    /**
     * Retrieve folder to get sounds.
     * @param event {@link RaffledEvent}.
     * @return Folder to get sound.
     */
    private String retrieveFolder(RaffledEvent event) {
        // Last balls, thrilling!
        if (event.getNoRaffledNumbers().size() <= BingoConstants.BALLS_THRILLING) {
            return FOLDER_THRILLING;
        }
        // or, folder ball
        return event.getRaffledBall().getNumber().toString();
    }
}