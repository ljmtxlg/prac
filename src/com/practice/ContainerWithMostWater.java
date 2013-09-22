package com.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ContainerWithMostWater {
	public class Pair {
		int value;
		int index;
		
		public Pair(int value, int index) {
			this.value = value;
			this.index = index;
		}
	}
	
    public int maxArea(int[] height) {
        int n = height.length;
        
        List<Pair> list = new ArrayList<Pair>(n);
        for(int i = 0; i < n; i++) 
        	list.add(new Pair(height[i], i));
        
        Collections.sort(list, new Comparator<Pair>() {
			@Override
			public int compare(Pair p0, Pair p1) {
				if(p0.value > p1.value)
					return -1;
				else if(p0.value < p1.value)
					return 1;
				else 
					return 0;
			}
        });
        
        int maxArea = 0, tmpArea;
        int init = list.get(0).index;
        int maxIndex = init, minIndex = init;
        for(int i = 1; i < list.size(); i++) {
        	Pair p = list.get(i);
        	tmpArea = Math.max(p.value * Math.abs(p.index - maxIndex), p.value * Math.abs(p.index - minIndex));
        	
        	if(tmpArea > maxArea)
        		maxArea = tmpArea;
        	if(p.index > maxIndex)
        		maxIndex = p.index;
        	if(p.index < minIndex)
        		minIndex = p.index;
        }
        
        return maxArea;
        
//        int max = 0, tmp;
//        for(int i = 0; i < n-1; i++) {
//        	for(int j = i + 1; j < n; j++) {
//        		tmp = Math.min(height[i], height[j]) * (j - i);
//        		
//        		if(tmp > max)
//        			max = tmp;
//        	}
//        }
//        return max;
    }
    
    public static void main(String[] args) {
    	int[] height = {1, 2, 8, 6, 3, 4, 5};
    	
    	System.out.println(new ContainerWithMostWater().maxArea(height));
    }
}
