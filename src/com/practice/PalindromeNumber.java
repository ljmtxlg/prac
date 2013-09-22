package com.practice;

public class PalindromeNumber {
    public boolean isPalindrome(int x) {
    	if(x < 0)
    		return false;
    	
    	boolean result = true;

    	int y = x;
        int digits = 0;
        while(y / 10 != 0) {
        	digits++;
        	y /= 10;
        }
        
        int start, end;
        while(x >= 10) {
        	start = (int) (x / Math.pow(10, digits));
        	end = x % 10;
        	
        	if(start == end) {
        		x -= start*Math.pow(10, digits);
        		x /= 10;
        		digits -= 2;
        	} else {
        		result = false;
        		break;
        	}
        }
        
    	return result;
    }
    
    public static void main(String[] args) {
    	int x = -2147447412;
    	
    	System.out.println(new PalindromeNumber().isPalindrome(x));
    }
}
