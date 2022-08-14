package com.sean.module4;

/**
 * @ClassName leetcode1011
 * @Description: 在 D 天内送达包裹的能力
 * @Author a9705
 * @Date 2022/8/13
 * @Version V1.0
 **/
public class leetcode1011 {

    /**
     * 时间复杂度 O(nlog(m))
     * 空间复杂度 O(1)
     */
    public int shipWithinDays(int[] weights, int days) {
        int start = 1;
        int end = 0;
        for (int weight : weights) {
            start = Math.max(start, weight);
            end += weight;
        }

        while (start < end) {
            int mid = (start + end) / 2;
            if (vaild(weights, days, mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return end;
    }

    /**
     *
     * @param weights 元素
     * @param days 使用天数
     * @param mid 当前最大运输量
     * @return
     */
    private boolean vaild(int[] weights, int days, int mid) {
        //当前使用天数
        int count = 0;
        //当前运输重量
        int curr = 0;
        for (int weight : weights) {
            if (curr + weight == mid) {
                count++;
                curr = 0;
            } else if (curr + weight > mid) {
                count++;
                curr = weight;
            } else {
                curr += weight;
            }
        }

        //把最后的数据处理一次
        if(curr>0){
            count++;
        }

        return count <= days;
    }
}
