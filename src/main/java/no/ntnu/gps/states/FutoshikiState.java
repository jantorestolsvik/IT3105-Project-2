/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.ntnu.gps.states;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jani
 */
public class FutoshikiState extends AbstractState {
   
    private int board[];
    private int [][] smallerThanOperators;
    private int rows;
    public FutoshikiState(int rows) {
        board = new int[rows*rows];
        smallerThanOperators = new int [0][2];
        this.rows = rows;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                board[i*rows+j] = j;
            }
        }
    }

    @Override
    public boolean solved() {
        for (int i = 0; i < rows; i++) {
            boolean [] seen = new boolean[rows];
            for (int j = 0; j < rows; j++) {
                if (seen[board[i+rows*j]] == true) {
                    return false;
                } else {
                    seen[board[i+rows*j]] = true;
                }
            }
        }
        return true;
    }

    @Override
    public int evaluation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Integer> conflictedStates() {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < rows; i++) {
            boolean [] seen = new boolean[rows];
            for (int j = 0; j < rows; j++) {
                if (seen[positions[i][j]] == true) {
                    return false;
                } else {
                    seen[positions[i][j]] = true;
                }
            }
        }
        return list;
    }

    @Override
    public List<Integer> leastConflictedNeighbours(int chosenIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void change(int chosenIndex, int chosenNeighbour) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbstractState randomNeighbourState() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void display() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
