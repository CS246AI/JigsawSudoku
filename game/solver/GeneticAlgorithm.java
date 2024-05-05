package game.solver;

import game.core.Board;
import game.core.Cell;
import game.ui.MainFrame;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GeneticAlgorithm extends Heuristic {

    private int populationSize;
    private double mutationRate;
    private int tournamentSize;
    private ArrayList<Board> population = new ArrayList<>();

    public GeneticAlgorithm(Board board, int populationSize, double mutationRate, int tournamentSize) {
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.tournamentSize = tournamentSize;
        initializePopulation(board);
    }

    private void initializePopulation(Board board) {
        for (int i = 0; i < populationSize; i++) {
            Board newBoard = new Board(board);
            for (ArrayList<Cell> shape : board.getAllShapes()) {
                ArrayList<Integer> numbers = IntStream.rangeClosed(1, board.getBoardSize().value)
                        .boxed()
                        .collect(Collectors.toCollection(ArrayList::new));
                for (Cell cell : shape) {
                    if (newBoard.getNumber(cell.getRow(), cell.getCol()) == 0) {
                        int num = numbers.get(new Random().nextInt(numbers.size()));
                        newBoard.getCell(cell.getRow(), cell.getCol()).setValue(num);
                        numbers.remove((Integer) num);
                    }
                }
            }
            population.add(newBoard);
        }
    }

    public void solve(Board board, MainFrame.BoardUpdater boardUpdater, int generations) {
        for (int generation = 0; generation < generations; generation++) {
            ArrayList<Board> newPopulation = new ArrayList<>();
            while (newPopulation.size() < populationSize) {
                Board parent1 = tournamentSelection();
                Board parent2 = tournamentSelection();
                Board child = crossover(board, parent1, parent2);
                mutate(child, boardUpdater);
                newPopulation.add(child);
            }
            population = newPopulation;
        }
    }

    private Board tournamentSelection() {
        ArrayList<Board> tournament = new ArrayList<>();
        for (int i = 0; i < tournamentSize; i++) {
            tournament.add(population.get(new Random().nextInt(population.size())));
        }
        return tournament.stream().min(Comparator.comparingInt(this::computeHeuristic)).orElseThrow();
    }

    private Board crossover(Board initial, Board parent1, Board parent2) {
        Board child = new Board(initial);
        for (int i = 0; i < initial.getBoardSize().value; i++) {
            for (int j = 0; j < initial.getBoardSize().value; j++) {
                if (new Random().nextDouble() > 0.5) {
                    child.getCell(i, j).setValue(parent1.getCell(i, j).getValue());
                } else {
                    child.getCell(i, j).setValue(parent2.getCell(i, j).getValue());
                }
            }
        }
        return child;
    }

    private void mutate(Board board, MainFrame.BoardUpdater boardUpdater) {
        for (int i = 0; i < board.getBoardSize().value; i++) {
            for (int j = 0; j < board.getBoardSize().value; j++) {
                if (new Random().nextDouble() < mutationRate) {

                    ArrayList<Cell> shape = null;
                    for (ArrayList<Cell> cells : board.getAllShapes()) {
                        if (cells.getFirst().getShapeNumber() == board.getCell(i, j).getShapeNumber()) {
                            shape = cells;
                            break;
                        }
                    }

                    Set<Integer> usedNumbers = new HashSet<>();
                    usedNumbers.addAll(getRowValues(board, i));
                    usedNumbers.addAll(getColumnValues(board, j));
                    assert shape != null;
                    usedNumbers.addAll(getShapeValues(shape));

                    ArrayList<Integer> possibleValues = IntStream.rangeClosed(1, board.getBoardSize().value)
                            .boxed()
                            .filter(num -> !usedNumbers.contains(num))
                            .collect(Collectors.toCollection(ArrayList::new));
                    if (!possibleValues.isEmpty()) {
                        board.getCell(i, j).setValue(possibleValues.get(new Random().nextInt(possibleValues.size())));
                        boardUpdater.update(board);
                    }
                }
            }
        }
    }
}
