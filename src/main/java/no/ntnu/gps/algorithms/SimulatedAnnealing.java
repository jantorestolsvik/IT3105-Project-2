package no.ntnu.gps.algorithms;

import no.ntnu.gps.statemanagers.AbstractStateManager;
import no.ntnu.gps.states.AbstractState;

/**
 *
 * @author Jan Tore Stølsvik & Tom Glover 
 */
public abstract class SimulatedAnnealing extends ConstraintBasedLocalSearch {
	protected int tempreture = 100;
	protected int nrOfNeigboors = 20;
	protected int time= 0;

    @Override
    public AbstractState solve() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public SimulatedAnnealing(AbstractStateManager stateManager) {
        super(stateManager);
    }
    
    
    
}
