package no.ntnu.gps.algorithms;

import no.ntnu.gps.implementations.KQueensMC;
import no.ntnu.gps.statemanagers.AbstractStateManager;

/**
 *
 * @author Jan Tore Stølsvik & Tom Glover 
 */
public abstract class ConstraintBasedLocalSearch {
    protected AbstractStateManager stateManager;

    public ConstraintBasedLocalSearch(AbstractStateManager stateManager) {
        this.stateManager = stateManager;
    }

    public void solve() {
    }
}
