/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apg062.hw5.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Bean representing a game of Mastermind. Contains the secret code and a list
 * of all player's previous guesses and associated feedback.
 *
 * @author Productivity
 */
public class GameBean implements Serializable {
    private static final int NUM_CODE_PEGS = 4;
    private boolean won;
    private boolean valid;
    private PegColor[] solution;
    private List<TurnBean> history;

    /**
     * Creates a game with a solution chosen randomly.
     */
    public GameBean() {
        won = false;
        valid = true;
        history = new LinkedList<>();

        //generate solution
        Random rand = new Random();
        solution = new PegColor[NUM_CODE_PEGS];
        for (int i = 0; i < solution.length; i++) {
            solution[i] = PegColor.values()[rand.nextInt(PegColor.values().length)];
        }
    }
    
    /**
     * Creates a game with the given solution
     * @param solution solution to game
     */
    public GameBean(PegColor[] solution){
        won = false;
        valid = true;
        history = new LinkedList<>();
        this.solution = Arrays.copyOf(solution, solution.length);
    }

    //for testing feedback algorithm
    private void testFeedback(Random rand) {
        for (int i = 0; i < 20; i++) {
            PegColor[] guess = new PegColor[]{
                PegColor.values()[rand.nextInt(PegColor.values().length)],
                PegColor.values()[rand.nextInt(PegColor.values().length)],
                PegColor.values()[rand.nextInt(PegColor.values().length)],
                PegColor.values()[rand.nextInt(PegColor.values().length)]
            };

            takeGuess(guess);
        }
    }

    /**
     * Returns a list depicting all the turns taken including guesses and feedback
     *
     * @return list of all turns taken
     */
    public List getHistory() {
        return new LinkedList<>(history);
    }

    /**
     * Allows player to take a guess. Adds guessed pegs and associated feedback
     * to the game history.
     * @param guess PegColor array representing player's takeGuess
     */
    public void takeGuess(PegColor[] guess) {
        TurnBean turn = new TurnBean();
        turn.setGuess(guess);
        FeedbackColor[] feedback = generateFeedback(guess);
        turn.setFeedback(feedback);
        history.add(0, turn);
        won = feedback[0] == FeedbackColor.BLACK
                && feedback[1] == FeedbackColor.BLACK
                && feedback[2] == FeedbackColor.BLACK
                && feedback[3] == FeedbackColor.BLACK;
    }

    //compares guess to solution and generates appropriate feedback
    private FeedbackColor[] generateFeedback(PegColor[] guess) {
        int black = 0;
        int white = 0;

        //copy guess and solution
        ArrayList<PegColor> solnList = new ArrayList<>(Arrays.asList(solution));
        ArrayList<PegColor> guessList = new ArrayList<>(Arrays.asList(guess));

        //count black feedback pegs
        for (int i = 0; i < guessList.size();) {
            if (guessList.get(i) == solnList.get(i)) {
                black++;
                //remove from list so pegs aren't counted twice
                guessList.remove(i);
                solnList.remove(i);
            } else {
                i++;
            }
        }

        //count white feedback pegs
        for (int g = 0; g < guessList.size(); g++) {
            for (int s = 0; s < solnList.size();) {
                if (guessList.get(g) == solnList.get(s)) {
                    white++;
                    solnList.remove(s);
                    break;
                } else {
                    s++;
                }
            }
        }

        //output for debugging
        //System.out.println("Guess: " + Arrays.toString(guess));
        //System.out.println("black: " + black + " white: " + white);

        //create array of feedback pegs
        FeedbackColor[] feedback = new FeedbackColor[NUM_CODE_PEGS];
        int index = 0;
        while(black > 0){
            feedback[index] = FeedbackColor.BLACK;
            black--;
            index++;
        }
        while(white > 0){
            feedback[index] = FeedbackColor.WHITE;
            white--;
            index++;
        }
        
        return feedback;
    }


    /**
     * Returns whether the game has been won or not
     * @return whether or not the game is won
     */
    public boolean isWon() {
        return won;
    }

    /**
     * Returns the game's solution. This is for displaying the solution at
     * the end of the game.
     * @return the game's solution
     */
    public PegColor[] getSolution() {
       return solution.clone();
    }
    
    /**
     * Returns the current number of guesses taken so far this game
     * @return number of guesses
     */
    public int getNumGuesses(){
        return history.size();
    }
    
    /**
     * Makes the current game no longer valid, for example, if the user clicks
     * the 'I give up, what is it?' button
     */
    public void invalidate(){
        valid = false;
        System.out.println("game invalidated");
    }
    
    /**
     * Returns whether or not the game is still valid.  Games can be invalidated
     * if the user clicks the 'I give up, what is it?' button.
     * @return true if game is valid
     */
    public boolean isValid(){
        return valid;
    }
}
