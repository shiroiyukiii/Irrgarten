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
    
    private Monster[][] monsters;
    private Player[][] players;
    private char[][] labyrinth;
    private int nRows;
    private int nCols;
    private int exitRow;
    private int exitCol;
    
    public Labyrinth(int _nRows, int _nCols, int _exitRow, int _exitCol) {
        nRows = _nRows;
        nCols = _nCols;
        exitRow = _exitRow;
        exitCol = _exitCol;
        monsters = new Monster[nRows][nCols];
        players = new Player[nRows][nCols];
        labyrinth = new char[nRows][nCols];
        
        // Llenamos el array laberinto
        for (int i = 0; i < nRows; i++) {
            Arrays.fill(labyrinth[i], EMPTY_CHAR);
        }
        /*
        for (int i = 0; i < nRows - 1; i++) {
           labyrinth[Dice.randomPos(nRows)][Dice.randomPos(nCols)] 
                   = BLOCK_CHAR; 
        }
        */
        labyrinth[exitRow][exitCol] = EXIT_CHAR;
    }
    
    public void spreadPlayers(ArrayList<Player> _players) {
       for (Player p : _players) {
           int[] pos = randomEmptyPos();
           putPlayer2D(-1, -1, pos[ROW], pos[COL], p);
       }
    }
    
    public boolean haveAWinner() {
        return players[exitRow][exitCol] != null;
    }
    
    public String toString() {
        final char BORDER_LR_CHAR = '|';
        final char BORDER_U_CHAR = '_';
        final char BORDER_D_CHAR = '‾';
        final String U_BORDER = " " + String.format("%"+(nCols * 2 + 1)+"s", 
                "").replace(' ', BORDER_U_CHAR) + " \n";
        final String D_BORDER = " " + String.format("%"+(nCols * 2 + 1)+"s", 
                "").replace(' ', BORDER_D_CHAR) + " \n";
        
        // Borde superior
        String labyrinth_str = U_BORDER;
        
        // Interior del laberinto
        for (int i = 0; i < nRows; i++) {
            labyrinth_str += String.valueOf(BORDER_LR_CHAR) + " ";
            for (int j = 0; j < nCols; j++) {
                labyrinth_str += (labyrinth[i][j] + " ");
            }
            labyrinth_str += String.valueOf(BORDER_LR_CHAR) + "\n";
        }
        
        // Borde inferior
        labyrinth_str += D_BORDER;
        
        return labyrinth_str;
    }
    
    public void addMonster(int row, int col, Monster monster) {
        // Comprobamos que este dentro del tablero y que este vacía
        if (posOK(row, col) && emptyPos(row, col)) {
            labyrinth[row][col] = MONSTER_CHAR;
            monsters[row][col] = monster;
            monster.setPos(row, col);
        }
    }
    
    // Establece la nueva posición en la que se colocará el jugador
    public Monster putPlayer(Directions direction, Player player) {
        int oldRow = player.getRow();
        int oldCol = player.getCol();
        int[] newPos = dir2Pos(oldRow, oldCol, direction);
        return putPlayer2D(oldRow, oldCol, newPos[ROW], newPos[COL], player);
    }
    
    public void addBlock(Orientation orientation, int startRow, 
            int startCol, int length) {
        int incCol = (orientation == Orientation.VERTICAL ? 1 : 0);
        int incRow = (orientation == Orientation.VERTICAL ? 0 : 1);
        int row = startRow;
        int col = startCol;
        
        while (posOK(row, col) && emptyPos(row, col) && (length > 0)) {
            labyrinth[row][col] = BLOCK_CHAR;
            length--;
            row += incRow;
            row += incCol;
        }
    }
    
    public ArrayList<Directions> validMoves(int row, int col) {
        ArrayList<Directions> output = new ArrayList(4);
        
        if (canStepOn(row+1, col)) {
            output.add(Directions.DOWN);
        }
        if (canStepOn(row-1, col)) {
            output.add(Directions.UP);
        }
        if (canStepOn(row, col+1)) {
            output.add(Directions.RIGHT);
        }
        if (canStepOn(row, col-1)) {
            output.add(Directions.LEFT);
        }
        
        return output;
    }
    
    private boolean posOK(int row, int col) {
        return 0 < row && row < nRows && 0 < col && col < nCols;
    }
    
    private boolean emptyPos(int row, int col) {
        return labyrinth[row][col] == EMPTY_CHAR;
    }
    
    private boolean monsterPos(int row, int col) {
        return labyrinth[row][col] == MONSTER_CHAR;
    }
    
    private boolean exitPos(int row, int col) {
        return labyrinth[row][col] == EXIT_CHAR;
    }
    
    private boolean combatPos(int row, int col) {
       return labyrinth[row][col] == COMBAT_CHAR;
    }
    
    private boolean canStepOn(int row, int col) {
        return posOK(row, col) && (emptyPos(row, col) || monsterPos(row, col) 
                || exitPos(row, col));
    }
    
    private void updateOldPos(int row, int col) {
        if (posOK(row, col)) {
            if (combatPos(row, col))
                labyrinth[row][col] = MONSTER_CHAR;
            else
                labyrinth[row][col] = EMPTY_CHAR;
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
            if (posOK(pos[ROW], pos[COL]) && emptyPos(pos[ROW], pos[COL]))
                return pos;
        }
    }
    
    private Monster putPlayer2D(int oldRow, int oldCol, int row, 
            int col, Player player) {
        Monster monster = null;
        if (canStepOn(row, col)) {
            if (posOK(oldRow, oldCol)) {
                Player p = players[oldRow][oldCol];
                if (p == player) {
                    updateOldPos(oldRow, oldCol);
                    players[oldRow][oldCol] = null;
                }
            }
            
            if(monsterPos(row, col)) {
                labyrinth[row][col] = COMBAT_CHAR;
                monster = monsters[row][col];
            }
            else {
                labyrinth[row][col] = player.getNumber();
            }
            
            players[row][col] = player;
            player.setPos(row, col);
        }
        
        return monster;
    }
}
