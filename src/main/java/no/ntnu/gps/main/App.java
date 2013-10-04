package no.ntnu.gps.main;

import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import no.ntnu.gps.algorithms.ConstraintBasedLocalSearch;
import no.ntnu.gps.implementations.GraphColorMC;
import no.ntnu.gps.implementations.KQueensMC;
import no.ntnu.gps.states.AbstractState;
import no.ntnu.gps.states.GraphColorState;

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
        GraphColorState result2 = (GraphColorState)result;
        result2.display();
    }
}
