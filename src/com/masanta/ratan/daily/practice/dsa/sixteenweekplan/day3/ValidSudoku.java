package com.masanta.ratan.daily.practice.dsa.sixteenweekplan.day3;

import java.util.HashSet;

public class ValidSudoku {

    /*

        36. Valid Sudoku


            Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

            Each row must contain the digits 1-9 without repetition.
            Each column must contain the digits 1-9 without repetition.
            Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
            Note:

            A Sudoku board (partially filled) could be valid but is not necessarily solvable.
            Only the filled cells need to be validated according to the mentioned rules.


            Example 1:


            Input: board =
            [['5','3','.','.','7','.','.','.','.']
            ,['6','.','.','1','9','5','.','.','.']
            ,['.','9','8','.','.','.','.','6','.']
            ,['8','.','.','.','6','.','.','.','3']
            ,['4','.','.','8','.','3','.','.','1']
            ,['7','.','.','.','2','.','.','.','6']
            ,['.','6','.','.','.','.','2','8','.']
            ,['.','.','.','4','1','9','.','.','5']
            ,['.','.','.','.','8','.','.','7','9']]
            Output: true
            Example 2:

            Input: board =
            [['8','3','.','.','7','.','.','.','.']
            ,['6','.','.','1','9','5','.','.','.']
            ,['.','9','8','.','.','.','.','6','.']
            ,['8','.','.','.','6','.','.','.','3']
            ,['4','.','.','8','.','3','.','.','1']
            ,['7','.','.','.','2','.','.','.','6']
            ,['.','6','.','.','.','.','2','8','.']
            ,['.','.','.','4','1','9','.','.','5']
            ,['.','.','.','.','8','.','.','7','9']]
            Output: false
            Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.


            Constraints:

            board.length == 9
            board[i].length == 9
            board[i][j] is a digit 1-9 or '.'.
     */


    public static void main(String[] args) {
        char[][] sudoku = {{'5','3','.','.','7','.','.','.','.'}
,{'6','.','.','1','9','5','.','.','.'}
,{'.','9','8','.','.','.','.','6','.'}
,{'8','.','.','.','6','.','.','.','3'}
,{'4','.','.','8','.','3','.','.','1'}
,{'7','.','.','.','2','.','.','.','6'}
,{'.','6','.','.','.','.','2','8','.'}
,{'.','.','.','4','1','9','.','.','5'}
,{'.','.','.','.','8','.','.','7','9'}};

        System.out.println(isValidSudoku(sudoku));
    }


