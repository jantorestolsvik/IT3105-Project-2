/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.ntnu.gps.states;

import java.util.List;

/**
 *
 * @author Jani
 */
public abstract class AbstractState {

    public abstract boolean solved();
    public abstract int evaluation();
    public abstract void display();
    public abstract List<Integer> conflictedStates();
    public abstract List<Integer> leastConflictedNeighbours(int chosenIndex);
    public abstract void change(int chosenIndex, int chosenNeighbour);
    public abstract AbstractState randomNeighbourState();
    
}
