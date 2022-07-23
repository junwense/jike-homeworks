package com.sean;

/**
 * @ClassName leetcode66
 * @Description: TODO
 * @Author a9705
 * @Date 2022/7/23
 * @Version V1.0
 **/
public class leetcode66 {

    /**
     * 时间复杂度 O(n)
     * 空间复杂度 O(2n)
     */
    public int[] plusOne(int[] digits) {
        int carry = 0;
        int size = digits.length;

        //从后往前遍历
        for (int i = size - 1; i >= 0; i--) {
            //最后一位先执行+1操作
            if (i == size - 1) {
                digits[i] += 1;
            }
            //保存当前的值
            int temp = digits[i];
            //计算除以10的余数，这一步主要还需要算上进位值
            digits[i] = (digits[i] + carry) % 10;
            //计算进位值
            carry = (temp + carry) / 10;
            //如果为0则不需要再进位了，直接返回
            if (carry == 0) {
                return digits;
            }
        }

        //最后进位需要重新copy数组，首位设置为1，因为只有+1，所以原来数组都是0
        if (carry == 1) {
            digits = new int[size + 1];
            digits[0] = 1;
        }
        return digits;
    }
}
