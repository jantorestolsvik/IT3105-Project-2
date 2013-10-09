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
//        ConstraintBasedLocalSearch temp = new GraphColorMC("graph.txt", 4);
        //ConstraintBasedLocalSearch temp = new SimulatedAnnealing(new GraphColorStateManager("graph.txt", 4));
        ConstraintBasedLocalSearch temp = new SimulatedAnnealing(new KQueensStateManager(10));
        AbstractState result = temp.solve();
        System.out.println(result);
        //GraphColorState result2 = (GraphColorState)result;
        //result2.display();
    }
}
