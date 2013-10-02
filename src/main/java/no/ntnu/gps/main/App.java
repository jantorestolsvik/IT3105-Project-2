package no.ntnu.gps.main;

import no.ntnu.gps.algorithms.ConstraintBasedLocalSearch;
import no.ntnu.gps.implementations.GraphColorMC;
import no.ntnu.gps.implementations.KQueensMC;
import no.ntnu.gps.states.AbstractState;

/**
 *
 * @author Jan Tore Stølsvik & Tom Glover 
 */
public class App 
{
    public static void main( String[] args )
    {
        ConstraintBasedLocalSearch temp = new GraphColorMC("graph.txt", 4);
        AbstractState result = temp.solve();
        System.out.println(result.toString());
    }
}
