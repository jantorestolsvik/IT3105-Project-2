/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.ntnu.gps.states;

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
        smallerThanOperators = new int [10][2];
        this.rows = rows;
    }

    @Override
    public boolean solved() {
        for (int i = 0; i < smallerThanOperators.length; i++) {
            if (smallerThanOperators[i][0] > smallerThanOperators[i][1]) {
                return false;
            }
        }
        for (int i = 0; i < rows; i++) {
            boolean []seenRow = new boolean[rows];
            for (int j = 0; j < rows; j++) {
                if (seenRow[board[i*rows + j]] == true) {
                    return false;
                } else {
                    seenRow[board[i*rows + j]] = true;
                }
            }
            boolean []seenCol = new boolean[rows];
            for (int j = 0; j < rows; j++) {
                if (seenCol[board[i + j*rows]] == true) {
                    return false;
                } else {
                    seenCol[board[i + j*rows]] = true;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
