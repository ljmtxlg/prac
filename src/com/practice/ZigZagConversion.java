package com.practice;

public class ZigZagConversion {
	
    public String convert(String s, int nRows) {
        int length = s.length();
        int partLength = nRows + nRows - 2;
        if(length == 0 || partLength == 0)
        	return s;
        
        int part = length / partLength;
        int remain = length % (nRows + nRows - 2);
        int nCols = part * (1 + nRows - 2) + (remain > nRows ? 1 + remain - nRows : 1);
        
        char[][] str = new char[nRows][nCols];
        int row = 0, col = 0, num = 0;
        while(part > 0){ 
        	for(; row < nRows; row++){
        		str[row][col] = s.charAt(num++);
        	}
        	for(row -= 2, col += 1; row > 0; row--, col++) {
        		str[row][col] = s.charAt(num++);
        	}
        	
        	part--;
        }
        if(remain > nRows) {
        	for(; row < nRows; row++){
        		str[row][col] = s.charAt(num++);
        	}
        	row -= 2;
        	col += 1;
        	remain -= nRows;
        	while(remain > 0) {
        		str[row--][col++] = s.charAt(num++);
        		remain--;
        	}
        } else {
        	for(; row < remain; row++){
        		str[row][col] = s.charAt(num++);
        	}
        } 
        
        StringBuffer sb = new StringBuffer();
        for(row = 0; row < nRows; row++)
        	for(col = 0; col < nCols; col++) {
        		if(str[row][col] != '\u0000')
        			sb.append(str[row][col]);
        	}
        
    	return sb.toString(); 
    }

    public static void main(String[] args) {
//    	String s = "PAYPALISHIRING";
    	String s = "abcde";
    	System.out.println(new ZigZagConversion().convert(s, 4));
    }
}
