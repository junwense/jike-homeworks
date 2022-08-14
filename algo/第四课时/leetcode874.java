package com.sean.module4;

/**
 * @ClassName leetcode874
 * @Description: 爱吃香蕉的珂珂
 * @Author a9705
 * @Date 2022/8/13
 * @Version V1.0
 **/
public class leetcode874 {

    /**
     * 时间复杂度 O(nlog(m))
     * 空间复杂度 O(1)
     */
    public int minEatingSpeed(int[] piles, int h) {
        int start = 1, end = 1;
        for (int pile : piles) {
            //求出最大速度
            end = Math.max(pile, end);
        }
        while (start < end) {
            int mid = (start + end) / 2;
            if (vaild(piles, h, mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return end;
    }

    /**
     * @param piles 元素
     * @param h     允许最大小时
     * @param mid   吃香蕉的速度
     * @return
     */
    private boolean vaild(int[] piles, int h, int mid) {
        int count = 0;
        for (int pile : piles) {
            //向上取整
            count += (pile - 1 + mid) / mid;
        }
        return count <= h;
    }

}
