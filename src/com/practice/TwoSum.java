package com.practice;


public class TwoSum {
	public static class Pair {
		public int value;
		public int index;
	}
	
	public int[] twoSum(int[] numbers, int target) {
		int[] indexes = new int[numbers.length];
		for(int i = 0; i < numbers.length; i++) {
			indexes[i] = i+1;
		}
        quickSort(numbers, indexes, 0, numbers.length-1);
        
        int i = 0, j = numbers.length-1;
        int sum = 0;
        while( i < j) {
            sum = numbers[i] + numbers[j];
            if(sum == target) {
            	int index1 = indexes[i];
            	int index2 = indexes[j];
            	return index1 < index2 ? new int[]{index1, index2} : new int[]{index2, index1};
            } else if(sum > target) {
                j--;
            } else {
                i++; 
            }
        }
        
        return null;
    }
    
    public void quickSort(int[] arrays, int[] indexes, int start, int end) {
        if(start < end) {
            int middle = (start + end)/2;
            int pivot = arrays[middle];
            int pivotIndex = indexes[middle];
            arrays[middle] = arrays[start];
            indexes[middle] = indexes[start];
            int i = start, j = end;
            while( i < j) {
                for( ; arrays[j] > pivot && i < j; j--);
                if(i < j) {
                    arrays[i] = arrays[j];
                    indexes[i] = indexes[j];
                    i++;
                }
                for( ; arrays[i] < pivot && i < j; i++);
                if(i < j) {
                    arrays[j] = arrays[i];
                    indexes[j] = indexes[i];
                    j--;
                }
            }
            arrays[i] = pivot;
            indexes[i] = pivotIndex;
            
            quickSort(arrays, indexes, start, i-1);
            quickSort(arrays, indexes, i+1, end);
        }
    }
    
    public static void main(String[] args) {
    	int[] numbers = {2,1,9,4,4,56,90,3};
    	int target = 8;
//    	new TwoSum().quickSort(numbers, 0, numbers.length-1);
//    	for(int i = 0; i < numbers.length; i++) {
//    		System.out.println(numbers[i]);
//    	}
    	int[] result = new TwoSum().twoSum(numbers, target);
    	for(int i = 0; i < result.length; i++) {
    		System.out.println(result[i]);
    	}
    }
}
