package no.ntnu.gps.implementations;

import no.ntnu.gps.algorithms.SimulatedAnnealing;
import no.ntnu.gps.statemanagers.AbstractStateManager;
import no.ntnu.gps.statemanagers.GraphColorStateManager;
import no.ntnu.gps.states.AbstractState;

/**
 *
 * @author Jan Tore Stølsvik & Tom Glover 
 */
public class GraphColorSA extends SimulatedAnnealing {

    public GraphColorSA(AbstractStateManager stateManager) {
        super(stateManager);
    }

}
