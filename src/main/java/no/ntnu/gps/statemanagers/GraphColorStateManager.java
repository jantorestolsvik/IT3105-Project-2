package no.ntnu.gps.statemanagers;

import no.ntnu.gps.states.AbstractState;
import no.ntnu.gps.states.GraphColorState;

/**
 *
 * @author Jan Tore Stølsvik & Tom Glover 
 */
public class GraphColorStateManager extends AbstractStateManager {
    GraphColorState state;
    public GraphColorStateManager(String file) {
        state = new GraphColorState(file);
    }

    @Override
    public AbstractState nextMinConflictState() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbstractState getState() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
