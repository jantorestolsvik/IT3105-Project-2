package no.ntnu.gps.implementations;

import no.ntnu.gps.algorithms.MinConflicts;
import no.ntnu.gps.statemanagers.AbstractStateManager;
import no.ntnu.gps.statemanagers.GraphColorStateManager;

/**
 *
 * @author Jan Tore Stølsvik & Tom Glover 
 */
public class GraphColorMC extends MinConflicts {

    public GraphColorMC(String file) {
        super(new GraphColorStateManager(file));
    }
    
}
