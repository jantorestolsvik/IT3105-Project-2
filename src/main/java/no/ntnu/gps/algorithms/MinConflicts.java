package no.ntnu.gps.algorithms;

import no.ntnu.gps.statemanagers.StateManager;
import no.ntnu.gps.states.AbstractState;
import no.ntnu.gps.states.KQueenState;

/**
 *
 * @author Jan Tore Stølsvik & Tom Glover 
 */
public class MinConflicts extends ConstraintBasedLocalSearch {
	protected int bestOfRun = 0;
	protected int i=0;
    public MinConflicts(StateManager stateManager) {
        super(stateManager);
    }
    
    @Override
    public AbstractState solve() {
        AbstractState state = stateManager.getState();
        i=0;
        while(!state.solved()&&i<1000000) {
            state = stateManager.nextMinConflictState();
            i++;
            
    		if(state.evaluation()>bestOfRun){
				bestOfRun = state.evaluation();
			}
        }
        return state;
    }
    
    
	public static void main(String[] args) {
        int nrOfRuns = 20;
    	int nrOfSolved = 0;
        int [] eval = new int [nrOfRuns];	
        int [] steps = new int [nrOfRuns];
    	for (int i = 0; i < nrOfRuns; i++) {
    		ConstraintBasedLocalSearch temp = new MinConflicts(new StateManager(new KQueenState(25)));
    		AbstractState result = temp.solve();
    		eval [i] = ((MinConflicts)temp).bestOfRun;
    		steps[i] = ((MinConflicts)temp).i;
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