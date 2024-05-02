package game.ui;

import game.core.Board;
import game.core.BoardSize;
import game.core.GameDifficulty;
import game.solver.BacktrackingSolver;
import game.solver.CSP;
import game.ui.board.BoardPanel;
import game.ui.controls.DifficultyPanel;
import game.ui.controls.SizePanel;
import game.ui.controls.ControlPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements ControlPanel.ControlUpdater {

    BoardPanel boardPanel;
    ControlPanel control;

    public MainFrame(Board board) {
        setTitle("Sudoku Board");
        setLayout(new BorderLayout());
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boardPanel = new BoardPanel(board);
        control = new ControlPanel(
                board.getBoardSize(),
                board.getGameDifficulty(),
                getSolverRunners(board, boardPanel, this),
                getSizeChangeInterface(board, boardPanel),
                getDifficultyChangeInterface(board, boardPanel)
        );
        add(control, BorderLayout.WEST);
        add(boardPanel, BorderLayout.CENTER);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void update(long time) {
        control.update(time);
    }

    public interface BoardUpdater {
        void update(Board board);
    }

    private ControlPanel.SolverRunner getSolverRunners(Board board, BoardUpdater boardUpdater, ControlPanel.ControlUpdater controlUpdater) {
        return new ControlPanel.SolverRunner() {

            @Override
            public void runGeneticAlgorithm() {

            }

            @Override
            public void runSimulatedAnnealing() {

            }

            @Override
            public void runCSP_Backtracking(boolean MRV, boolean LCV, boolean forwardChecking) {
                CSP csp = new CSP(MRV, LCV, forwardChecking);
                long start = System.nanoTime();
                csp.solve(board, boardUpdater);
                board.printBoard();
                long end = System.nanoTime();
                controlUpdater.update(end - start);
            }
        };
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