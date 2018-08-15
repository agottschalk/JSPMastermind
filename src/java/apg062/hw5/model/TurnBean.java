/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apg062.hw5.model;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Represents a single turn in the game. Contains the colors guessed and the
 * feedback received on that turn.
 *
 * @author Productivity
 */
public class TurnBean implements Serializable {
    private final int NUM_CODE_PEGS = 4;
    private PegColor[] guess;
    private FeedbackColor[] feedback;

    /**
     * Creates a bean with empty arrays of pegs
     */
    public TurnBean() {
        guess = new PegColor[NUM_CODE_PEGS];
        feedback = new FeedbackColor[NUM_CODE_PEGS];
    }

    /**
     * Get the value of guess
     *
     * @return the value of guess
     */
    public PegColor[] getGuess() {
        return guess.clone();
    }

    /**
     * Set the value of guess
     *
     * @param guess new value of guess
     */
    public void setGuess(PegColor[] guess) {
        this.guess = guess.clone();
    }

    public FeedbackColor[] getFeedback() {
        return feedback.clone();
    }

    public void setFeedback(FeedbackColor[] feedback) {
        this.feedback = feedback.clone();
    }
}
