package game.ui;

import game.core.Board;
import game.core.BoardSize;
import game.core.GameDifficulty;
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
        ControlPanel control = new ControlPanel(board.getBoardSize(), getSizeChangeInterface(board, boardPanel), board.getGameDifficulty(), getDifficultyChangeInterface(board, boardPanel));
        add(control, BorderLayout.WEST);
        add(boardPanel, BorderLayout.CENTER);
        setResizable(false);
        setVisible(true);
    }

    private SizePanel.SizeChangeInterface getSizeChangeInterface(Board board, BoardPanel boardPanel) {
        return new SizePanel.SizeChangeInterface() {
            @Override
            public void onSmallSizeClick() {
                if (board.getBoardSize() == BoardSize.SMALL) return;
                board.resize(BoardSize.SMALL, board.getGameDifficulty());
                boardPanel.resize(board);
            }

            @Override
            public void onMediumSizeClick() {
                if (board.getBoardSize() == BoardSize.MEDIUM) return;
                board.resize(BoardSize.MEDIUM, board.getGameDifficulty());
                boardPanel.resize(board);
            }

            @Override
            public void onBigSizeClick() {
                if (board.getBoardSize() == BoardSize.BIG) return;
                board.resize(BoardSize.BIG, board.getGameDifficulty());
                boardPanel.resize(board);
            }
        };
    }

    private DifficultyPanel.DifficultyChangeInterface getDifficultyChangeInterface(Board board, BoardPanel boardPanel) {
        return new DifficultyPanel.DifficultyChangeInterface() {

            @Override
            public void onEasyClick() {
                if (board.getGameDifficulty() == GameDifficulty.EASY) return;
                board.resize(board.getBoardSize(), GameDifficulty.EASY);
                boardPanel.resize(board);
            }

            @Override
            public void onMediumClick() {
                if (board.getGameDifficulty() == GameDifficulty.MEDIUM) return;
                board.resize(board.getBoardSize(), GameDifficulty.MEDIUM);
                boardPanel.resize(board);
            }

            @Override
            public void onHardClick() {
                if (board.getGameDifficulty() == GameDifficulty.HARD) return;
                board.resize(board.getBoardSize(), GameDifficulty.HARD);
                boardPanel.resize(board);
            }
        };
    }
}