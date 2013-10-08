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
public class KQueenState extends AbstractState {
    private int [] conflicts;
    private int [] queens;
    private int k;

    public KQueenState(int k) {
        this.k = k;
        this.conflicts = new int[k];
        this.queens = new int[k];
        for (int i = 0; i < k; i++) {
            queens[i]=0;
            conflicts[i]=k-1;
        }
    }
    
    public KQueenState randomNeighbourState(){
//    	try {
			KQueenState returner = this.clone();
    	int queen = (int) (Math.random()*k);
    	int pos = (int) (Math.random()*k);
    	
    	returner.moveQueen(queen, pos);
    	
    	return returner;
//    	} catch (CloneNotSupportedException e) {
//    		e.printStackTrace();
//    		return null;
    			
    }
    public KQueenState clone(){
    	KQueenState returner = new KQueenState(k);
    	returner.conflicts = this.conflicts.clone();
    	returner.queens = this.queens.clone();
    	returner.k = this.k;
//    	System.out.println(returner);
//    	System.out.println(this);
    	return returner;
    }
    
    public boolean solved() {
        for (int i = 0; i < k; i++) {
            if (conflicts[i] != 0) {
                return false;
            }
        }
        return true;
    }
    
    public void moveQueen(int queenIndex, int position) {
        addConflict(queenIndex, -1);
        queens[queenIndex] = position;
        addConflict(queenIndex, 1);
    }

    private void addConflict(int queenIndex, int conflict) {
        for (int i = 0; i < k; i++) {
            if (i!=queenIndex) {
                if (queens[i]==queens[queenIndex]) {
                    conflicts[i]+=conflict;
                    conflicts[queenIndex]+=conflict;
                }
                int offsetPos = Math.abs(queenIndex-i);
                if (queens[i]==queens[queenIndex]+offsetPos) {
                    conflicts[i]+=conflict;
                    conflicts[queenIndex]+=conflict;
                }
                if (queens[i]==queens[queenIndex]-offsetPos) {
                    conflicts[i]+=conflict;
                    conflicts[queenIndex]+=conflict;
                }
            }
        }
    }

    public List<Integer> conflictedStates() {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < k; i++) {
            if (conflicts[i] != 0) {
                list.add(i);
            }
        }
        return list;
    }

    public List<Integer> leastConflictedPositions(int chosenIndex) {
        int min = Integer.MAX_VALUE;
        List<Integer> list = new ArrayList<Integer>();
        int [] tempConflicts = new int[k];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                if (j!=chosenIndex) {
                    if (i==queens[j]) {
                        tempConflicts[i]++;
                    }
                    int offsetPos = Math.abs(j-chosenIndex);
                    if (i==queens[j]+offsetPos) {
                        tempConflicts[i]++;
                    }
                    if (i==queens[j]-offsetPos) {
                        tempConflicts[i]++;
                    }
                }
            }
        }
        for (int i = 0; i < k; i++) {
            if (tempConflicts[i] < min) {
                min = tempConflicts[i];
            }
        }
        for (int i = 0; i < k; i++) {
            if(tempConflicts[i] == min) {
                list.add(i);
            }
        }
        if (list.contains(queens[chosenIndex])&&(list.size()!=1)) {
            list.remove(list.indexOf(queens[chosenIndex]));
        }
        return list;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                if (queens[i]==j) {
                    sb.append("Q"+conflicts[i]+"\t");
                } else {
                    sb.append("\t");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

	public int[] getConflicts() {
		return conflicts;
	}
}
