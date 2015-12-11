package queens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class NqueensAlgorithm {

	public static final char EMPTY_MARK = '.';
	public static final char QUEEN_MARK = 'Q';
	/**
	 * In some cases the algorithm gets stuck, so we need to shuffle the queens
	 * on the board. When the <b>ITERATIONS_LIMIT</b> is exceeded, the algorithm
	 * shuffle the queens, and starts again.
	 */
	private static final int ITERATIONS_LIMIT = 100_000;

	/**
	 * Represents the queens on the board.(their positions)
	 */
	private List<Queen> queens;

	private long iterations;// iterations during the execution

	/**
	 * The size of the queens list. It is extracted as a new field for
	 * performance reasons.
	 */
	private int boardSize;

	public NqueensAlgorithm(char[][] board) {
		queens = new ArrayList<Queen>();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[j][i] == QUEEN_MARK) {
					queens.add(new Queen(i, j));
				}
			}
		}
		boardSize = queens.size();
	}

	/**
	 * Finds a solution.
	 * 
	 * @return
	 */
	public char[][] solve() {
		Random random = new Random();
		while (true) {
			if (foundSolution()) {
				break;
			}
			if (iterations++ >= ITERATIONS_LIMIT) {
				iterations = 0;
				shuffle();
			}
			setQueenToMinConflictPosition(queens.get(random.nextInt(boardSize)));
		}
		// The solution is found. Now generating the board from the queens.
		return generateBoard();
	}

	public long getIterations() {
		return iterations;
	}

	/**
	 * The total number of conflicts between <b>currentQueen</b> and all other
	 * queens.
	 * 
	 * @param currentQueen
	 * @return
	 */
	private int numberOfConfilcts(Queen currentQueen) {
		int conflicts = 0;
		for (Queen queen : queens) {
			if (currentQueen.isInConfilct(queen)) {
				conflicts++;
			}
		}
		/*
		 * Every queen is in conflict with herself, so actual
		 * conflicts==conflicts-1
		 */
		return --conflicts;
	}

	/**
	 * Check if there are no conflicts between the queens on the board.
	 * 
	 * @return
	 */
	private boolean foundSolution() {
		for (Queen queen : queens) {
			if (numberOfConfilcts(queen) != 0) {
				return false;
			}
		}
		return true;

	}

	/**
	 * The state of the boards sometimes gets stuck and produces the same
	 * positions. So this function "restart" the board.(place all queens
	 * randomly - but one per a column)
	 */
	private void shuffle() {
		for (Queen queen : queens) {
			int randomColumnIndex = (int) (Math.random() * boardSize);
			queen.setY(randomColumnIndex);
		}
	}

	/**
	 * Generates the board after we know the queens positions.
	 * 
	 * @return
	 */
	private char[][] generateBoard() {
		char[][] board = new char[boardSize][boardSize];
		for (char[] cs : board) {
			Arrays.fill(cs, EMPTY_MARK);
		}
		for (Queen queen : queens) {
			board[queen.getX()][queen.getY()] = QUEEN_MARK;
		}
		return board;
	}

	/**
	 * Finds the best position of a queen in her column.
	 * 
	 * @param queen
	 */
	private void setQueenToMinConflictPosition(Queen queen) {
		int minConflicts = numberOfConfilcts(queen);
		int minConflictPositionY = 0;
		for (int i = 0; i < boardSize; i++) {
			queen.setY(i);
			int possibleMin = numberOfConfilcts(queen);
			if (possibleMin <= minConflicts) {
				minConflicts = possibleMin;
				minConflictPositionY = i;
			}
		}
		queen.setY(minConflictPositionY);
	}
}

class Queen {
	private int x;
	private int y;

	public Queen(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean isInConfilct(Queen queen) {
		/*
		 * Check row and column(actually in my case the column check is not
		 * necessary since we start with one queen per column)
		 */
		if (x == queen.getX() || y == queen.getY()) {
			return true;
		}
		/* Check diagonal */
		int deltaRow = Math.abs(x - queen.getX());
		int deltaColumn = Math.abs(y - queen.getY());
		return deltaRow == deltaColumn;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return String.format("Queen [x=%s, y=%s]", x, y);
	}

}
