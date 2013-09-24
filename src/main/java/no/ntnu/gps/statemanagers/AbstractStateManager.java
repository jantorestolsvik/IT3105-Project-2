package no.ntnu.gps.statemanagers;

/**
 *
 * @author Jan Tore Stølsvik & Tom Glover 
 */
public abstract class AbstractStateManager {
    
    public abstract void nextMinConflictState();
    public abstract boolean solved();

}
