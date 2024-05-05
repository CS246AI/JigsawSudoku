package game.ui.controls;

import game.core.BoardSize;
import game.core.GameDifficulty;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ControlPanel extends JPanel {

    JButton geneticAlgorithm;
    JTextField generations;
    JLabel generationsLabel;
    JTextField populationSize;
    JLabel populationSizeLabel;
    JTextField mutationRate;
    JLabel mutationRateLabel;
    JTextField tournamentSize;
    JLabel tournamentSizeLabel;

    JButton stochasticHillClimbing;
    JTextField maxIterations;
    JLabel maxIterationsLabel;

    JButton firstChoiceHillClimbing;

    JButton randomRestartHillClimbing;
    JTextField numberOfRestarts;
    JLabel numberOfRestartsLabel;

    JButton simulatedAnnealing;
    JTextField temperature;
    JLabel temperatureLabel;
    JTextField coolingRate;
    JLabel coolingRateLabel;

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
        geneticAlgorithm.addActionListener(e -> solverRunners.runGeneticAlgorithm(
                Integer.parseInt(generations.getText()),
                Integer.parseInt(populationSize.getText()),
                Double.parseDouble(mutationRate.getText()),
                Integer.parseInt(tournamentSize.getText())
        ));

        generationsLabel = new JLabel("Generations Rate");
        generationsLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        generations = new JTextField("100");
        generations.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        populationSizeLabel = new JLabel("Population Size");
        populationSizeLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        populationSize = new JTextField("100");
        populationSize.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        mutationRateLabel = new JLabel("Mutation Rate");
        mutationRateLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        mutationRate = new JTextField("0.5");
        mutationRate.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        tournamentSizeLabel = new JLabel("Tournament Size");
        tournamentSizeLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        tournamentSize = new JTextField("5");
        tournamentSize.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        stochasticHillClimbing = new JButton("Stochastic Hill Climbing");
        stochasticHillClimbing.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        stochasticHillClimbing.addActionListener(e -> solverRunners.runStochasticHillClimbing(
                Integer.parseInt(maxIterations.getText())
        ));
        maxIterationsLabel = new JLabel("Max Iterations");
        maxIterationsLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        maxIterations = new JTextField("100");
        maxIterations.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        firstChoiceHillClimbing = new JButton("First Choice Hill Climbing");
        firstChoiceHillClimbing.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        firstChoiceHillClimbing.addActionListener(e -> solverRunners.runFirstChoiceHillClimbing());

        randomRestartHillClimbing = new JButton("Random Restart Hill Climbing");
        randomRestartHillClimbing.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        randomRestartHillClimbing.addActionListener(e -> solverRunners.runRandomRestartHillClimbing(
                Integer.parseInt(numberOfRestarts.getText())
        ));
        numberOfRestartsLabel = new JLabel("Number of Restarts");
        numberOfRestartsLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        numberOfRestarts = new JTextField("5");
        numberOfRestarts.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        simulatedAnnealing = new JButton("Simulated Annealing");
        simulatedAnnealing.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        simulatedAnnealing.addActionListener(e -> solverRunners.runSimulatedAnnealing(
                Integer.parseInt(temperature.getText()),
                Double.parseDouble(coolingRate.getText())
        ));

        temperatureLabel = new JLabel("Temperature");
        temperatureLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        temperature = new JTextField("1000");
        temperature.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        coolingRateLabel = new JLabel("Cooling Rate");
        coolingRateLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        coolingRate = new JTextField("0.95");
        coolingRate.setAlignmentX(JComponent.CENTER_ALIGNMENT);

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
        add(generationsLabel);
        add(generations);
        add(populationSizeLabel);
        add(populationSize);
        add(mutationRateLabel);
        add(mutationRate);
        add(tournamentSizeLabel);
        add(tournamentSize);
        add(Box.createVerticalGlue());
        add(stochasticHillClimbing);
        add(maxIterationsLabel);
        add(maxIterations);
        add(firstChoiceHillClimbing);
        add(randomRestartHillClimbing);
        add(numberOfRestartsLabel);
        add(numberOfRestarts);
        add(Box.createVerticalGlue());
        add(simulatedAnnealing);
        add(temperatureLabel);
        add(temperature);
        add(coolingRateLabel);
        add(coolingRate);
        add(Box.createVerticalGlue());
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
        void runGeneticAlgorithm(int generations, int populationSize, double mutationRate, int tournamentSize);

        void runStochasticHillClimbing(int maxIterationsWithoutImprovement);

        void runFirstChoiceHillClimbing();

        void runRandomRestartHillClimbing(int numberOfRestarts);

        void runSimulatedAnnealing(double temperature, double coolingRate);

        void runCSP_Backtracking(boolean MRV, boolean LCV, boolean forwardChecking);
    }
}
