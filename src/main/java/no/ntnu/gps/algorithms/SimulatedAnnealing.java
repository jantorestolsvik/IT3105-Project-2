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
	private AbstractState bestNeightbour;

	@Override
	public AbstractState solve() {
		AbstractState state = stateManager.getRandomStartState();
		AbstractStateManager intermediatNeighbour;
		//		while(!state.solved()&&maxIterations<=0) {
		neighboors = stateManager.getRandomNeighboorStates(nrOfNeigboors);
		for (int i = 0; i < nrOfNeigboors; i++) {
			if(bestNeightbour!=null){
				if(bestNeightbour.evaluation()>neighboors.get(i).evaluation()){
					bestNeightbour = neighboors.get(i);
				}
			}else{
				bestNeightbour = neighboors.get(0);
			}
		}

		maxIterations--;
		//		}
		System.out.println(bestNeightbour);
		System.out.println("LINE");
		System.out.println(stateManager.getState());

		return state;
	}

	public SimulatedAnnealing(AbstractStateManager stateManager) {
		super(stateManager);
	}



}
