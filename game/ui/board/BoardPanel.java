package game.ui.board;

import game.core.Board;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BoardPanel extends JPanel {

    public BoardPanel(Board board) {
        update(board);
    }

    public void update(Board board) {
        removeAll();
        int size = board.getBoardSize().value;
        setLayout(new GridLayout(size, size));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel[][] gridCells = new JLabel[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JLabel cell = new JLabel(String.valueOf(board.getNumber(i, j)));
                cell.setSize(50,50);
                cell.setHorizontalAlignment(JTextField.CENTER);
                gridCells[i][j] = cell;
                add(cell);
            }
        }
    }
}
