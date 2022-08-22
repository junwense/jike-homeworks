package com.sean.module5_1;

/**
 * @ClassName leetcode130
 * @Description: 130. 被围绕的区域
 * @Author a9705
 * @Date 2022/8/22
 * @Version V1.0
 **/
public class leetcode130_1 {

    final static int[] dx = {0, 1, 0, -1};
    final static int[] dy = {-1, 0, 1, 0};

    /**
     * 时间复杂度 O(n*m)
     * 空间复杂度 O(1)
     */
    public void solve(char[][] board) {

        int row = board.length;
        int col = board[0].length;

        if(row==1&&col==1){
            return;
        }

        //从边界开始遍历
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j += col - 1) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'Y';
                    dfs(board, i, j);
                }
            }
        }

        //从边界开始遍历
        for (int i = 0; i < row; i += row - 1) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'Y';
                    dfs(board, i, j);
                }
            }
        }

        for (int n = 0; n < row; n++) {
            for (int m = 0; m < col; m++) {
                if (board[n][m] == 'O') {
                    board[n][m] = 'X';
                } else if (board[n][m] == 'Y') {
                    board[n][m] = 'O';
                }
            }
        }

    }

    private void dfs(char[][] board, int i, int j) {
        int row = board.length;
        int col = board[0].length;
        for (int k = 0; k < dy.length; k++) {
            int newRow = i + dy[k];
            int newCol = j + dx[k];
            if (newRow >= 0 && newRow < row && newCol >= 0 && newCol < col) {
                if (board[newRow][newCol] == 'O') {
                    board[newRow][newCol] = 'Y';
                    dfs(board, newRow, newCol);
                }
            }
        }
    }

}
