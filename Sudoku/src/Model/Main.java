package Model;

import Controller.SudokuControllerConsole;

import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        SudokuModel sudoku = new SudokuModel();
        SudokuControllerConsole controller = new SudokuControllerConsole(new InputStreamReader(System.in),
                System.out);
        controller.play(sudoku);
    }

}
