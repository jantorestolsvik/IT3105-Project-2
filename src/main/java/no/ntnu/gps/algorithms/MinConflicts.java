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
        int i=0;
        while(!state.solved()&&i<1000000) {
            state = stateManager.nextMinConflictState();
            i++;
        }
        return state;
    }
    
}