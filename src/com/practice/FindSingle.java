package com.practice;

/*
 * Given an array where every element occurs three times, except one element which occurs only once. 
 * Find the element that occurs once. Expected time complexity is O(n) and O(1) extra space.
 *
 * http://www.geeksforgeeks.org/find-the-element-that-appears-once/
 */
public class FindSingle {
    public int getSingle(int[] arr) {
        int ones = 0, twos = 0, common;
        
        for(int i = 0; i < arr.length; i++) {
            twos |= (ones & arr[i]);
            ones ^= arr[i];
            common = ~(ones & twos);
            ones &= common;
            twos &= common;
        }
        
        return ones;
    }
    
    public int getSingle2(int[] arr) {
        int INT_SIZE = 32;
        int result = 0, bit = 0, bitCount;
        
        for(int i = 0; i < INT_SIZE; i++) {
            bitCount = 0;
            bit = 1 << i;
            for(int j = 0; j < arr.length; j++) {
                bitCount += (arr[j] & bit);
            }
            
            if(bitCount % 3 > 0)
                result |= bit;
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        int[] arr = {12, 1, 12, 3, 12, 1, 1, 2, 3, 3};
        
        FindSingle fs = new FindSingle();
        System.out.println(fs.getSingle(arr));
        System.out.println(fs.getSingle2(arr));
    }
    
    
    /*
     * Given two signed integers, write a function that returns true if the signs of 
     * given integers are different, otherwise false. 
     * The function should not use any of the arithmetic operators.
     *
     * http://www.geeksforgeeks.org/detect-if-two-integers-have-opposite-signs/
     */
    public boolean oppositeSigns(int x, int y) {
        return (x ^ y) < 0;
        //return x > 0 ? y <= 0 : y > 0;
    }
}
