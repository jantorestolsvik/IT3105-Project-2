package no.ntnu.gps.algorithms;

import java.util.ArrayList;

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
	protected ArrayList<AbstractState> neighboors;

	@Override
	public AbstractState solve() {
		AbstractState state = stateManager.getRandomStartState();
		while(!state.solved()) {
			neighboors = new ArrayList<AbstractState>();
			for (int i = 0; i < nrOfNeigboors; i++) {
				neighboors.add(stateManager.getRandomNeighboorState());
			}
		}
		return state;
	}

	public SimulatedAnnealing(AbstractStateManager stateManager) {
		super(stateManager);
	}



}
