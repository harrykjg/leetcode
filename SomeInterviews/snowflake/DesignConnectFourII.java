package SomeInterviews.snowflake;

import java.util.Arrays;

public class DesignConnectFourII {
    /*
    Design and implement a Connect Four style game board that supports dropping colored pieces into columns. In this board game, pieces fall vertically under gravity and settle at the lowest available position in the chosen column.

Implement the ConnectFourBoard class:

ConnectFourBoard(int rows, int cols) Initializes a game board with the specified number of rows (height) and cols (width). All cells start empty, represented by the character '0'.

char[][] drop(char color, int col) Drops a piece of the specified color into column col. The piece falls to the lowest available row in that column. Returns an independent copy of the current board state as a 2D character array after the drop operation.

Empty cells are represented by '0'
Colored pieces are represented by their characters (e.g., 'R' for red, 'Y' for yellow, 'B' for blue)
If the column is already full, the board remains unchanged
Constraints:

2 ≤ rows ≤ 100
2 ≤ cols ≤ 100
0 ≤ col < cols
color can be any character except '0' (which represents empty cells)
Example:

Input:
["ConnectFourBoard", "drop", "drop", "drop", "drop"]
[[3, 3], ['Y', 1], ['Y', 1], ['Y', 1], ['Y', 1]]

Output:
[null, [["0", "0", "0"], ["0", "0", "0"], ["0", "Y", "0"]], [["0", "0", "0"], ["0", "Y", "0"], ["0", "Y", "0"]], [["0", "Y", "0"], ["0", "Y", "0"], ["0", "Y", "0"]], [["0", "Y", "0"], ["0", "Y", "0"], ["0", "Y", "0"]]]

Explanation:


The board state changes are tracked as follows:

ConnectFourBoard board = new ConnectFourBoard(3, 3); // Initialize a 3 x 3 board.
board.drop('Y', 1); // Returns [["0", "0", "0"], ["0", "0", "0"], ["0", "Y", "0"]]. The yellow piece falls to the bottom row (row 2) of column 1.
board.drop('Y', 1); // Returns [["0", "0", "0"], ["0", "Y", "0"], ["0", "Y", "0"]]. The yellow piece falls to row 1 of column 1.
board.drop('Y', 1); // Returns [["0", "Y", "0"], ["0", "Y", "0"], ["0", "Y", "0"]]. The yellow piece fills the top row (row 0) of column 1.
board.drop('Y', 1); // Returns [["0", "Y", "0"], ["0", "Y", "0"], ["0", "Y", "0"]]. Column 1 is full, so the board remains unchanged.
     */
    char[][] b;
    int[] emptyRow;//就是拿一个数组记录某列上的哪一行是availiable的
    public ConnectFourBoard(int rows, int cols) {
        // TODO: Initialize ConnectFourBoard
        b=new char[rows][cols];
        emptyRow=new int[cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                b[r][c] = '0';
            }
        }
        Arrays.fill(emptyRow,rows-1);
    }
    //就是填到原数组再copy到新的board上，毫无技术含量
    public char[][] drop(char color, int col) {
        if(emptyRow[col]>=0){
            b[emptyRow[col]][col]=color;
            emptyRow[col]--;
        }
        char[][] snapshot = new char[b.length][b[0].length];
        for (int r = 0; r < b.length; r++) {
            for (int c = 0; c < b[0].length; c++) {
                snapshot[r][c] = b[r][c];
            }
        }

        return snapshot;

    }
}
