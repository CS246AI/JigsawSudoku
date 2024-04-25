package game.ui.board;

import game.core.Board;
import game.core.BoardSize;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BoardPanel extends JPanel {

    private static final Color[] colors = new Color[]{Color.decode("#9ff5e8"), Color.decode("#eebb88"), Color.decode("#f3d98c"), Color.decode("#9ff59b"), Color.decode("#f2d2d1"), Color.decode("#e886ea"), Color.decode("#869ff2"), Color.WHITE, Color.decode("#f0f890"),};
    JLabel[][] gridCells;

    public BoardPanel(Board board) {
        resize(board);
    }

    public void resize(Board board) {
        removeAll();
        setViewProperties(board);
        generateCells(board);
        revalidate();
        repaint();
    }

    public void update(Board board) {
        int size = board.getBoardSize().value;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int num = board.getNumber(i, j);
                if (num != -1) {
                    gridCells[i][j].setText(String.valueOf(num));
                }
            }
        }
        revalidate();
        repaint();
    }

    private void setViewProperties(Board board) {
        int size = board.getBoardSize().value;
        setLayout(new GridLayout(size, size));
        setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    public void generateCells(Board board) {
        removeAll();
        int size = board.getBoardSize().value;
        gridCells = new JLabel[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JLabel cell;
                if (board.getNumber(i, j) != -1) {
                    cell = new JLabel(String.valueOf(board.getNumber(i, j)));
                } else {
                    cell = new JLabel();
                }
                cell.setFont(setCellFont(board.getBoardSize(), cell.getFont()));
                cell.setSize(50, 50);
                cell.setHorizontalAlignment(JTextField.CENTER);
                cell.setOpaque(true);
                cell.setBackground(colors[board.getCell(i, j).getShapeNumber() - 1]);
                gridCells[i][j] = cell;
                add(cell);
            }
        }
    }

    private Font setCellFont(BoardSize boardSize, Font font) {
        switch (boardSize) {
            case SMALL -> {
                return new Font(font.getFontName(), Font.PLAIN, 24);
            }
            case MEDIUM -> {
                return new Font(font.getFontName(), Font.PLAIN, 18);
            }
            case BIG -> {
                return new Font(font.getFontName(), Font.PLAIN, 14);
            }
        }
        return null;
    }
}
