package Controller;

import Model.SudokuModel;

import java.io.IOException;

/**
 * This class represents the controller of the game,
 * creating a new game and taking in user input
 */
interface SudokuController {

    /**
     * Main method of playing game given a puzzle
     * @param puzzle generated to be played
     * @throws IOException
     */
    void play(SudokuModel puzzle) throws IOException;
}
