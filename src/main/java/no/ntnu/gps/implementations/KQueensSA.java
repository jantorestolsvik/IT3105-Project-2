package no.ntnu.gps.implementations;

import no.ntnu.gps.algorithms.SimulatedAnnealing;
import no.ntnu.gps.statemanagers.KQueensStateManager;

/**
 *
 * @author Jan Tore Stølsvik & Tom Glover 
 */
public class KQueensSA extends SimulatedAnnealing {

    public KQueensSA(int k) {
        super(new KQueensStateManager(k));
    }
    
}
