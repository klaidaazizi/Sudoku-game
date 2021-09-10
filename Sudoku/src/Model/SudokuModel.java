package Model;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class SudokuModel implements Sudoku {
    int puzzle[][];
    int wrongGuesses;

    public SudokuModel(){
        this.puzzle = new int[9][9];
    }

    @Override
    public int[][] generatePuzzle(int level) {
        File file;
        if (level == 1) {
            file = new File("easy");
        } else if (level == 2){
            file = new File("medium");
        } else{
            file = new File("hard");
        }
        try {
            Scanner scanner = new Scanner(file);
            //read a  Sudoku file
            for (int row = 0; row < 9; row++) {
                String[] numbers = scanner.nextLine().split(" ");
                for (int column = 0; column < 8; column++) {
                    puzzle[row][column] = Integer.parseInt(numbers[column]);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        return puzzle;
    }

    @Override
    public void move(int row, int col, int val) throws IllegalArgumentException, IllegalStateException {
        if (isGameOver())
            throw new IllegalStateException("Game is over!");
        else if (row < 0 || row > 9 || col < 0 || col > 9)
            throw new IllegalArgumentException("Position off the grid");
        else if (puzzle[row][col] != 0)
            throw new IllegalArgumentException("Cell not available");
        else if (!isInputValid(row,col,val)){
            puzzle[row][col] = val;
            wrongGuesses ++;}
        else {
            puzzle[row][col] = val;
        }
    }

    public int getWrongGuesses(){
        return this.wrongGuesses;
    }


    public int[][] getPuzzle(){
        return this.puzzle;
    }

    @Override
    public boolean foundInRow(int row, int val) {
        for (int i = 0; i < 9; i++) {
            if (puzzle[row][i] == val) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean foundInColumn(int col, int val) {
        for (int i = 0; i < 9; i++) {
            if (puzzle[i][col] == val) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean foundInBox(int row, int col, int val) {
        int currBoxRow = row - row %3; //find top left row coordinate of box
        int currBoxCol = col - col %3; //find top left col coordinate of box
        for (int i = currBoxRow; i < currBoxRow + 3; i++) {
            for (int j = currBoxCol; j < currBoxCol + 3; j++) {
                if (puzzle[i][j] == val) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isInputValid(int row, int col, int val) {
        if (!foundInRow(row,val) && !foundInColumn(col,val) &&
                !foundInBox(row,col,val))
            return true;
        return false;
    }


    @Override
    public boolean isGameOver() {
        if (this.getWrongGuesses() >= 3)
            return true;
        else if (isSolved())
            return true;
        return false;
    }

    @Override
    public boolean isSolved() {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (puzzle[row][column] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean solveGame(int row, int column) {
        if (column == 9) { //if column coordinate reaches 9,
            column = 0; // jump back to 0
            row += 1; //and increase row coordinate by 1 to go to the next cell
            if (row == 9){ //if it reaches the end of the grid, last spot
                return true;
            }
        }
        for (int newNumber = 1; newNumber <=9; newNumber++){ //try new number from 1 to 9
            if(puzzle[row][column] == 0){ //find an empty spot
                if (isInputValid(row,column,newNumber)){ //find the first number that is is valid for empty spot from 1-9
                    puzzle[row][column] = newNumber; //choose this number that is valid
                    if (solveGame(row, column + 1)){ //recurse for next spot until all spots are filled
                        return true;
                    } else{
                        puzzle[row][column] = 0; //if it reaches a spot that can't be valid, reset and start over
                    }
                }
            } else
                return solveGame(row, column + 1); //continue to next spot
        }
        return false;
    }

    @Override
    public void print() {
        for (int row = 0; row < 9; row++) {
            if (row % 3 == 0 && row != 0)
                System.out.println("-----------------------");
            for (int col = 0; col < 9; col++) {
                if (col % 3 == 0 && col != 0)
                    System.out.print(" | ");
                System.out.print(puzzle[row][col] + " ");
            }
            System.out.println();
        }
    }

    public String toString(){
        String str = "";
        for (int row = 0;row<9;row++){
            for(int col =0;col<9;col++) {
                str = str + puzzle[row][col] + " ";
            }
            str = str + "\n";
            }
        return str;
        }

}