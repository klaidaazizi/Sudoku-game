package Tests;

import Model.SudokuModel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class SudokuModelTest {

    private SudokuModel sudoku = new SudokuModel();

    @Test
    public void testGeneratePuzzle() {
        sudoku.generatePuzzle(1);
        assertEquals("2 0 3 0 0 0 0 6 0 \n" +
                        "0 0 0 0 0 3 7 0 0 \n" +
                        "0 1 0 2 0 4 0 0 0 \n" +
                        "0 0 6 0 5 0 0 0 0 \n" +
                        "0 5 8 0 0 0 0 1 0 \n" +
                        "0 0 0 9 4 0 0 5 0 \n" +
                        "7 0 9 8 0 5 2 0 0 \n" +
                        "0 2 0 0 7 0 5 0 0 \n" +
                        "0 4 0 1 6 0 0 7 0 \n",sudoku.toString());

        sudoku.generatePuzzle(2);
        assertEquals("2 0 0 0 0 0 6 9 0 \n" +
                "0 5 0 0 0 3 0 0 0 \n" +
                "1 7 0 0 0 9 4 0 0 \n" +
                "0 0 3 0 2 5 0 1 0 \n" +
                "0 0 0 0 4 0 0 0 0 \n" +
                "7 2 0 3 8 0 5 0 0 \n" +
                "5 0 2 6 0 0 0 4 0 \n" +
                "0 0 0 5 0 0 0 7 0 \n" +
                "0 6 7 0 0 0 0 0 0 \n",sudoku.toString());
        sudoku.generatePuzzle(3);
        assertEquals("0 0 2 7 8 0 0 0 0 \n" +
                "0 0 0 0 0 9 8 0 0 \n" +
                "4 0 0 0 0 3 0 7 0 \n" +
                "9 0 5 0 0 8 0 0 0 \n" +
                "0 0 0 0 7 0 0 0 0 \n" +
                "0 0 0 5 0 0 4 0 0 \n" +
                "0 6 0 4 0 0 0 0 0 \n" +
                "3 0 9 8 0 0 0 0 0 \n" +
                "8 0 0 0 3 1 6 0 0 \n",sudoku.toString());
    }

    @Test
    public void testMove(){
        sudoku.generatePuzzle(1);
        sudoku.move(1,1,1);
        assertEquals("2 0 3 0 0 0 0 6 0 \n" +
                "0 1 0 0 0 3 7 0 0 \n" +
                "0 1 0 2 0 4 0 0 0 \n" +
                "0 0 6 0 5 0 0 0 0 \n" +
                "0 5 8 0 0 0 0 1 0 \n" +
                "0 0 0 9 4 0 0 5 0 \n" +
                "7 0 9 8 0 5 2 0 0 \n" +
                "0 2 0 0 7 0 5 0 0 \n" +
                "0 4 0 1 6 0 0 7 0 \n",sudoku.toString());
    }

    @Test
    public void testFoundInRow() {
        sudoku.generatePuzzle(1);
        assertTrue(sudoku.foundInRow(1,7));
    }

    @Test
    public void testFoundInColumn() {
        sudoku.generatePuzzle(1);
        assertTrue(sudoku.foundInColumn(3,8));
    }

    @Test
    public void testFoundInBox() {
        sudoku.generatePuzzle(1);
        assertTrue(sudoku.foundInBox(7,8,2));
    }

    @Test
    public void testIsInputValid() {
        sudoku.generatePuzzle(1);
        assertTrue(sudoku.foundInBox(2,3,2));
    }

    @Test
    public void testIsGameOver() {
        sudoku.generatePuzzle(2);
        sudoku.solveGame(0,0);
        assertTrue(sudoku.isGameOver());
    }


    @Test
    public void testSolveGame() {
        sudoku.generatePuzzle(1);
        assertTrue(sudoku.solveGame(0,0));
    }

    @Test(expected= IllegalArgumentException.class)
    public void testInvalidMove(){
        sudoku.generatePuzzle(1);
        sudoku.move(9,10,1); //Position off the grid

        sudoku.move(1,2,3); //Spot not available
    }

    @Test(expected= IllegalStateException.class)
    public void testMoveAttemptafterGameOver(){
        sudoku.generatePuzzle(2);
        sudoku.solveGame(0,0);
        sudoku.move(2,2,5);
    }


}