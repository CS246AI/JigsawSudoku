package game.ui;

import game.core.Board;
import game.core.BoardSize;
import game.ui.board.BoardPanel;
import game.ui.controls.DifficultyPanel;
import game.ui.controls.SizePanel;
import game.ui.controls.ControlPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame(Board board) {
        setTitle("Sudoku Board");
        setLayout(new BorderLayout());
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BoardPanel boardPanel = new BoardPanel(board);
        ControlPanel control = new ControlPanel(new SizePanel.SizeChangeInterface() {
            @Override
            public void onSmallSizeClick() {
                if (board.getBoardSize() == BoardSize.SMALL) return;
                board.resize(BoardSize.SMALL);
                boardPanel.update(board);
                boardPanel.revalidate();
                boardPanel.repaint();
            }

            @Override
            public void onMediumSizeClick() {
                if (board.getBoardSize() == BoardSize.MEDIUM) return;
                board.resize(BoardSize.MEDIUM);
                boardPanel.update(board);
                boardPanel.revalidate();
                boardPanel.repaint();
            }

            @Override
            public void onBigSizeClick() {
                if (board.getBoardSize() == BoardSize.BIG) return;
                board.resize(BoardSize.BIG);
                boardPanel.update(board);
                boardPanel.revalidate();
                boardPanel.repaint();
            }
        }, new DifficultyPanel.DifficultyChangeInterface() {
            @Override
            public void onEasyClick() {

            }

            @Override
            public void onMediumClick() {

            }

            @Override
            public void onHardClick() {

            }
        });
        add(control, BorderLayout.WEST);
        add(boardPanel, BorderLayout.CENTER);
        setResizable(false);
        setVisible(true);
    }
}