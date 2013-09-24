package no.ntnu.gps.algorithms;

import no.ntnu.gps.statemanagers.AbstractStateManager;

/**
 *
 * @author Jan Tore Stølsvik & Tom Glover 
 */
public abstract class MinConflicts extends ConstraintBasedLocalSearch {

    public MinConflicts(AbstractStateManager stateManager) {
        super(stateManager);
    }
    
}
