package queens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NqueensAlgorithm {
	private static final char EMPTY_MARK = '.';
	/**
	 * In some cases the algorithm gets stuck, so we need to shuffle the queens
	 * on the board. When the iterations limit is exceeded, the algorithm
	 * shuffle the queens, and starts again.
	 */
	private static final int ITERATIONS_LIMIT = 1_000_000;
	private static final char QUEEN_MARK = 'Q';

	private List<Queen> queens;
	private long iterations;
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

	public char[][] execute() {
		while (true) {
			if (foundSolution()) {
				break;
			}
			if (iterations++ >= ITERATIONS_LIMIT) {
				shuffle();
			}
			int randomColumnIndex = (int) (Math.random() * boardSize);
			setQueenToMinConflictPosition(queens.get(randomColumnIndex));
		}
		// The solution is found. Now generating the board from the queens.
		return generateBoard();
	}

	public long getIterations() {
		return iterations;
	}

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

	private boolean foundSolution() {
		for (Queen queen : queens) {
			if (numberOfConfilcts(queen) != 0) {
				return false;
			}
		}
		return true;

	}

	private void shuffle() {
		for (Queen queen : queens) {
			int randomColumnIndex = (int) (Math.random() * boardSize);
			queen.setY(randomColumnIndex);
		}
	}

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
		/* Check row and column */
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
