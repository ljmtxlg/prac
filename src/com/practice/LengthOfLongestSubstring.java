package com.practice;

import java.util.HashSet;
import java.util.Set;

public class LengthOfLongestSubstring {
	
    public int lengthOfLongestSubstring(String s) {
        Set<Character> uniques = new HashSet<Character>();
        int i = 0, j = 0, max = 0;
        while (j < s.length()) {
        	char c = s.charAt(j++);

    		while(uniques.contains(c)) {
    			uniques.remove(s.charAt(i++));
    		}

    		uniques.add(c);
        	if(uniques.size() > max)
        		max = uniques.size();
        }
    	
    	return max;
    }
    
    public static void main(String[] args) {
    	String s = "abcabcde";
    	System.out.println(new LengthOfLongestSubstring().lengthOfLongestSubstring(s));
    }
}
