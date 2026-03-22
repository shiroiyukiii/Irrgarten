/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Víctor
 */
public class Labyrinth {
    static private final char BLOCK_CHAR = 'X';
    static private final char EMPTY_CHAR = '-';
    static private final char MONSTER_CHAR = 'M';
    static private final char COMBAT_CHAR = 'C';
    static private final char EXIT_CHAR = 'E';
    static private final int ROW = 0;
    static private final int COL = 1;
    
    private Monster[][] monster_grid;
    private Player[][] player_grid;
    private char[][] labyrinth_grid;
    private int nRows;
    private int nCols;
    private int exitRow;
    private int exitCol;
    
    public Labyrinth(int _nRows, int _nCols, int _exitRow, int _exitCol) {
        nRows = _nRows;
        nCols = _nCols;
        exitRow = _exitRow;
        exitCol = _exitCol;
        monster_grid = new Monster[nRows][nCols];
        player_grid = new Player[nRows][nCols];
        labyrinth_grid = new char[nRows][nCols];
        
        // Llenamos el array laberinto
        for (int i = 0; i < nRows; i++) {
            Arrays.fill(labyrinth_grid, EMPTY_CHAR);
        }
        
        
        // Ponemos las paredes
        labyrinth_grid[0][0] = BLOCK_CHAR;
        /*
        for (int i = 0; i < nRows - 1; i++) {
           labyrinth_grid[Dice.randomPos(nRows)][Dice.randomPos(nCols)] 
                   = BLOCK_CHAR; 
        }
        */
        
        // Poniendo un monstruo y la salida
        monster_grid[nRows - 2][nCols - 1] = new Monster("EC", 
                Dice.randomIntelligence(), Dice.randomStrength());
        labyrinth_grid[nRows - 2][nCols - 1] = MONSTER_CHAR;
        labyrinth_grid[exitRow][exitCol] = EXIT_CHAR;
    }
    
    public void spreadPlayers(ArrayList<Player> players) {
        throw new UnsupportedOperationException();
    }
    
    public boolean haveAWinner() {
        return player_grid[exitRow][exitCol] != null;
    }
    
    public String toString() {
        final char BORDER_LR_CHAR = '|';
        final char BORDER_UD_CHAR = '_';
        final String UD_BORDER = " " + String.format("%"+(nCols * 2 + 1)+"s")
                .replace(' ', BORDER_UD_CHAR) + " \n";
        
        // Borde superior
        String labyrinth_str = UD_BORDER;
        
        // Interior del laberinto
        for (int i = 0; i < nRows; i++) {
            labyrinth_str += BORDER_LR_CHAR + ' ';
            for (int j = 0; i < nCols; j++) {
                labyrinth_str += (labyrinth_grid[i][j] + " ");
            }
            labyrinth_str += BORDER_LR_CHAR + '\n';
        }
        
        // Borde inferior
        labyrinth_str += UD_BORDER;
        
        return labyrinth_str;
    }
    
    public void addMonster(int row, int col, Monster monster) {
        // Comprobamos que este dentro del tablero y que este vacía
        if (posOK(row, col) && emptyPos(row, col)) {
            labyrinth_grid[row][col] = MONSTER_CHAR;
            monster_grid[row][col] = monster;
            monster.setPos(row, col);
        }
    }
    
    public Monster putPlayer(Directions direction, Player player) {
        throw new UnsupportedOperationException();
    }
    
    public void addBlock(Orientation orientation, int startRow, 
            int startCol, int length) {
        throw new UnsupportedOperationException();
    }
    
    public Directions[] validMoves(int row, int col) {
        throw new UnsupportedOperationException();
    }
    
    private boolean posOK(int row, int col) {
        return 0 < row && row < nRows && 0 < col && col < nCols;
    }
    
    private boolean emptyPos(int row, int col) {
        return labyrinth_grid[row][col] == EMPTY_CHAR;
    }
    
    private boolean monsterPos(int row, int col) {
        return labyrinth_grid[row][col] == MONSTER_CHAR;
    }
    
    private boolean exitPos(int row, int col) {
        return labyrinth_grid[row][col] == EXIT_CHAR;
    }
    
    private boolean combatPos(int row, int col) {
       return labyrinth_grid[row][col] == COMBAT_CHAR;
    }
    
    private boolean canStepOn(int row, int col) {
        return posOK(row, col) && (emptyPos(row, col) || monsterPos(row, col) 
                || exitPos(row, col));
    }
    
    private void updateOldPos(int row, int col) {
        if (posOK(row, col)) {
            if (combatPos(row, col))
                labyrinth_grid[row][col] = MONSTER_CHAR;
            else
                labyrinth_grid[row][col] = EMPTY_CHAR;
        }
    }
    
    private int[] dir2Pos(int row, int col, Directions direction) {
        int[] dirPos = {row, col};
        
        switch (direction) {
            case LEFT:
                dirPos[COL]--;
                break;
            case RIGHT:
                dirPos[COL]++;
                break;
            case UP:
                dirPos[ROW]--;
                break;
            case DOWN:
                dirPos[ROW]++;
                break;
        }
        return dirPos;
    }
    
    private int[] randomEmptyPos() {
        while (true) {
            int[] pos = {Dice.randomPos(nRows), Dice.randomPos(nCols)};
            if (emptyPos(pos[ROW], pos[COL]))
                return pos;
        }
    }
    
    private Monster putPlayer2D(int oldRow, int oldCol, int row, 
            int col, Player player) {
        throw new UnsupportedOperationException();
    }
}
