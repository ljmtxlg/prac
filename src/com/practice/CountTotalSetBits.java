package com.practice;

/*
 * Given a positive integer n, count the total number of set bits in 
 * binary representation of all numbers from 1 to n.
 * 
 * http://www.geeksforgeeks.org/count-total-set-bits-in-all-numbers-from-1-to-n/
 * 
 * time complexity ??
 */
public class CountTotalSetBits {
    
    /*
     * Method 1, sum the count of set bits in all numbers from 1 to n
     */
    public int countSetBits(int n) {
        int total = 0;
        
        for(int i = 1; i <= n; i++) {
            total += getSetBits(i);
        }
        
        return total;
    }
    
    //count number of 1 bitwise for n's binary representation
    public int getSetBits(int n) {
        int count = 0;
        while(n > 0) {
            n = n & (n-1);  
            count++;
        }
        
        return count;
    }
    
    /*
     * Method 2: segmented count
     */
    public int countSetBits1(int n) {
        int m = getLeftMostBitPos(n);
        
        return countSetBits1R(n, m);
    }
    
    public int countSetBits1R(int n, int m) {
        if(n == 0)
            return 0;
        
        //it is a rule by observation
        if(n == ((1 << (m+1)) - 1)) {
            return (m+1) * (1 << m);
        }
        
        n = n - (1 << m);
        int nextM = getNextLeftMostBitPos(n, m);
        
        /*
         * three parts: 
         * left most position set bits n-(1<<m)+1; 
         * below 1<<m set bits m*(1<<(m-1));
         * set bits of smaller n which remove the left most 1 from last n.
         */
        return (n+1) + m * (1 << (m-1)) + countSetBits1R(n, nextM); 
    }
    
    //position m starts from 0, so use n > 1 as condition
    public int getLeftMostBitPos(int n) {
        int m = 0;
        while(n > 1) {
            n >>= 1;
            m++;
        }
        return m;
    }
    
    //use last left most position m of bigger n to find the left most bit position for smaller n.
    //cause of : n = n - (1 << m)
    public int getNextLeftMostBitPos(int n, int m) {
        int tmp = 1 << m;
        while(tmp > n) {
            tmp >>= 1;
            m--;
        }
        
        return m;
    }
    
    public static void main(String[] args) {
        int n = 4;
        CountTotalSetBits ctsb = new CountTotalSetBits();
        System.out.println(ctsb.countSetBits1(n));
    }
}
