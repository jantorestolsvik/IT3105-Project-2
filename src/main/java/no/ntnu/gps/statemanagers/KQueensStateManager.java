package no.ntnu.gps.statemanagers;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jan Tore Stølsvik & Tom Glover 
 */
public class KQueensStateManager extends AbstractStateManager {
    private int k;
    private int [] queens;
    
    public KQueensStateManager(int k) {
        this.k = k;
        queens = new int [k];
        for (int i = 0; i < k; i++) {
            queens[i] = 0;
        }
    }

    @Override
    public void nextMinConflictState() {
        List<Integer> conflictedStates = conflictedStates();
        int chosenIndex = conflictedStates.get((int) (Math.random() * conflictedStates.size()));
        queens[chosenIndex] = leastConflictedPosition(chosenIndex);
    }

    @Override
    public boolean solved() {
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                if ((queens[i] == queens[j])&&(i!=j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private List<Integer> conflictedStates() {
        List<Integer> conflictedStates = new ArrayList<Integer>();
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                if ((queens[i] == queens[j])&&(i!=j)) {
                    conflictedStates.add(i);
                    break;
                }
            }
        }
        return conflictedStates;
    }

    private int leastConflictedPosition(Integer queen) {
        int [] conflicts = new int[k];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                if (i != queen) {
                    if (queens[i] == j) {
                        conflicts[j]++;
                    }
                }
            }
        }
        int min = conflicts[0];
        int minPos = 0;
        for (int i = 1; i < k; i++) {
            if (min>=conflicts[i]) {
                if (i!=queens[queen]) {
                    minPos = i;
                    min = conflicts[i];    
                }
            }
        }
        return minPos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                if (queens[i]==j) {
                    sb.append("Q\t");
                } else {
                    sb.append("\t");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
