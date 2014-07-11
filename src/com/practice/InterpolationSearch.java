package com.practice;

public class InterpolationSearch {
    public int interpolationSearch(int[] sortedArr, int target) {
        int low = 0, high = sortedArr.length-1;
        int mid;
        while(low < high) {
            mid = low + (high - low) * (target - sortedArr[low]) / (sortedArr[high] - sortedArr[low]);
            if(sortedArr[mid] == target) 
                return mid;
            else if(sortedArr[mid] > target)
                high = mid - 1;
            else
                low = mid + 1;
        }
        
        return -1;
    }
    
    public static void main(String[] args) {
        int target = 44;
        int[] array = {1, 12, 23, 34, 37, 44, 53, 64, 71};
        
        System.out.println(new InterpolationSearch().interpolationSearch(array, target));
    }
}
