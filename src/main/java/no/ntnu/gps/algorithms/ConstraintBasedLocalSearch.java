package no.ntnu.gps.algorithms;

import no.ntnu.gps.statemanagers.AbstractStateManager;
import no.ntnu.gps.states.AbstractState;

/**
 *
 * @author Jan Tore Stølsvik & Tom Glover 
 */
public abstract class ConstraintBasedLocalSearch {
    protected AbstractStateManager stateManager;

    public ConstraintBasedLocalSearch(AbstractStateManager stateManager) {
        this.stateManager = stateManager;
    }

    public abstract AbstractState solve();
}
