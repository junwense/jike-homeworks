package com.sean.module4;

/**
 * @ClassName leetcode74
 * @Description: 搜索二维矩阵
 * @Author a9705
 * @Date 2022/8/13
 * @Version V1.0
 **/
public class leetcode74 {

    /**
     * 时间复杂度 O(log(n)*log(m))
     * 空间复杂度 O(1)
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int column = matrix[0].length;
        int start = 0;
        int end = row - 1;

        //先对列做二分
        while (start < end) {
            int mid = (start + end + 1) / 2;
            if (matrix[mid][0] == target) {
                return true;
            }

            if (matrix[mid][0] < target) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }
        //如果第一行的第一个数都比目标target大，则返回false
        if (target < matrix[end][0]) {
            return false;
        }

        int temp = end;
        start = 0;
        end = column - 1;
        //再对行做二分
        while (start < end) {
            int mid = (start + end + 1) / 2;
            if (matrix[temp][mid] == target) {
                return true;
            }
            if (matrix[temp][mid] < target) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }

        if (target != matrix[temp][end]) {
            return false;
        }

        return true;
    }
}
