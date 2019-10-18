package src;

public class Sudoku {
	//defined grid to solve.
	public static int[][] GIVEN_GRID = {
	  		  {1,0,0,0,2,6,0,3,9},
	  		  {0,0,8,7,0,0,6,0,1},
	  		  {0,0,0,0,0,0,2,0,0},
	  		  {4,0,0,9,0,0,5,1,6},
	  		  {0,0,0,0,0,0,0,0,0},
	  		  {7,9,6,0,0,4,0,0,8},
	  		  {0,0,9,0,0,0,0,0,0},
	  		  {3,0,1,0,0,2,9,0,0},
	  		  {6,4,0,1,8,0,0,0,3},
	  	};
	
	private int[][] board;
	
	//empty cell
	public static final int Empty = 0; 
	
	//size of sudoku grid
	public static final int Size = 9;
	
	public Sudoku(int[][] board) {
		this.board = new int[Size][Size];
		
		for (int i = 0; i < Size; i++) {
			for (int j = 0; j < Size; j++) {
				this.board[i][j] = board[i][j];
			}
		}
	}
	
	//check number horizontally 
	private boolean isInRow(int Row, int Number) {
		for (int i = 0; i < Size; i++)
			if (board[Row][i] == Number)
				return true;
		
		return false;
	}
	
	//check number vertically 
	private boolean isInCol(int Col, int Number) {
		for (int i = 0; i < Size; i++)
			if (board[i][Col] == Number)
				return true;
			
		return false;
	}
	
	//check number in box
	private boolean isInBox(int Row, int Col, int Number) {
		int r = Row - Row % 3;
		int c = Col - Col % 3;
			
		for (int i = r; i < r + 3; i++)
			for (int j = c; j < c + 3; j++)
			if (board[i][j] == Number)
				return true;
					
		return false;
	}
	
	//method to check position
	private boolean isOK(int Row, int Col, int Number) {
		return !isInRow(Row, Number) && !isInCol(Col, Number) && !isInBox(Row, Col, Number);
	}
	
	//method solve
	public boolean solve() {
		for (int Row = 0; Row < Size; Row++) {
			for (int Col = 0; Col < Size; Col++) {
	
	//Search empty cell
				if (board[Row][Col] == Empty) {
					
	//Try numbers
					for (int Number = 1; Number <= Size; Number++) {
						if (isOK(Row, Col, Number)) {
							board[Row][Col] = Number;
							
							if (solve()) {
								return true;
							} else {
								board[Row][Col] = Empty;
							}
						}
					}
					
					return false;
				}
			}
		}
		
		return true;
	//Solved
		
	}
	
	//method display
	public void display() {
		for (int i = 0; i < Size; i++) {
			for (int j = 0; j < Size; j++) {
				System.out.print(" " + board[i][j]);
			}
		
		System.out.println();
		
		}
		
		System.out.println();
	}
	
	//method Main
	public static void main(String[] args) {
		Sudoku sudoku = new Sudoku(GIVEN_GRID);
		System.out.println("grid:");
		sudoku.display();
		
		if (sudoku.solve()) {
			System.out.println("sudoku solved:");
			sudoku.display();
		} else {
			System.out.println("sudoku can not be solved");
		}
	}
}
