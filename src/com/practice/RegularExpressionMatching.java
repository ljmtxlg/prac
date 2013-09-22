package com.practice;

public class RegularExpressionMatching {
	//recursive impl, too many border cases to consider...
    public boolean isMatch(String s, String p) {
    	if(s.length() == 0 && p.length() == 0)
    		return true;
    	else if(p.length() == 0 && s.length() != 0)
    		return false;
    	else if(s.length() == 0 && p.length() != 0) {
    		if(p.length() % 2 == 0) {
    			int flag = 1;
    			for(int i = 1; i < p.length(); i += 2) {
    				if(p.charAt(i) == '*')
    					flag *= 1;
    				else 
    					flag *= 0;
    			}
    			if(flag == 1)
    				return true;
    			else
    				return false;
    		} else
    			return false;
    	}
    	
    	char pLast = p.charAt(p.length()-1);
    	if( pLast != '.' && pLast != '*' && pLast != s.charAt(s.length() - 1))
    		return false;
    	
        if(p.length() > 1) {
            if(p.charAt(0) == s.charAt(0) || p.charAt(0) == '.') {
            	if(p.charAt(1) == '*') 
            		return isMatch(s, p.substring(2))
            			|| isMatch(s.substring(1), p)
            			|| isMatch(s.substring(1), p.substring(2));
            	else
            		return isMatch(s.substring(1), p.substring(1));
            } else {
                if(p.charAt(1) == '*') {
                	return isMatch(s, p.substring(2));
                } else 
                	return false;
            }
        } else {
        	if(p.charAt(0) == '.')
        		return s.length() == 1;
        	else
        		return s.length() == 1 && p.charAt(0) == s.charAt(0);
        }
    }
    
    public static void main(String[] args) {
    	String s = "";
    	String p = "a*a*a*a*a*a*a*a*a*a*";
    	
    	System.out.println(new RegularExpressionMatching().isMatch(s, p));
    }
}
