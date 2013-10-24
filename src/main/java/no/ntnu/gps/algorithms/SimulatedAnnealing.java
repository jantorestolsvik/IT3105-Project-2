package no.ntnu.gps.algorithms;

import java.util.ArrayList;

import no.ntnu.gps.statemanagers.StateManager;
import no.ntnu.gps.states.AbstractState;
import no.ntnu.gps.states.KQueenState;

/**
 *
 * @author Jan Tore Stølsvik & Tom Glover 
 */
public class SimulatedAnnealing extends ConstraintBasedLocalSearch {
	protected double tempreture = 100;
	protected int nrOfNeigboors = 40;
	protected int bestOfRun = 0;
//	protected int time= 0;
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
			if(bestNeightbour.evaluation()>bestOfRun){
				bestOfRun = bestNeightbour.evaluation();
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
				
			tempreture /=1.0001;
			maxIterations--;
		}
		return state;
	}

	public SimulatedAnnealing(StateManager stateManager) {
		super(stateManager);
	}

	public static void main(String[] args) {
        int nrOfRuns = 20;
    	int nrOfSolved = 0;
        int [] eval = new int [nrOfRuns];	
        int [] steps = new int [nrOfRuns];
    	for (int i = 0; i < nrOfRuns; i++) {
    		ConstraintBasedLocalSearch temp = new SimulatedAnnealing(new StateManager(new KQueenState(1000)));
    		AbstractState result = temp.solve();
    		eval [i] = ((SimulatedAnnealing)temp).bestOfRun;
    		steps[i] = 100000 - ((SimulatedAnnealing)temp).maxIterations;
			if(result.solved()){
				nrOfSolved++;
			}
		}
    	System.out.println(nrOfSolved + "/" + nrOfRuns);/**/
    	for (int i = 0; i < steps.length; i++) {
			System.out.println("id:" + i + " eval:" + eval[i] + " steps:" + steps[i]);
		}
	}


}
