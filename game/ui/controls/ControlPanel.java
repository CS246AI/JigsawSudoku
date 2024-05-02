package game.ui.controls;

import game.core.BoardSize;
import game.core.GameDifficulty;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ControlPanel extends JPanel {

    JButton geneticAlgorithm;
    JButton simulatedAnnealing;
    JButton csp;
    JCheckBox mrv;
    JCheckBox lcv;
    JCheckBox forwardChecking;
    SizePanel sizePanel;
    DifficultyPanel difficultyPanel;
    JLabel timeLabel;

    public ControlPanel(
            BoardSize boardSize,
            GameDifficulty gameDifficulty,
            SolverRunner solverRunners,
            SizePanel.SizeChangeInterface sizeChangeInterface,
            DifficultyPanel.DifficultyChangeInterface difficultyChangeInterface
    ) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setAlignmentX(JComponent.CENTER_ALIGNMENT);

        geneticAlgorithm = new JButton("Genetic Algorithm");
        geneticAlgorithm.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        simulatedAnnealing = new JButton("Simulated Annealing");
        simulatedAnnealing.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        csp = new JButton("CSP Backtracking");
        csp.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        csp.addActionListener(e -> solverRunners.runCSP_Backtracking(mrv.isSelected(), lcv.isSelected(), forwardChecking.isSelected()));

        mrv = new JCheckBox("MRV");
        mrv.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        lcv = new JCheckBox("LCV");
        lcv.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        forwardChecking = new JCheckBox("Forward Checking");
        forwardChecking.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        sizePanel = new SizePanel(boardSize, sizeChangeInterface);
        sizePanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        difficultyPanel = new DifficultyPanel(gameDifficulty, difficultyChangeInterface);
        difficultyPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        timeLabel = new JLabel();
        timeLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        add(Box.createVerticalGlue());
        add(geneticAlgorithm);
        add(simulatedAnnealing);
        add(csp);
        add(mrv);
        add(lcv);
        add(forwardChecking);
        add(Box.createVerticalGlue());
        add(sizePanel);
        add(Box.createVerticalGlue());
        add(difficultyPanel);
        add(Box.createVerticalGlue());
        add(timeLabel);
        add(Box.createVerticalGlue());
    }

    public void update(long time) {
        timeLabel.setText("Time: " + time + " nanoseconds");
        timeLabel.invalidate();
        timeLabel.repaint();
    }

    public interface ControlUpdater {
        void update(long time);
    }

    public interface SolverRunner {
        void runGeneticAlgorithm();

        void runSimulatedAnnealing();

        void runCSP_Backtracking(boolean MRV, boolean LCV, boolean forwardChecking);
    }
}
