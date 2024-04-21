package game.ui.board;

import game.core.Board;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BoardPanel extends JPanel {

    private static final Color[] colors = new Color[]{Color.decode("#9ff5e8"), Color.decode("#eebb88"), Color.decode("#f3d98c"), Color.decode("#9ff59b"), Color.decode("#f2d2d1"), Color.decode("#e886ea"), Color.decode("#869ff2"), Color.WHITE, Color.decode("#f0f890"),};

    public BoardPanel(Board board) {
        update(board);
    }

    public void update(Board board) {
        removeAll();
        setViewProperties(board);
        generateCells(board);
    }

    private void setViewProperties(Board board) {
        int size = board.getBoardSize().value;
        setLayout(new GridLayout(size, size));
        setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    public void generateCells(Board board) {
        removeAll();
        int size = board.getBoardSize().value;
        JLabel[][] gridCells = new JLabel[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JLabel cell = new JLabel(String.valueOf(board.getNumber(i, j)));
                cell.setSize(50, 50);
                cell.setHorizontalAlignment(JTextField.CENTER);
                cell.setOpaque(true);
                cell.setBackground(colors[board.getCell(i, j).getShapeNumber() - 1]);
                gridCells[i][j] = cell;
                add(cell);
            }
        }
    }
}
