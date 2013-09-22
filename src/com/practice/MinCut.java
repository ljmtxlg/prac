package com.practice;


public class MinCut {
	public int minCut(String s) {
        int n = s.length();
        int[] part = new int[n];
        boolean[][] isPalindrome = new boolean[n][n];
        
        //j as start index, i + 1 as length
        for(int i = 0; i < n; i++) {
        	for(int j = 0; j < n - i; j++) {
        		if(i < 1)
        			isPalindrome[j][i] = true;
        		else 
        			isPalindrome[j][i] = (s.charAt(j) == s.charAt(j + i) && isPalindrome[j + 1][i - 2]);
        	}
        }
        
        //j as start index, i + 1 as length
        for(int i = 0; i < n; i++) {
        	part[i] = Integer.MAX_VALUE;
        	if(isPalindrome[0][i]) {
        		part[i] = 1;
        	}
        	for(int j = i; j >= 0; j--) {
    			if(j > 0 && isPalindrome[j][i - j]) {
        			part[i] = Math.min(part[i], part[j - 1] + 1);
        		}
        	}
        }
        
        return part[n - 1] - 1;
    }
	
	public static void main(String[] args) {
		MinCut s = new MinCut();
		System.out.println(s.minCut("12321545"));
		System.out.println(s.minCut("cabababcbc"));
	}
}
