package no.ntnu.gps.statemanagers;

import java.util.ArrayList;
import java.util.List;

import no.ntnu.gps.states.AbstractState;

/**
 *
 * @author Jan Tore Stølsvik & Tom Glover 
 */
public class StateManager {

    AbstractState state;
    public StateManager(AbstractState state) {
        this.state = state;
    }
    
    public AbstractState nextMinConflictState() {
        List<Integer> conflictedIndexes = state.conflictedStates();
        int chosenIndex = conflictedIndexes.get((int) (Math.random() * conflictedIndexes.size()));
        List<Integer> leastConflictedNeighbours = state.leastConflictedNeighbours(chosenIndex);
        int chosenNeighbour = leastConflictedNeighbours.get((int) (Math.random() * leastConflictedNeighbours.size()));
        state.change(chosenIndex, chosenNeighbour);
        return this.state;
    }
    
    public AbstractState getState() {
        return state;
    }
    
    public void setState(AbstractState state) {
        this.state = state;
    }
    
    public ArrayList<AbstractState> getRandomNeighboorStates(int amount) {
        ArrayList<AbstractState> randomNeighboorStates = new ArrayList<AbstractState>();
        for (int i = 0; i < amount; i++) {
                randomNeighboorStates.add(state.randomNeighbourState());
        }
        return randomNeighboorStates;
    }
}
