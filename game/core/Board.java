package game.core;

import game.generator.BoardGenerator;

import java.util.ArrayList;

public class Board {
    private ArrayList<ArrayList<Integer>> board;
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
                System.out.print(board.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    public BoardSize getBoardSize() {
        return boardSize;
    }

    public int getNumber(int row, int col) {
        return board.get(row).get(col);
    }
}
