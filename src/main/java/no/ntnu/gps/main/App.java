package no.ntnu.gps.main;

import no.ntnu.gps.algorithms.ConstraintBasedLocalSearch;
import no.ntnu.gps.implementations.GraphColorMC;
import no.ntnu.gps.implementations.KQueensMC;

/**
 *
 * @author Jan Tore Stølsvik & Tom Glover 
 */
public class App 
{
    public static void main( String[] args )
    {
        ConstraintBasedLocalSearch temp = new KQueensMC(4);
        temp.solve();
        System.out.println(temp.toString());
    }
}
