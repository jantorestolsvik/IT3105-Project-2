/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.ntnu.gps.states;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.ChangedCharSetException;

/**
 *
 * @author Jani
 */
public class FutoshikiState extends AbstractState {

	private int board[][];
	private int [][] smallerThanOperators;
	private int rows;
	public FutoshikiState(int rows) {
		board = new int[rows][rows];
		smallerThanOperators = new int [rows][rows+rows];
		this.rows = rows;
		// test eksample
		
		
		smallerThanOperators[0][0] = -1;
		smallerThanOperators[1][1] = -1;
		smallerThanOperators[4][1] = -1;
		smallerThanOperators[2][4] = -1;
		smallerThanOperators[3][4] = 1;
		smallerThanOperators[0][5] = -1;
		smallerThanOperators[4][7] = 1;
		board =new int[][]{ 
				{5,5,5,5,5},
				{5,5,5,5,5},
				{5,5,5,5,5},
				{5,5,5,5,5},
				{5,5,5,5,5}
		};
		//solution
//		board =new int[][]{ 
//				{5,4,3,2,1},
//				{4,2,1,5,3},
//				{1,3,5,4,2},
//				{3,5,2,1,4},
//				{2,1,4,3,5}
//		};
//		smallerThanOperators[1][0] = 1;
//		smallerThanOperators[4][5] = -11;
//		smallerThanOperators[3][6] = 1;
//		smallerThanOperators[1][7] = 1;
//		smallerThanOperators[3][8] = -1;
//		board =new int[][]{ {2,4,1,5,3},
//				{1,5,3,2,4},
//				{3,2,5,4,1},
//				{4,3,2,1,5},
//				{5,1,4,3,2}
//		};

	}

	@Override
	public boolean solved() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < rows; j++) {
				if(board[j][i]==0)
					return false;
			}
		}
		//rows
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < rows; j++) {
				for (int j2 = j+1; j2 < rows; j2++) {
					if(board[j][i]==board[j2][i])
						return false;

				}
			}
		}
		//columns
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < rows; j++) {
				for (int j2 = j+1; j2 < rows; j2++) {
					if(board[i][j]==board[i][j2])
						return false;

				}
			}
		}
		//inequalities
//		System.out.println("in");
		for (int i = 0; i < rows+rows-1; i+=2) {
			//rows
			for (int j = 0; j < rows-1; j++) {
				if(smallerThanOperators[j][i]==0){

				}else if(smallerThanOperators[j][i]<0){
//					System.out.println("wat");
					if(board[j][i/2]<board[j+1][i/2])
						return false;
				}else{
//					System.out.println("wat2");
					if(board[j][i/2]>board[j+1][i/2])
						return false;
				}
			}
		}
		for (int i = 1; i < rows+rows-1; i+=2) {
			//columns
			for (int j = 0; j < rows; j++) {
				if(smallerThanOperators[j][i]==0){

				}else if(smallerThanOperators[j][i]<0){
//					System.out.println("wat3");
					if(board[i/2][i/2]<board[i/2][i/2+1])
						return false;
				}else{
//					System.out.println("wat4");
					if(board[i/2][i/2]>board[i/2][i/2+1])
						return false;
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
	/**
	 * chosen index is the id of the position where 0 would be row 0 column 0, and 6 would be row 1 column 2 if the board is 5x5
	 * chosen neightbeighbour is the nr to change to.
	 */
	public void change(int chosenIndex, int chosenNeighbour) {
		if(chosenIndex>rows*rows-1||chosenNeighbour>rows){
			System.err.println("Error the nr is to big");
			return;
		}
		int x = chosenIndex%rows, y = chosenIndex/rows;
		board[x][y] = chosenNeighbour;
//		System.out.println(x+":" + y);
				

	}

	@Override
	public AbstractState randomNeighbourState() {
		FutoshikiState returner = this.clone();
		int pos = (int) (Math.random()*rows*rows);
		int nr = (int) (Math.random()*rows)+1;
		returner.change(pos, nr);
		System.out.println(pos);
		System.out.println(nr);
		return returner;
		
	}
	public FutoshikiState clone(){
		FutoshikiState returner = new FutoshikiState(rows);
		//yes this is redicules, but nessesary
		for (int i = 0; i < board.length; i++) {
			returner.board[i] = this.board[i].clone();
			
		}
		returner.smallerThanOperators = this.smallerThanOperators;
		
		return returner;
	}

	@Override
	public void display() {
		String line;
		for (int i = 0; i < rows-1; i++) {
			line ="";
			for (int j = 0; j < rows-1; j++) {
				line += " " + board[j][i] + " ";
				if(smallerThanOperators[j][i*2]==0){
					line += "   ";				
				}else if(smallerThanOperators[j][i*2]<0){
					line += " > ";
				}else{
					line += " < ";
				}
			}
			System.out.println(line + board[rows-1][i]);
			line = "";
			for (int j = 0; j < smallerThanOperators.length; j++) {
				if(smallerThanOperators[j][i*2+1]==0){
					line += "      ";				
				}else if(smallerThanOperators[j][i*2+1]<0){
					line += "\\/    ";
				}else{
					line += "/\\    ";
				}
			}
			System.out.println(line);
		}
		line = "";
		for (int j = 0; j < rows-1; j++) {
			line += " " + board[j][rows-1] + " ";
			if(smallerThanOperators[j][rows+rows-2]==0){
				line += "   ";				
			}else if(smallerThanOperators[j][rows+rows-2]<0){
				line += " > ";
			}else{
				line += " < ";
			}
		}
		System.out.println(line + board[rows-1][rows-1]);

	}
	public static void main(String[] args) {
		FutoshikiState fs = new FutoshikiState(5);
		FutoshikiState fs2 = new FutoshikiState(5);
		for (int i = 0; i < 100; i++) {
			
			fs = (FutoshikiState) fs.randomNeighbourState();
		}
		fs.display();
		System.out.println(fs.solved());
	}

}
