package no.ntnu.gps.statemanagers;

import no.ntnu.gps.states.AbstractState;

/**
 *
 * @author Jan Tore Stølsvik & Tom Glover 
 */
public abstract class AbstractStateManager {
    public abstract AbstractState nextMinConflictState();
    public abstract AbstractState getState();
    public abstract AbstractState getRandomNeighboorState();
    public abstract AbstractState getRandomStartState();
    
    
}
