package no.ntnu.gps.algorithms;

import java.util.ArrayList;

import no.ntnu.gps.statemanagers.AbstractStateManager;
import no.ntnu.gps.states.AbstractState;

/**
 *
 * @author Jan Tore Stølsvik & Tom Glover 
 */
public class SimulatedAnnealing extends ConstraintBasedLocalSearch {
	protected double tempreture = 1000;
	protected int nrOfNeigboors = 20;
	protected int time= 0;
	protected int maxIterations = 10000;
	protected ArrayList<AbstractState> neighboors;
	private AbstractState bestNeightbour;

	@Override
	public AbstractState solve() {
		AbstractState state = stateManager.getRandomStartState();
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
			System.out.println("Q:"+q);
			System.out.println(("("+  bestNeightbour.evaluation()) + " - " + stateManager.getState().evaluation()+ ")/" + stateManager.getState().evaluation());
			double p = Math.exp(-q/(double)tempreture);
			System.out.println("P:"+p);
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
				
			tempreture /=1.1;
			System.out.println("T:"+tempreture);
			maxIterations--;
		}
		//		System.out.println(bestNeightbour);
		//		System.out.println("LINE");
		//		System.out.println(stateManager.getState());
System.out.println(state.evaluation() + " last state eval");
		return state;
	}

	public SimulatedAnnealing(AbstractStateManager stateManager) {
		super(stateManager);
	}



}
