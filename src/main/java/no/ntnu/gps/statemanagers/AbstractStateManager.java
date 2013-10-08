package no.ntnu.gps.statemanagers;

import java.util.ArrayList;

import no.ntnu.gps.states.AbstractState;

/**
 *
 * @author Jan Tore Stølsvik & Tom Glover 
 */
public abstract class AbstractStateManager {
    public abstract AbstractState nextMinConflictState();
    public abstract AbstractState getState();
    public abstract ArrayList<AbstractState> getRandomNeighboorStates(int nr);
    public abstract AbstractState getRandomStartState();
    
}
