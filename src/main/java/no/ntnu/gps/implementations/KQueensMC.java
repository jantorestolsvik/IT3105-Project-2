package no.ntnu.gps.implementations;

import no.ntnu.gps.algorithms.MinConflicts;
import no.ntnu.gps.statemanagers.AbstractStateManager;
import no.ntnu.gps.statemanagers.KQueensStateManager;

/**
 *
 * @author Jan Tore Stølsvik & Tom Glover 
 */
public class KQueensMC extends MinConflicts {
    public KQueensMC(int k) {
        this.stateManager = new KQueensStateManager(k);
    }
}
