package com.d.erp;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ArraySingleNumberTest {

    @Test
    public void test( ){
        int[] nums =new int[]{-336,513,-560,-481,-174,101,-997,40,-527,-784,-283,-336,513,-560,-481,-174,101,-997,40,-527,-784,-283,354};
        int result = solution(nums);
        System.out.println(result);
    }

    public int solution(int [] nums){

        if(nums.length<3){
            return nums[0];
        }
        Arrays.sort(nums);
        for( int i = 0 ;i< nums.length ;){
            int k = 1;
            int j = i +k;
            while (j<nums.length){
                if(nums[i] != nums[j]){
                    if(k == 1){
                        return  nums[i];
                    }
                    i = i+k;
                    if(i + k == nums.length -1){
                        return nums[i+k];
                    }
                    break;
                }else{
                    k++;
                    j = i +k;
                }

            }
        }
        return nums[0];
    }

}
