package no.ntnu.gps.algorithms;

import java.util.ArrayList;

import no.ntnu.gps.statemanagers.AbstractStateManager;
import no.ntnu.gps.states.AbstractState;

/**
 *
 * @author Jan Tore Stølsvik & Tom Glover 
 */
public class SimulatedAnnealing extends ConstraintBasedLocalSearch {
	protected int tempreture = 100;
	protected int nrOfNeigboors = 20;
	protected int time= 0;
	protected int maxIterations = 100;
	protected ArrayList<AbstractState> neighboors;
	private AbstractStateManager bestNeightbour;

	@Override
	public AbstractState solve() {
		AbstractState state = stateManager.getRandomStartState();
		while(!state.solved()&&maxIterations!=0) {
			neighboors = new ArrayList<AbstractState>();
			for (int i = 0; i < nrOfNeigboors; i++) {
				neighboors.add(stateManager.getRandomNeighboorState());
			}
			maxIterations--;
		}
		
		return state;
	}

	public SimulatedAnnealing(AbstractStateManager stateManager) {
		super(stateManager);
	}



}
