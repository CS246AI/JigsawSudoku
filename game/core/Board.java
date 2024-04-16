package game.core;

import game.generator.BoardGenerator;

public class Board {
    private int[][] board;
    private BoardSize boardSize;

    public Board() {
        this(BoardSize.SMALL);
    }

    public Board(BoardSize size) {
        resize(size);
    }

    public void resize(BoardSize size) {
        boardSize = size;
        board = BoardGenerator.generateBoard(size);
    }

    public void printBoard() {
        for (int i = 0; i < boardSize.value; i++) {
            for (int j = 0; j < boardSize.value; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public BoardSize getBoardSize() {
        return boardSize;
    }

    public int getNumber(int row, int col) {
        return board[row][col];
    }
}
