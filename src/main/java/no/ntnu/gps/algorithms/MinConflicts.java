package no.ntnu.gps.algorithms;

import no.ntnu.gps.statemanagers.StateManager;
import no.ntnu.gps.states.AbstractState;

/**
 *
 * @author Jan Tore Stølsvik & Tom Glover 
 */
public class MinConflicts extends ConstraintBasedLocalSearch {

    public MinConflicts(StateManager stateManager) {
        super(stateManager);
    }
    
    @Override
    public AbstractState solve() {
        AbstractState state = stateManager.getState();
        while(!state.solved()) {
            state = stateManager.nextMinConflictState();
        }
        return state;
    }
    
}