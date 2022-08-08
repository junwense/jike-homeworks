package com.sean.module3;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName leetcode685
 * @Description:  冗余连接 II（暴力破解）
 * @Author a9705
 * @Date 2022/8/8
 * @Version V1.0
 **/
public class leetcode685 {

    /**
     * 时间复杂度 O(2n*n+2n)
     * 空间复杂度 O(3n)
     */
    public int[] findRedundantDirectedConnection(int[][] edges) {

        int[] ans = null;

        int size = edges.length;
//        for (int[] edge : edges) {
//            size=Math.max(Math.max(edge[0],edge[1]),size);
//        }
        //出边数组
        List<Integer>[] map = new ArrayList[size + 1];
        //入度数组
        int[] preEdges = new int[size + 1];
        //初始化出边素组
        for (int i = 0; i < map.length; i++) {
            map[i] = new ArrayList<>();
        }

        //设置图和出边数组
        for (int[] edge : edges) {
            map[edge[0]].add(edge[1]);
            preEdges[edge[1]] += 1;
        }

        //从最后一条边开始处理
        for (int i = edges.length - 1; i >= 0; i--) {
            int[] edge = edges[i];
            //移除边，这里注意要传入对象，不然会按照index删除元素
            map[edge[0]].remove(Integer.valueOf(edge[1]));
            preEdges[edge[1]] -= 1;

            //看看剩下的节点是否可以构造成树
            if (buildTree(map, preEdges, size)) {
                return edge;
            }

            //还原边
            preEdges[edge[1]] += 1;
            map[edge[0]].add(edge[1]);
        }

        return ans;
    }

    /**
     * 时间复杂度 O(2n)
     * 空间复杂度 O(n)
     */
    private boolean buildTree(List<Integer>[] map, int[] preEdges, int size) {

        //bfs 遍历队列
        Deque<Integer> temp = new LinkedList<>();
        for (int i = 1; i < preEdges.length; i++) {
            if (preEdges[i] == 0) {
                temp.add(i);
            }
        }
        //如果入度为0的点不为1个，直接返回不能构造树
        //入度为0则存在环
        //入度大于1则存在分割的节点
        if (temp.size() != 1) {
            return false;
        }
        //通过索引查看是否遍历所有节点
        int index = 0;
        while (!temp.isEmpty()) {
            Integer curr = temp.pollFirst();
            index++;
            for (Integer edge : map[curr]) {
                //如果入度不为1，则无法构成树
                if (preEdges[edge] == 1) {
                    temp.addLast(edge);
                } else {
                    return false;
                }
            }
        }

        return index == size;
    }
}
