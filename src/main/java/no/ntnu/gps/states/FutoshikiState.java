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
 * @author Jan Tore Stølsvik & Tom Glover 
 */
public class FutoshikiState extends AbstractState {
	private int conflikts [][];
	private int board[][];
	private int [][] inequalitiesRows;
	private int [][] inequalitiesColumns;
	private int rows;
	public FutoshikiState(int rows) {
		board = new int[rows][rows];
		inequalitiesRows = new int [rows-1][rows];
		inequalitiesColumns = new int [rows][rows-1];
		conflikts = new int [rows][rows];
		this.rows = rows;
		// test eksample


		//		inequalitiesRows[0][0] = -1;
		//		inequalitiesColumns[1][0] = -1;
		//		inequalitiesRows[3][4] = -1;
		for (int i = 0; i < rows-2; i++) {
			inequalitiesRows[(int) (Math.random()*(rows-1))][(int) (Math.random()*rows)] = (int)(Math.random()*3)-1;
			inequalitiesColumns[(int) (Math.random()*(rows))][(int) (Math.random()*(rows-1))] = (int)(Math.random()*3)-1;
		}
		//		inequalitiesColumns[4][3] = 1;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < rows; j++) {
				board[i][j]=rows;
			}

		}
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

		arrangeConflikts();
		//		display();
	}
	private void arrangeConflikts(){
		conflikts = new int [rows][rows];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < rows; j++) {
				for (int j2 = j+1; j2 < rows; j2++) {
					if(board[j][i]==board[j2][i]){
						conflikts[j][i]++;
						conflikts[j2][i]++;
					}
				}
			}
		}
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < rows; j++) {
				for (int j2 = j+1; j2 < rows; j2++) {
					if(board[i][j]==board[i][j2]){
						conflikts[i][j]++;
						conflikts[i][j2]++;
					}

				}
			}
		}

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < rows-1; j++) {
				if(inequalitiesRows[j][i]!=0){
					if(inequalitiesRows[j][i]<0){
						if(board[j][i]<=board[j+1][i]){
							conflikts[j][i]+=2;
							conflikts[j+1][i]+=2;
						}
					}else{
						if(board[j][i]>=board[j+1][i]){
							conflikts[j][i]+=2;
							conflikts[j+1][i]+=2;
						}
					}

				}
			}

		}
		for (int i = 0; i < rows-1; i++) {
			for (int j = 0; j < rows; j++) {
				if(inequalitiesColumns[j][i]!=0){
					if(inequalitiesColumns[j][i]<0){
						if(board[j][i]<=board[j][i+1]){
							conflikts[j][i]+=2;
							conflikts[j][i+1]+=2;
						}
					}else{
						if(board[j][i]>=board[j][i+1]){
							conflikts[j][i]+=2;
							conflikts[j][i+1]+=2;
						}
					}
				}
			}
		}
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
		//inequ. rows
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < rows-1; j++) {
				if(inequalitiesRows[j][i]!=0){
					if(inequalitiesRows[j][i]<0){
						if(board[j][i]<=board[j+1][i]){
							return false;
						}
					}else{
						if(board[j][i]>=board[j+1][i]){
							return false;
						}
					}

				}
			}

		}
		for (int i = 0; i < rows-1; i++) {
			for (int j = 0; j < rows; j++) {
				if(inequalitiesColumns[j][i]!=0){
					if(inequalitiesColumns[j][i]<0){
						if(board[j][i]<=board[j][i+1]){
							return false;
						}
					}else{
						if(board[j][i]>=board[j][i+1]){
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	@Override
	public int evaluation() {
		if(this.solved()){
			return Integer.MAX_VALUE;
		}
		int returner =0;
		//		arrangeConflikts();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < rows; j++) {
				returner += conflikts[i][j];
			}
		}
		//		this.display();
		return	100-returner;
	}

	@Override
	public List<Integer> conflictedStates() {
		List<Integer> returner = new ArrayList<Integer>();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < rows; j++) {
				if(conflikts[i][j]!=0){
					returner.add(j*rows+i);
				}
			}

		}
		return returner;
	}

	@Override
	public List<Integer> leastConflictedNeighbours(int chosenIndex) {
		int max = Integer.MIN_VALUE;//because im useing the eval fonction
		List<Integer> list = new ArrayList<Integer>();
		int [] tempConflicts = new int[rows+1];
		FutoshikiState temp;
		for (int i = 1; i <= rows; i++) {
			temp = this.clone();
			temp.change(chosenIndex, i);
			tempConflicts[i]= temp.evaluation();
			if(max<tempConflicts[i]){
				max=tempConflicts[i];
			}

		}
		for (int i = 1; i < tempConflicts.length; i++) {
			if(tempConflicts[i]==max){
				list.add(i);
			}
		}
		int x = chosenIndex%rows, y = chosenIndex/rows;
		if(list.contains(board[x][y])&&list.size()!=1){
			list.remove(list.indexOf(board[x][y]));
		}
		return list;
	}
	@Override
	/**
	 * chosen index is the id of the position where 0 would be row 0 column 0, and 6 would be row 1 column 2 if the board is 5x5
	 * chosen neightbeighbour is the nr to change to.
	 */
	public void change(int chosenIndex, int chosenNeighbour) {
		if(chosenIndex>rows*rows-1||chosenNeighbour>rows){
			System.err.println("Error the nr is to big");
			System.err.println(chosenIndex+ ":"+ chosenNeighbour);
			return;
		}
		int x = chosenIndex%rows, y = chosenIndex/rows;
		//Conflicts removes and adds //May Deus machina have mercy on my soul.
		for (int i = 0; i < rows; i++) {
			if(board[x][y]==board[x][i]&&i!=y){
				conflikts[x][y]--;
				conflikts[x][i]--;
			}
			if(board[x][y]==board[i][y]&&i!=x){
				conflikts[x][y]--;
				conflikts[i][y]--;
			}
			if(chosenNeighbour==board[i][y]&&i!=x){
				conflikts[x][y]++;
				conflikts[i][y]++;
			}
			if(chosenNeighbour==board[x][i]&&i!=y){
				conflikts[x][y]++;
				conflikts[x][i]++;
			}
		}
		if(x!=0){
			if(inequalitiesRows[x-1][y]!=0){
				if(inequalitiesRows[x-1][y]<0){
					if(board[x-1][y]<=board[x][y]){
						conflikts[x-1][y]-=2;
						conflikts[x][y]-=2;
					}
					if(board[x-1][y]<=chosenNeighbour){
						conflikts[x-1][y]+=2;
						conflikts[x][y]+=2;
					}
				}
				else{
					if(board[x-1][y]>=board[x][y]){
						conflikts[x-1][y]-=2;
						conflikts[x][y]-=2;
					}
					if(board[x-1][y]>=chosenNeighbour){
						conflikts[x-1][y]+=2;
						conflikts[x][y]+=2;
					}
				}
			}
		}
		if(x!=rows-1){
			if(inequalitiesRows[x][y]!=0){
				if(inequalitiesRows[x][y]<0){
					if(board[x+1][y]>=board[x][y]){
						conflikts[x+1][y]-=2;
						conflikts[x][y]-=2;
					}
					if(board[x+1][y]>=chosenNeighbour){
						conflikts[x+1][y]+=2;
						conflikts[x][y]+=2;
					}
				}
				else{
					if(board[x+1][y]<=board[x][y]){
						conflikts[x+1][y]-=2;
						conflikts[x][y]-=2;
					}
					if(board[x+1][y]<=chosenNeighbour){
						conflikts[x+1][y]+=2;
						conflikts[x][y]+=2;
					}
				}
			}
		}

		if(y!=0){
			if(inequalitiesColumns[x][y-1]!=0){
				if(inequalitiesColumns[x][y-1]<0){
					if(board[x][y-1]<=board[x][y]){
						conflikts[x][y-1]-=2;
						conflikts[x][y]-=2;
					}
					if(board[x][y-1]<=chosenNeighbour){
						conflikts[x][y-1]+=2;
						conflikts[x][y]+=2;
					}
				}
				if(inequalitiesColumns[x][y-1]>0){
					if(board[x][y-1]>=board[x][y]){
						conflikts[x][y-1]-=2;
						conflikts[x][y]-=2;
					}
					if(board[x][y-1]>=chosenNeighbour){
						conflikts[x][y-1]+=2;
						conflikts[x][y]+=2;
					}
				}
			}
		}
		if(y!=rows-1){
			if(inequalitiesColumns[x][y]!=0){
				if(inequalitiesColumns[x][y]<0){
					if(board[x][y+1]>=board[x][y]){
						conflikts[x][y+1]-=2;
						conflikts[x][y]-=2;
					}
					if(board[x][y+1]>=chosenNeighbour){
						conflikts[x][y+1]+=2;
						conflikts[x][y]+=2;
					}
				}
				if(inequalitiesColumns[x][y]>0){
					if(board[x][y+1]<=board[x][y]){
						conflikts[x][y+1]-=2;
						conflikts[x][y]-=2;
					}
					if(board[x][y+1]<=chosenNeighbour){
						conflikts[x][y+1]+=2;
						conflikts[x][y]+=2;
					}
				}

			}
		}


		board[x][y] = chosenNeighbour;
	}

	@Override
	public AbstractState randomNeighbourState() {
		FutoshikiState returner = this.clone();
		int pos = (int) (Math.random()*rows*rows);
		int nr = (int) (Math.random()*rows)+1;
		returner.change(pos, nr);
		//		System.out.println(pos);
		//		System.out.println(nr);
		return returner;

	}
	public FutoshikiState clone(){
		FutoshikiState returner = new FutoshikiState(rows);
		//yes this is redicules, but nessesary
		for (int i = 0; i < board.length; i++) {
			returner.board[i] = this.board[i].clone();

		}
		for (int i = 0; i < conflikts.length; i++) {
			returner.conflikts[i] = this.conflikts[i].clone();

		}
		returner.inequalitiesRows = this.inequalitiesRows;
		returner.inequalitiesColumns = this.inequalitiesColumns;

		return returner;
	}

	@Override
	public void display() {
		String line;
		for (int i = 0; i < rows-1; i++) {
			line ="";
			for (int j = 0; j < rows-1; j++) {
				line += " " + board[j][i] + " ";
				if(inequalitiesRows[j][i]==0){
					line += "   ";				
				}else if(inequalitiesRows[j][i]<0){
					line += " > ";
				}else{
					line += " < ";
				}
			}
			System.out.println(line + board[rows-1][i]);
			line = "";
			for (int j = 0; j < rows; j++) {
				if(inequalitiesColumns[j][i]==0){
					line += "      ";				
				}else if(inequalitiesColumns[j][i]<0){
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
			if(inequalitiesRows[j][rows-1]==0){
				line += "   ";				
			}else if(inequalitiesRows[j][rows-1]<0){
				line += " > ";
			}else{
				line += " < ";
			}
		}
		System.out.println(line + board[rows-1][rows-1]);

		//conflicts print
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < rows; j++) {
				System.out.print(conflikts[j][i]+ " ");
			}
			System.out.println();
		}

	}
	//	public static void main(String[] args) {
	//		FutoshikiState fs = new FutoshikiState(5);
	//		FutoshikiState fs2 = new FutoshikiState(5);
	//		for (int i = 0; i < 100; i++) {
	//
	//			fs = (FutoshikiState) fs.randomNeighbourState();
	//		}
	//		fs.display();
	//		fs.arrangeConflikts();
	//		fs.display();
	//		System.out.println(fs.solved());
	//	}

}
