package no.ntnu.gps.main;

import no.ntnu.gps.algorithms.*;
import no.ntnu.gps.statemanagers.GraphColorStateManager;
import no.ntnu.gps.statemanagers.KQueensStateManager;
import no.ntnu.gps.states.*;

/**
 *
 * @author Jan Tore Stølsvik & Tom Glover 
 */
public class App 
{
    public static void main( String[] args )
    {
    	int nrOfRuns = 100;
    	int nrOfSolved = 0;
//        ConstraintBasedLocalSearch temp = new GraphColorMC("graph.txt", 4);
    	for (int i = 0; i < nrOfRuns; i++) {
    		ConstraintBasedLocalSearch temp = new SimulatedAnnealing(new GraphColorStateManager("graph4.txt", 4));
    		AbstractState result = temp.solve();
			if(result.solved()){
				nrOfSolved++;
			}
		}
    	System.out.println(nrOfSolved + "/" + nrOfRuns);
        //ConstraintBasedLocalSearch temp = new SimulatedAnnealing(new KQueensStateManager(10));
        //System.out.println(result);
//        GraphColorState result2 = (GraphColorState)result;
//        result2.display();
    }
}
