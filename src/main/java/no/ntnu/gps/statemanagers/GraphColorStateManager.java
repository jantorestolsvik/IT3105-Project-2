package no.ntnu.gps.statemanagers;

import java.util.List;
import no.ntnu.gps.states.AbstractState;
import no.ntnu.gps.states.GraphColorState;

/**
 *
 * @author Jan Tore St�lsvik & Tom Glover 
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
        List<Integer> leastConflictedColors = state.leastConflictedColors(chosenIndex);
        int chosenColor = leastConflictedColors.get((int) (Math.random() * leastConflictedColors.size()));
        state.changeColor(chosenIndex, chosenColor);
        return this.state;
    }

    @Override
    public AbstractState getState() {
        return state;
    }
}
