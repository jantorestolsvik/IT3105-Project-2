package no.ntnu.gps.algorithms;

import no.ntnu.gps.statemanagers.StateManager;
import no.ntnu.gps.states.AbstractState;

/**
 *
 * @author Jan Tore Stølsvik & Tom Glover 
 */
public abstract class ConstraintBasedLocalSearch {
    protected StateManager stateManager;

    public ConstraintBasedLocalSearch(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    public abstract AbstractState solve();
}
