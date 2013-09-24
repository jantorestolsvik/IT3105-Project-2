package no.ntnu.gps.algorithms;

import no.ntnu.gps.statemanagers.AbstractStateManager;

/**
 *
 * @author Jan Tore Stølsvik & Tom Glover 
 */
public abstract class SimulatedAnnealing extends ConstraintBasedLocalSearch {
	protected int tempreture = 100;
	protected int nrOfNeigboors = 20;
	protected int time= 0;

    public SimulatedAnnealing(AbstractStateManager stateManager) {
        super(stateManager);
    }
    
}
