package com.live.zhf.leetcode;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int length = nums.length/2+1;
        int[] result = new int[length];
        int j=0;
        for (int i=0; i< nums.length-1; i++){
            for (int k=i+1; k< nums.length; k++){
                if((nums[i] + nums[k]) == target){
                    result[j] = i;
                    result[j+1] = k;
                    j+=2;
                }else {
                    continue;
                }
            }
       }
        int[] data = new int[j];
        for(int l=0; l<j; l++){
            data[l] =result[l];
        }
        return data;
    }
}
