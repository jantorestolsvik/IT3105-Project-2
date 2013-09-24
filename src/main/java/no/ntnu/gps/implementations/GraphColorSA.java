package no.ntnu.gps.implementations;

import no.ntnu.gps.algorithms.SimulatedAnnealing;
import no.ntnu.gps.statemanagers.AbstractStateManager;
import no.ntnu.gps.statemanagers.GraphColorStateManager;

/**
 *
 * @author Jan Tore Stølsvik & Tom Glover 
 */
public class GraphColorSA extends SimulatedAnnealing {

    public GraphColorSA(AbstractStateManager stateManager) {
        super(stateManager);
    }

}
