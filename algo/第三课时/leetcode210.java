package com.sean.module3;

import java.util.*;

/**
 * @ClassName leetcode210
 * @Description: TODO
 * @Author a9705
 * @Date 2022/8/8
 * @Version V1.0
 **/
public class leetcode210 {

    /**
     * 时间复杂度 O(3n)
     * 空间复杂度 O(4n)
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        //出边数组
        List<Integer>[] map = new ArrayList[numCourses];
        //入度数组
        int[] preEdges = new int[numCourses];
        //初始化出边素组
        for (int i = 0; i < map.length; i++) {
            map[i] = new ArrayList<>();
        }

        for (int[] edge : prerequisites) {
            map[edge[1]].add(edge[0]);
            preEdges[edge[0]] += 1;
        }

        //bfs 遍历队列
        Deque<Integer> temp = new LinkedList<>();
        //结果数组
        int[] ans = new int[numCourses];
        //当前 ansIdx 位置
        int ansIdx = 0;
        for (int i = 0; i < preEdges.length; i++) {
            if (preEdges[i] == 0) {
                temp.add(i);
            }
        }
        //不存在入度为0的点，则直接返回
        if (temp.isEmpty()) {
            return new int[]{};
        }

        while (!temp.isEmpty()) {
            Integer point = temp.pollFirst();
            //记录课程
            ans[ansIdx++] = point;
            for (Integer toPoint : map[point]) {
                //入度减去1，如果为0则代表前置条件结束
                preEdges[toPoint] -= 1;
                if (preEdges[toPoint] == 0) {
                    temp.addLast(toPoint);
                }
            }
        }

        if(ansIdx==numCourses){
            return ans;
        }else{
            return new int[]{};
        }

    }
}
