package nqueens;

import java.util.Arrays;

import org.junit.Test;

import queens.NqueensAlgorithm;

public class NqueensTest {

	private static final int BOARD_SIZE = 10;

	@Test
	public void testManualChecking() throws Exception {
		NqueensAlgorithm nqueensAlgorithm = new NqueensAlgorithm(
				createBoard(BOARD_SIZE));
		
		char[][] solution = nqueensAlgorithm.solve();
		for (char[] cs : solution) {
			System.out.println(Arrays.toString(cs));
		}
		System.out.println("Iterations to find the solution: "
				+ nqueensAlgorithm.getIterations());
	}

	private char[][] createBoard(int size) {
		char[][] board = new char[size][size];
		for (char[] cs : board) {
			Arrays.fill(cs, NqueensAlgorithm.EMPTY_MARK);
		}
		for (int i = 0; i < board.length; i++) {
			int randomColumnIndex = (int) (Math.random() * size);
			board[randomColumnIndex][i] = NqueensAlgorithm.QUEEN_MARK;
		}
		return board;

	}

}
