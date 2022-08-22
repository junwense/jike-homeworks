package com.sean.module5_1;

/**
 * @ClassName leetcode327
 * @Description: 区间和的个数
 * @Author a9705
 * @Date 2022/8/23
 * @Version V1.0
 **/
public class leetcode327 {
    /**
     * 时间复杂度 O(nlogn)
     * 空间复杂度 O(n)
     */
    public int countRangeSum(int[] nums, int lower, int upper) {

        int size = nums.length;
        long[] preSum = new long[size + 1];
        for (int i = 1; i < size + 1; i++) {
            preSum[i] = nums[i - 1] + preSum[i - 1];
        }
        return mergeSort(preSum, 0, size, lower, upper);
    }

    public static int mergeSort(long[] array, int left, int right, int lower, int upper) {
        if (right <= left) {
            return 0;
        }
        int mid = (left + right) >> 1;
        int ans = 0;
        ans += mergeSort(array, left, mid, lower, upper);
        ans += mergeSort(array, mid + 1, right, lower, upper);

        //在归并排序的时候，对左右子数组进行前缀和差值求解，具体思路还是需要在多看几遍
        int start = mid + 1;
        int end = mid + 1;
        for (int i = left; i <= mid; i++) {
            while (start <= right && array[start] - array[i] < lower) {
                start++;
            }
            while (end <= right && array[end] - array[i] <= upper) {
                end++;
            }
            ans += end - start;
        }

        merge(array, left, mid, right);
        return ans;
    }

    public static void merge(long[] arr, int left, int mid, int right) {
        long[] temp = new long[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        for (int p = 0; p < temp.length; p++) {
            arr[left + p] = temp[p];
        }
    }
}
