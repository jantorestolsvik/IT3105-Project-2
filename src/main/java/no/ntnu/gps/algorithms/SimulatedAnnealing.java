package no.ntnu.gps.algorithms;

import java.util.ArrayList;

import no.ntnu.gps.statemanagers.StateManager;
import no.ntnu.gps.states.AbstractState;

/**
 *
 * @author Jan Tore Stølsvik & Tom Glover 
 */
public class SimulatedAnnealing extends ConstraintBasedLocalSearch {
	protected double tempreture = 100;
	protected int nrOfNeigboors = 40;
	protected int time= 0;
	protected int maxIterations = 100000;
	protected ArrayList<AbstractState> neighboors;
	private AbstractState bestNeightbour;

	@Override
	public AbstractState solve() {
		AbstractState state = stateManager.getState();
		while(!state.solved()&&maxIterations>=0) {
			neighboors = stateManager.getRandomNeighboorStates(nrOfNeigboors);
			for (int i = 0; i < nrOfNeigboors; i++) {
				if(bestNeightbour!=null){
					if(bestNeightbour.evaluation()<neighboors.get(i).evaluation()){
						bestNeightbour = neighboors.get(i);
					}
				}else{
					bestNeightbour = neighboors.get(0);
				}
			}
			double q = ((double)bestNeightbour.evaluation() - (double)stateManager.getState().evaluation() )/(double)stateManager.getState().evaluation();
			double p = Math.exp(-q/(double)tempreture);
			if(p>1){
				p=1;
			}
			if(Math.random()>p){
				this.stateManager.setState(bestNeightbour);
				state = bestNeightbour;
			}else{
				this.stateManager.setState(neighboors.get((int)(Math.random()*20)));
				state = bestNeightbour;
			}
				
			tempreture /=1.001;
			maxIterations--;
		}
		return state;
	}

	public SimulatedAnnealing(StateManager stateManager) {
		super(stateManager);
	}



}
