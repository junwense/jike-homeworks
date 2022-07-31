package com.sean;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName leetcode1074
 * @Description: TODO
 * @Author a9705
 * @Date 2022/7/27
 * @Version V1.0
 **/
public class leetcode1074 {

    public static void main(String[] args) {
        leetcode1074 leetcode1074 = new leetcode1074();
        int i = leetcode1074.numSubmatrixSumTarget(new int[][]{{0, 1, 0}, {1, 1, 1}, {0, 1, 0}}, 0);
        System.out.println(i);
    }

    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int ans = 0;

        int n = matrix.length;
        int m = matrix[0].length;

        Set<String> store = new HashSet<>();

        int[][] sum = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                //前缀和
                sum[i][j] = sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1] + matrix[i - 1][j - 1];

                for (int q = i; q >=1 ; q--) {
                    for (int p = j; p >=1 ; p--) {
                        if(target==getChildMatrixSum(i,j,q,p,sum)){
                            ans++;
                        }
                    }
                }
            }

        }

        //子矩阵和
        //可以考虑用hash保存一条边后处理
        for (int i = n; i >= 1; i--) {
            for (int j = m; j >= 1; j--) {
                for (int q = 1; q <= i; q++) {
                    for (int p = 1; p <= j; p++) {
//                        String key = getKey(i, j, q, p);
//                        if(store.contains(key)){
//                            continue;
//                        }else{
//                            store.add(key);
//                        }
                        if(target==getChildMatrixSum(i,j,q,p,sum)){
                            ans++;
                        }
                    }
                }
            }
        }

        return ans;
    }

    //计算以(i,j)为右下角 为左上角(q,p)的矩形的所含数据和
    public int getChildMatrixSum(int i, int j, int q, int p, int[][] matrixSum) {
        return matrixSum[i][j] - matrixSum[i][p - 1] - matrixSum[q - 1][j] + matrixSum[q - 1][p - 1];
    }

    public String getKey(int i, int j, int q, int p) {
        System.out.println("" + i + j + q + p);
        return "" + i + j + q + p;
    }
}
