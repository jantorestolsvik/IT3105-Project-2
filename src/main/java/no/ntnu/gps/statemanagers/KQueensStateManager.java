/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.ntnu.gps.statemanagers;

/**
 *
 * @author Jan Tore Stølsvik & Tom Glover 
 */
public class KQueensStateManager extends AbstractStateManager {
    private int k;
    private int [][] board;
    private int [][] constreints;
    public KQueensStateManager(int k) {
        this.k = k;
        board = new int [k][k];
        for (int i = 0; i < k; i++) {
			board[0][i] = 1;
		}
        
    }

    @Override
    public void nextMinConflictState() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
