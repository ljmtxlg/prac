package com.practice;

public class LongestPalindrome {
	
    public String longestPalindrome(String s) {
    	int length = s.length();
        boolean[][] isPalindrome = new boolean[length+1][length+1];
        int maxLength = 0, maxStart = 0;
        
        //l as length, st as start
        for(int l = 0; l <= length; l++)
        	for(int st = 0; st <= length - l; st++) {
        		if(l <= 1)
        			isPalindrome[st][l] = true;
        		else 
        			isPalindrome[st][l] = isPalindrome[st+1][l-2] && (s.charAt(st) == s.charAt(st+l-1));
        		
        		if(isPalindrome[st][l] && l > maxLength) {
        			maxLength = l;
        			maxStart = st;
        		}
        	}
    	
    	return s.substring(maxStart, maxStart + maxLength);
    }
    
    
    public static void main(String[] args) {
    	String s = "baabbcca";
    	
    	System.out.println(new LongestPalindrome().longestPalindrome(s));
    }

}
