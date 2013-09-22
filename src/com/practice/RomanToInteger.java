package com.practice;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
    private static Map<Character, Integer> romanIntMap = new HashMap<Character, Integer>(7);
    static {
    	romanIntMap.put('I', 1);
        romanIntMap.put('V', 5);
        romanIntMap.put('X', 10);
        romanIntMap.put('L', 50);
        romanIntMap.put('C', 100);
        romanIntMap.put('D', 500);
        romanIntMap.put('M', 1000);
    }
    
    public int romanToInt(String s) {
        int num = 0;
        
        char c, ca;
        int n, na;
        int i = 0;
        while(i < s.length()) {
        	c = s.charAt(i++);
        	if((i < s.length()) && (c == 'I' || c == 'X' || c == 'C')) {
        		ca = s.charAt(i);
        		n = romanIntMap.get(c);
        		na = romanIntMap.get(ca);
        		if(na > n) {
        			num += (na - 2*n);
        			i++;
        		} 
        	} 
        	num += romanIntMap.get(c);
        }
        
        return num;
    }
    
    public static void main(String[] args) {
    	String roman = "LXXIX";
    	
    	System.out.println(new RomanToInteger().romanToInt(roman));
    }
}