    /**
     *
     * We check the sudoku to see if all the rows have valid values (1-9 only once)
     * We check the columns for the same
     * We add a logic for box calculation with the below logic:
     *
     * 1. Sudoku Board Structure:
     *    - The board is a 9x9 grid represented as char[][] board.
     *    - Each cell contains either a digit ('1' to '9') or a period ('.') for an empty cell.
     *    - The board is divided into nine 3x3 sub-grids (boxes), typically indexed as follows:
     *
     *      Box 0 | Box 1 | Box 2
     *      ------+-------+------
     *      Box 3 | Box 4 | Box 5
     *      ------+-------+------
     *      Box 6 | Box 7 | Box 8
     *
     *
     * 2. HashSet for Boxes:
     *    - The array HashSet<Character>[] boxes = new HashSet[9] creates nine HashSet objects, one for each 3x3 box.
     *    - Each HashSet tracks the digits encountered in its corresponding box to detect duplicates.
     *
     * 3. Box Index Calculation:
     *    - The critical line for box calculation is:
     *      java
     *      int boxIndex = (r/3)  3 + (c/3);
     *
     *    - Here, r is the row index (0 to 8), and c is the column index (0 to 8).
     *    - This formula maps each cell (r, c) to one of the nine boxes (0 to 8).
     *
     *  How the Box Index is Calculated
     * The formula (r/3)  3 + (c/3) determines which 3x3 box a cell belongs to. Let’s break it down:
     *
     * - Integer Division:
     *   - In Java, the / operator performs integer division when applied to integers, discarding the remainder.
     *   - r/3 maps the row index to one of three row groups:
     *     - Rows 0, 1, 2 → r/3 = 0
     *     - Rows 3, 4, 5 → r/3 = 1
     *     - Rows 6, 7, 8 → r/3 = 2
     *   - Similarly, c/3 maps the column index to one of three column groups:
     *     - Columns 0, 1, 2 → c/3 = 0
     *     - Columns 3, 4, 5 → c/3 = 1
     *     - Columns 6, 7, 8 → c/3 = 2
     *
     * - Formula Breakdown:
     *   - (r/3) determines the row group (0, 1, or 2) of the 3x3 box.
     *   - (r/3)  3 scales the row group to the starting box index for that row of boxes:
     *     - Row group 0 (rows 0–2) → (0  3) = 0 (boxes 0, 1, 2)
     *     - Row group 1 (rows 3–5) → (1  3) = 3 (boxes 3, 4, 5)
     *     - Row group 2 (rows 6–8) → (2  3) = 6 (boxes 6, 7, 8)
     *   - (c/3) determines the column group (0, 1, or 2) and adds an offset to select the specific box within the row group:
     *     - Column group 0 (columns 0–2) → +0
     *     - Column group 1 (columns 3–5) → +1
     *     - Column group 2 (columns 6–8) → +2
     *
     * - Resulting Box Index:
     *   - The formula combines these to produce a unique index from 0 to 8 for each 3x3 box.
     *   - For example:
     *     - Cell (r=1, c=1) (row 1, column 1):
     *       - r/3 = 1/3 = 0
     *       - c/3 = 1/3 = 0
     *       - boxIndex = (0  3) + 0 = 0 → Box 0
     *     - Cell (r=4, c=5) (row 4, column 5):
     *       - r/3 = 4/3 = 1
     *       - c/3 = 5/3 = 1
     *       - boxIndex = (1  3) + 1 = 3 + 1 = 4 → Box 4
     *     - Cell (r=7, c=8) (row 7, column 8):
     *       - r/3 = 7/3 = 2
     *       - c/3 = 8/3 = 2
     *       - boxIndex = (2  3) + 2 = 6 + 2 = 8 → Box 8
     *
     *  Mapping of Cells to Boxes
     * The formula ensures that all cells in a given 3x3 sub-grid map to the same box index. Here’s how the 9x9 grid is divided:
     *
     * | Rows \ Columns | 0–2        | 3–5        | 6–8        |
     * |----------------|------------|------------|------------|
     * | 0–2        | Box 0      | Box 1      | Box 2      |
     * | 3–5        | Box 3      | Box 4      | Box 5      |
     * | 6–8        | Box 6      | Box 7      | Box 8      |
     *
     * For example:
     * - All cells in rows 0–2 and columns 0–2 (e.g., (0,0), (1,1), (2,2)) map to boxIndex = 0.
     * - All cells in rows 3–5 and columns 3–5 (e.g., (3,3), (4,4), (5,5)) map to boxIndex = 4.
     *
     *  Validation Process for Boxes
     * Once the boxIndex is calculated for a cell (r, c):
     * 1. Check for Duplicates:
     *    - The code retrieves the cell’s value: char val = board[r][c].
     *    - If the cell is empty (val == '.'), it skips to the next cell (continue).
     *    - It checks if the digit val is already in the HashSet for the corresponding box:
     *      java
     *      if(boxes[boxIndex].contains(val)) return false;
     *
     *    - If val is present, it means a duplicate digit exists in the 3x3 box, violating Sudoku rules, so the function returns false.
     *
     * 2. Add the Digit:
     *    - If no duplicate is found, the digit is added to the box’s HashSet:
     *      java
     *      boxes[boxIndex].add(val);
     *
     *    - This ensures that subsequent checks for the same box will detect any repeated digits.
     *
     *  Why This Works
     * - Efficiency: Using a HashSet for each box allows O(1) lookup and insertion, making the validation fast.
     * - Correct Mapping: The formula (r/3)  3 + (c/3) correctly groups cells into their respective 3x3 boxes, ensuring that all cells in a given box are checked together.
     * - Sudoku Rules: The code ensures that no digit (1–9) appears more than once in any 3x3 box, which is one of the three Sudoku constraints (along with no duplicates in rows and columns).
     *
     *  Example Walkthrough
     * Suppose the board has a cell at (r=4, c=5) with value '5':
     * - Calculate boxIndex:
     *   - r/3 = 4/3 = 1
     *   - c/3 = 5/3 = 1
     *   - boxIndex = (1  3) + 1 = 4
     * - Check boxes[4] (the HashSet for Box 4):
     *   - If boxes[4].contains('5') is true, return false (invalid Sudoku due to duplicate).
     *   - Otherwise, add '5' to boxes[4] and continue.
     *
     *
     * @param board int[][] given sudoku
     * @return if the sudoku is valid or not
     */
    public static boolean isValidSudoku(char[][] board) {
        HashSet<Character>[] rows = new HashSet[9];
        HashSet<Character>[] cols = new HashSet[9];
        HashSet<Character>[] boxes = new HashSet[9];

        //initialize the arrays with hashsets
        for(int i = 0 ; i < 9 ; i++){
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        //iterate over the sudoku using two for loops
        for(int r = 0 ; r < 9 ; r++){
            for(int c = 0 ; c < 9 ; c++){
                char val = board[r][c];
                if(val == '.') continue;

                //check rows
                if(rows[r].contains(val)) return false;
                rows[r].add(val);

                //check cols
                if(cols[c].contains(val)) return false;
                cols[c].add(val);

                //check box
                int boxIndex = (r/3) * 3 + (c/3);
                if(boxes[boxIndex].contains(val)) return false;
                boxes[boxIndex].add(val);
            }
        }

        return true;
    }
}
