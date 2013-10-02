package no.ntnu.gps.statemanagers;

import java.util.List;
import no.ntnu.gps.states.AbstractState;
import no.ntnu.gps.states.GraphColorState;

/**
 *
 * @author Jan Tore Stølsvik & Tom Glover 
 */
public class GraphColorStateManager extends AbstractStateManager {
    GraphColorState state;
    public GraphColorStateManager(String file, int K) {
        state = new GraphColorState(file, K);
    }

    @Override
    public AbstractState nextMinConflictState() {
        List<Integer> conflictedStates = state.conflictedStates();
        int chosenIndex = conflictedStates.get((int) (Math.random() * conflictedStates.size()));
        List<Integer> leastConflictedPositions = state.leastConflictedPositions(chosenIndex);
        int chosenPosition = leastConflictedPositions.get((int) (Math.random() * leastConflictedPositions.size()));
        state.changeColor(chosenIndex, chosenPosition);
        return this.state;
    }

    @Override
    public AbstractState getState() {
        return state;
    }
}
