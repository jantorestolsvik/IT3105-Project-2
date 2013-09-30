package no.ntnu.gps.statemanagers;

import java.util.ArrayList;
import java.util.List;
import no.ntnu.gps.states.AbstractState;
import no.ntnu.gps.states.KQueenState;

/**
 *
 * @author Jan Tore Stølsvik & Tom Glover 
 */
public class KQueensStateManager extends AbstractStateManager {
    KQueenState state;

    @Override
    public KQueenState getState() {
        return state;
    }
    
    public KQueensStateManager(int k) {
        this.state = new KQueenState(k);
    }

    @Override
    public AbstractState nextMinConflictState() {
        List<Integer> conflictedStates = state.conflictedStates();
        int chosenIndex = conflictedStates.get((int) (Math.random() * conflictedStates.size()));
        List<Integer> leastConflictedPositions = state.leastConflictedPositions(chosenIndex);
        int chosenPosition = leastConflictedPositions.get((int) (Math.random() * leastConflictedPositions.size()));
        state.moveQueen(chosenIndex, chosenPosition);
        return this.state;
    }
}
