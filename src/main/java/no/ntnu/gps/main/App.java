package no.ntnu.gps.main;

import java.util.Scanner;
import no.ntnu.gps.algorithms.*;
import no.ntnu.gps.statemanagers.StateManager;
import no.ntnu.gps.states.*;

/**
 *
 * @author Jan Tore Stølsvik & Tom Glover 
 */
public class App 
{   
    public static void main( String[] args )
    {
    	int nrOfRuns = 1;
    	int nrOfSolved = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which puzzle do you want to solve?");
        System.out.println("1:Kqueens");
        System.out.println("2:GraphColor");
        System.out.println("3:Futoshiki");
        AbstractState state;
        switch(scanner.nextInt()) {
            case 1:
                System.out.println("How many queens?");
                state = new KQueenState(scanner.nextInt());
                break;
            case 2:
                System.out.println("Which file? 1, 2 or 3");
                state = new GraphColorState("graph" + scanner.nextInt() + ".txt", 4);
                break;
            case 3:
            	  System.out.println("How big in rows? 1-9");
            	state = new FutoshikiState(scanner.nextInt());
            	break;
            default:
                throw new IllegalArgumentException("No such input");
        }
        StateManager stateManager = new StateManager(state);
        System.out.println("Which algorithm do you which to use?");
        System.out.println("1:MinConflicts");
        System.out.println("2:SimulatedAnnealing");
        ConstraintBasedLocalSearch localSearch;
        switch(scanner.nextInt()) {
            case 1:
                localSearch = new MinConflicts(stateManager);
                break;
            case 2:
                localSearch = new SimulatedAnnealing(stateManager);
                break;
            default:
                throw new IllegalArgumentException("No such input");
        }
        
        AbstractState result = localSearch.solve();
        result.display();
        if (result.solved()) {
            System.out.println("Solved!");
        } else {
            System.out.println("Not solved!");
        }
       
    	/*for (int i = 0; i < nrOfRuns; i++) {
    		ConstraintBasedLocalSearch temp = new MinConflicts(new StateManager(new KQueenState(100)));
    		AbstractState result = temp.solve();
			if(result.solved()){
				nrOfSolved++;
			}
		}
    	System.out.println(nrOfSolved + "/" + nrOfRuns);*/
    }
}
