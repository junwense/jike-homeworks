package com.sean;

import java.util.*;

/**
 * @ClassName leetcode47
 * @Description: TODO
 * @Author a9705
 * @Date 2022/7/30
 * @Version V1.0
 **/
public class leetcode47_1 {


    List<List<Integer>> ans = new ArrayList<>();
    boolean[] visite;
    List<Integer> temp = new ArrayList<>();
    int size = 0;

    public static void main(String[] args) {
        leetcode47_1 leetcode47_1 = new leetcode47_1();
        leetcode47_1.permuteUnique(new int[]{1, 1, 2});
    }


    /**
     * 空间复杂度 2n
     * 时间复杂度 n*n!
     * 剪支可以节约大部分时间
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        size = nums.length;
        visite = new boolean[size];
        Arrays.sort(nums);
        doUnique(nums, 0);
        return ans;
    }

    private void doUnique(int[] nums, int index) {
        if (index == size) {
            ans.add(new ArrayList<>(temp));
        }

        for (int i = 0; i < size; i++) {
            // 使用快排后进行减支，
            // 如果当前元素没被访问过
            // 如果上个元素和这个元素相同，并且上个元素被使用过
            // (假设我们有 3 个重复数排完序后相邻，那么我们一定保证每次都是拿从左往右第一个未被填过的数字)
            // 比如[1,1,1] 进行排列
            // 顺序如下
            // [未填入，未填入，未填入] 、[填入，未填入，未填入]、
            //  [填入，填入，未填入]、[填入，填入，填入]

            if (visite[i] || (i > 0 && nums[i] == nums[i - 1] && !visite[i - 1])) {
                continue;
            }
            temp.add(nums[i]);
            visite[i] = true;
            doUnique(nums, index + 1);
            visite[i] = false;
            temp.remove(index);
        }
    }
}
