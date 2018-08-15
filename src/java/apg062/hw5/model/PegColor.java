/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apg062.hw5.model;

/**
 * The colors of pegs that can be used in the game
 * @author Productivity
 */
public enum PegColor {
    BLUE, RED, YELLOW, PURPLE, ORANGE, GREEN;

    /**
     * Returns a PegColor whose name matches the given string. 
     * @param color string representing a peg color
     * @return corresponding PegColor, or null if there is no match
     */
    public static PegColor parse(String color) {
        PegColor result = null;
        color = color.toUpperCase();
        
        for(PegColor peg : PegColor.values()){
            if(peg.name().equals(color)){
                result = peg;
                break;
            }
        }
        
        return result;
    }
}
