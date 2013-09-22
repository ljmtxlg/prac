package com.practice;

public class ReverseInteger {
    public int reverse(int x) {
    	if(x > -10 && x < 10)
    		return x;
    	
        int result = 0;
        int flag = 1;
        if(x < 0)
        	flag = -1;
        
        x = Math.abs(x);
        int remain = 0;
        while(x > 0) {
        	remain = x % 10;
        	result = result*10 + remain;
        	
        	x = x / 10;
        }
        
        return result * flag;
    }
    
    public static void main(String[] args) {
    	int x = -123;
    	System.out.println(new ReverseInteger().reverse(x));
    }
}
