package game.generator;

import game.core.BoardSize;

import java.util.ArrayList;
import java.util.Collections;

public class BoardGenerator {

    public static ArrayList<ArrayList<Integer>> generateBoard(BoardSize size) {
        ArrayList<ArrayList<Integer>> board = new ArrayList<>();
        board.add(generateRow(size));
        for (int i = 1; i < size.value; i++) {
            board.add(shift((ArrayList<Integer>) board.get(i - 1).clone()));
        }
        Collections.shuffle(board);
        return board;
    }

    private static ArrayList<Integer> generateRow(BoardSize size) {
        ArrayList<Integer> row = generateArrayList(size.value);
        Collections.shuffle(row);
        return row;
    }

    private static ArrayList<Integer> generateArrayList(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        return list;
    }

    private static ArrayList<Integer> shift(ArrayList<Integer> previous) {
        ArrayList<Integer> row = new ArrayList<>();
        row.add(previous.removeLast());
        row.addAll(previous);
        return row;
    }
}
