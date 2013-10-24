/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.ntnu.gps.states;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jan Tore Stølsvik & Tom Glover 
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
		KQueenState returner = this.clone();
		int queen = (int) (Math.random()*k);
		int pos = (int) (Math.random()*k);
		while(queens[queen] == pos) {
			pos = (int) (Math.random()*k);
		}
		returner.change(queen, pos);
		return returner;
	}

	public KQueenState clone(){
		KQueenState returner = new KQueenState(k);
		returner.conflicts = this.conflicts.clone();
		returner.queens = this.queens.clone();
		returner.k = this.k;
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

	@Override
	public int evaluation() {
		int eval = 0;
		if (this.solved()){
			return Integer.MAX_VALUE;
		}
		for (int i = 0; i < conflicts.length; i++) {
			eval += conflicts[i];
		}
		return conflicts.length*conflicts.length - eval;
	}

	@Override
	public List<Integer> leastConflictedNeighbours(int chosenIndex) {
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

	@Override
	public void change(int chosenIndex, int chosenNeighbour) {
		addConflict(chosenIndex, -1);
		queens[chosenIndex] = chosenNeighbour;
		addConflict(chosenIndex, 1);
	}

	@Override
	public void display() {
		System.out.println(this.toString());
	}

}
