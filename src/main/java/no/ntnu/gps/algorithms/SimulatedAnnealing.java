package no.ntnu.gps.algorithms;

import no.ntnu.gps.statemanagers.AbstractStateManager;

/**
 *
 * @author Jan Tore Stølsvik & Tom Glover 
 */
public abstract class SimulatedAnnealing extends ConstraintBasedLocalSearch {

    public SimulatedAnnealing(AbstractStateManager stateManager) {
        super(stateManager);
    }
    
}
