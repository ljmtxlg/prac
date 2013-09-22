package com.practice;

import java.util.HashMap;
import java.util.Map;

public class IntegerToRoman {
    private static Map<Integer, Character> intRomanMap = new HashMap<Integer, Character>(7);
    static {
    	intRomanMap.put(1, 'I');
        intRomanMap.put(5, 'V');
        intRomanMap.put(10, 'X');
        intRomanMap.put(50, 'L');
        intRomanMap.put(100, 'C');
        intRomanMap.put(500, 'D');
        intRomanMap.put(1000, 'M');
    }
	
    public String intToRoman(int num) {
    	if(num < 1) 
    		return null;
    	
        int digits = 0;
        int tmp = num;
        while(tmp != 0) {
        	digits++;
        	tmp /= 10;
        }
        
        StringBuffer sb = new StringBuffer();
        int power, quotient;
        while(num > 0) {
        	power = (int) Math.pow(10, digits);
        	quotient = num / power;
        	
        	sb.append(getRomanForDigit(power, quotient));
        	digits--;
        	num -= quotient*power;
        }
        
    	return sb.toString();
    }
    
    private String getRomanForDigit(int power, int quotient) {
    	StringBuffer sb = new StringBuffer();
        if(quotient < 9) {
        	if(quotient <= 3) {
        		while(quotient > 0) {
        			sb.append(intRomanMap.get(power));
        			quotient--;
        		}
        	} else if(quotient >= 5) {
        		sb.append(intRomanMap.get(5*power));
        		while(quotient > 5) {
        			sb.append(intRomanMap.get(power));
        			quotient--;
        		}
        	} else {
        		sb.append(intRomanMap.get(power)).append(intRomanMap.get(5*power));
        	}
        } else {
        	sb.append(intRomanMap.get(power)).append(intRomanMap.get(10*power));
        }
        
        return sb.toString();
    }
    
    public static void main(String[] args) {
    	int num = 49;
    	
    	System.out.println(new IntegerToRoman().intToRoman(num));
    }
}
