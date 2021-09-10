package Model;

/**
 * This class represents the model interface of the game
 */
interface Sudoku {

    /**
     * Generates a new puzzle (2D array) based on the level that user selects
     * @param level of difficulty that user selects for the game
     * @return a 2D array as puzzle to be completed
     */
    int[][] generatePuzzle(int level);

    /**
     * Makes a move selected by the user
     * @param row int row selected by user
     * @param col int column selected by user
     * @param val int number user guesses for game
     */
    void move(int row, int col, int val);

    /**
     * Helper function- Checks if number guessed is found in row or not
     * @param row int row of guessed number
     * @param val number guessed
     * @return boolean true if number is found in the same row and false if not
     */
    boolean foundInRow(int row, int val);
    /**
     * Helper function- Checks if number guessed is found in column or not
     * @param col int column of guessed number
     * @param val number guessed
     * @return boolean true if number is found in the same column and false if not
     */
    boolean foundInColumn(int col, int val);
    /**
     * Helper function- hecks if number guessed is found in the 3x3 grid or not
     * @param row int row of guessed number
     * @param col int column of guessed number
     * @param val number guessed
     * @return boolean true if number is found inside the 3x3 grid and false if not
     */
    boolean foundInBox(int row, int col, int val);

    /**
     * Checks if number guessed by user is right or not
     * @param row int row of number guessed
     * @param col int column of number guessed
     * @param val int number guessed
     * @return boolean true if number is right and false if not
     */
    boolean isInputValid(int row, int col, int val);

    /**
     * Checks if game is over or not- it's over if it's solved, user has more than 3 wrong guesses or decides to quit
     * @return boolean true if game is over and false if not
     */
    boolean isGameOver();

    /**
     * Checks if puzzle is solved
     * @return boolean true if puzzle is solved and false if not
     */
    boolean isSolved();

    /**
     * Solves the puzzle by using the backtracking algorithm
     * @param row int row of starting cell
     * @param column int column of starting cell
     * @return true if puzzle can be solved and false if not
     */
    boolean solveGame(int row, int column);

    /**
     * Prints a 2D array of the current state of the puzzle
     */
    void print();
}
