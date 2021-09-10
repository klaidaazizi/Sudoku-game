package Controller;

import Model.SudokuModel;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.WeakHashMap;

public class SudokuControllerConsole implements SudokuController {
    private Readable in;
    private Appendable out;
    private int level;

    public SudokuControllerConsole(Readable in, Appendable out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public void play(SudokuModel puzzle) throws IOException {
        int flag = 0;
        while (flag < 5) {
            int points = 0;
            Scanner scanner = new Scanner(in);
            try {
                out.append("Welcome to Sudoku!\n");
                out.append("Select level of difficulty: 1 for easy, 2 for medium, 3 for hard. \n");
                level = scanner.nextInt();
                puzzle.generatePuzzle(level);
            } catch (InputMismatchException e) {
                System.out.println("Only integers please");
            } catch (NoSuchElementException e) {
                System.out.println("No elements found in file");
            }

            while (!puzzle.isGameOver()) {
                try {
                    puzzle.print();
                    out.append("Select cell: Enter row and column\n");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InputMismatchException e) {
                    System.out.println("Only integers please");
                }

                String input1 = "";
                String input2 = "";

                try {
                    input1 = scanner.next();
                    input2 = scanner.next();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (input1.equalsIgnoreCase("q") ||
                        (input2.equalsIgnoreCase("q"))) {
                    System.out.println("Current state:\n");
                    puzzle.print();
                    break; //break loop
                }

                try {
                    int row = Integer.parseInt(input1);
                    int column = Integer.parseInt(input2);
                    row -= 1;
                    column -= 1;
                    out.append("Enter number for selected cell.\n");
                    int number = scanner.nextInt();
                    puzzle.move(row, column, number);
                    System.out.println("Current number of wrong guesses: " + puzzle.getWrongGuesses());
                } catch (NumberFormatException e) {
                    System.out.println("Only integers allowed for row and column");
                } catch (InputMismatchException e) {
                    System.out.println("Only integers allowed for guess");
                } catch (IllegalArgumentException e) {
                    if (e.getMessage().contentEquals("Position off the grid")) {
                        System.out.println("Position is off the grid!");
                    } else if (e.getMessage().contentEquals("Cell not available")) {
                        System.out.println("Cell is not available!");
                    }
                } catch (IOException e) {
                    System.out.println("Can't read input");
                }
            }
            if (puzzle.isGameOver()) {
                try {
                    if (puzzle.isSolved()) {
                        out.append("Game is over! You win the game.");
                        points += 5;
                    }
                    else {
                        out.append("Game over! No winner.\n");
                        out.append("Solved puzzle: \n");
                        puzzle.generatePuzzle(level);
                        puzzle.solveGame(0, 0);
                        puzzle.print();
                    }
                } catch (IOException e) {
                    throw new IllegalStateException("Illegal board state");
                }
            }

            flag++;
            System.out.println("Current number of points: " + points);
        }
    }
}




