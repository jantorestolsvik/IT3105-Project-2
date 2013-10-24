package no.ntnu.gps.algorithms;

import no.ntnu.gps.statemanagers.StateManager;
import no.ntnu.gps.states.AbstractState;
import no.ntnu.gps.states.GraphColorState;
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
    		ConstraintBasedLocalSearch temp = new MinConflicts(new StateManager(new GraphColorState("graph3.txt",4)));
    		AbstractState result = temp.solve();
    		eval [i] = ((MinConflicts)temp).bestOfRun;
    		steps[i] = ((MinConflicts)temp).i;
			if(result.solved()){
				nrOfSolved++;
			}
		}
    	System.out.println(nrOfSolved + "/" + nrOfRuns);/**/
        double meanEval = 0;
        double meanStep = 0;
        double varianceEval = 0;
        double varianceStep = 0;
    	for (int i = 0; i < steps.length; i++) {
			System.out.println("id:" + i + " eval:" + eval[i] + " steps:" + steps[i]);
                        meanEval += eval[i];
                        meanStep += steps[i];
            }
        meanEval /= steps.length;
        meanStep /= steps.length;
            for (int i = 0; i < steps.length; i++) {
                varianceEval += ((eval[i]-meanEval)*(eval[i]-meanEval));
                varianceStep += ((steps[i]-meanStep)*(steps[i]-meanStep));
            }
        varianceEval /= steps.length;
        varianceStep /= steps.length;
        double SDEval = Math.sqrt(varianceEval);
        double SDStep = Math.sqrt(varianceStep);
            System.out.println("Eval mean: " + meanEval);
            System.out.println("Step mean: " + meanStep);
            System.out.println("Eval SD: " + SDEval);
            System.out.println("Step SD: " + SDStep);
    }
    
}