package com.sean;

import java.util.Map;
import java.util.TreeMap;

/**
 * @ClassName leetcode697
 * @Description: TODO
 * @Author a9705
 * @Date 2022/7/30
 * @Version V1.0
 **/
public class leetcode697 {

    public static void main(String[] args) {
        leetcode697 leetcode697=new leetcode697();
        leetcode697.findShortestSubArray(new int[]{1,3,1});
    }

    /**
     * 空间复杂度 2n
     * 时间复杂度 2n
     */
    public int findShortestSubArray(int[] nums) {
        int ans=0;
        Map<Integer,int[]> cache=new TreeMap<>();
        //生成缓存队列
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if(cache.containsKey(num)){
                int[] ints = cache.get(num);
                ints[0]+=1;
                ints[2]=i;
            }else{
                cache.put(num,new int[]{1,i,i});
            }
        }

        int count=0;
        int arraySize=0;
        //处理合并后的数据
        for (Map.Entry<Integer, int[]> entry : cache.entrySet()) {
            int[] value = entry.getValue();
            if(value[0]==count){
                arraySize=Math.min(arraySize,value[2]-value[1]+1);
                continue;
            }
            if(value[0]>count){
                count=value[0];
                arraySize=value[2]-value[1]+1;
            }
        }

        return arraySize;
    }
}
