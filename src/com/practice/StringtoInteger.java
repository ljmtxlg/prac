package com.practice;

//Need to exercise once more.
public class StringtoInteger {
    public int atoi(String str) {
    	//str is null or length 0
    	if(str == null || str.length() == 0)
    		return 0;
    	
    	//skip white space
    	int i = 0;
    	for( ; str.charAt(i) == ' '; i++);
    	
    	if(i == str.length())
    		return 0;
    	
    	long result = 0;
    	//check sign
    	int signFlag = 1;
    	char ch = str.charAt(i++);
    	if(ch == '-')
    		signFlag = -1;
    	else if(ch == '+')
    		signFlag = 1;
    	else if(ch >= '0' && ch <= '9')
    		result += ch - '0';
    	else 
    		return 0;
    		
    	//calculate 
        for(; i < str.length(); i++) {
        	ch = str.charAt(i);
        	if(ch >= '0' && ch <= '9')
         		result = result * 10 + ch - '0';
        	else
        		break;
        }

        //check if over range
        result *= signFlag;
        if(result > Integer.MAX_VALUE)
        	return Integer.MAX_VALUE;
        else if(result < Integer.MIN_VALUE)
        	return Integer.MIN_VALUE;
        else
        	return (int) result;
    }
    
    public static void main(String[] args) {
    	String str = "2147483648";
    	
    	System.out.println(new StringtoInteger().atoi(str));
    }
}
