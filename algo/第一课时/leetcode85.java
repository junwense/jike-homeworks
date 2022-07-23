package com.sean;

import java.util.Stack;

/**
 * @ClassName leetcode85
 * @Description: 最大矩阵
 * @Author a9705
 * @Date 2022/7/23
 * @Version V1.0
 **/
public class leetcode85 {

    /**
     * 空间复杂度 n*n
     * 时间复杂度 n*n
     */
    public int maximalRectangle(char[][] matrix) {
        int ans = 0;
        //定义每行数据的temp
        int[] temp = new int[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    temp[j] += 1;
                } else {
                    //如果当前部不为1，则当前矩阵需要重新累加
                    temp[j] = 0;
                }
            }
            //按行读取矩阵后，temp里面存的就是一个最大矩阵的数组
            ans = Math.max(ans, largestRectangleArea(temp));
        }

        return ans;
    }

    class Node {
        public int height;
        public int weight;

        public Node(int height, int weight) {
            this.height = height;
            this.weight = weight;
        }
    }

    /**
     * 单调栈求最大矩形
     * 空间复杂度 2n
     * 时间复杂度 n
     */
    public int largestRectangleArea(int[] heights) {

        if (heights.length == 1) {
            return heights[0];
        }

        int ans = 0;
        Stack<Node> stack = new Stack<>();
        for (int height : heights) {
            int weight = 0;
            //当前元素小于栈顶元素则破坏单调性
            while (!stack.isEmpty() && stack.peek().height >= height) {
                Node pop = stack.pop();
                weight += pop.weight;
                //取出栈顶元素并算出最大值
                ans = Math.max(ans, pop.height * weight);
            }
            //最后加入栈
            stack.push(new Node(height, weight + 1));
        }

        //最后再出栈一次，可以考虑补0，但是空间不是最优
        if (!stack.isEmpty()) {
            int weight = 0;
            while (!stack.isEmpty()) {
                Node pop = stack.pop();
                weight += pop.weight;
                //取出栈顶元素并算出最大值
                ans = Math.max(ans, pop.height * weight);
            }
        }

        return ans;
    }

    /**
     * 优化后的计算最大矩形方法
     * 空间复杂度 3n
     * 时间复杂度 n
     * @param heights
     * @return
     */
    public int largestRectangleArea1(int[] heights) {
        int n = heights.length;
        //边界条件判断
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return heights[0];
        }
        //left记录元素左边第一个小于它的元素的下标
        //right记录元素右边第一个小于它的元素的下标
        int[] left = new int[n];
        int[] right = new int[n];
        //stack是用来模拟栈的数组 ，类似于heights的索引素组
        int[] stack = new int[n+1];
        //tt记录栈中元素位置
        int tt = 0;
        for (int i = 0; i < n; i++) {
            //遍历栈，直到下标元素小于heights[i]
            while (tt != 0 && heights[stack[tt]] >= heights[i]) {
                tt--;
            }
            left[i] = (tt == 0) ? -1 : stack[tt];
            stack[++tt] = i;
        }

        //将tt置为0即可当做一个空栈，用来找每个元素右边第一个小于它的元素的下标
        tt = 0;
        for (int i = n - 1; i >= 0; i--) {
            while (tt != 0 && heights[stack[tt]] >= heights[i]) {
                tt--;
            }
            right[i] = (tt == 0) ? n : stack[tt];
            stack[++tt] = i;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, heights[i] * (right[i] - left[i] - 1));
        }
        return ans;
    }
}
