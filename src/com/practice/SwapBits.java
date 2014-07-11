package com.practice;

/*
 * Given a number x and two positions (from right side) in binary representation of x, 
 * write a function that swaps n bits at given two positions and returns the result. 
 * It is also given that the two sets of bits do not overlap.
 * 
 * http://www.geeksforgeeks.org/swap-bits-in-a-given-number/
 */
public class SwapBits {
    public void swapNumbers(int x, int y) {
        x ^= y;
        y = x ^ y;
        x = x ^ y;
    }
    
    //xor, if 2 bits different, result is 1, if 2 bits same, result is 0.
    public int swapBits(int x, int p1, int p2, int n) {
        int set1 = (x >> p1) & ((1 << n) - 1);
        int set2 = (x >> p2) & ((1 << n) - 1);
        int xor = set1 ^ set2;
        xor = (xor << p1) | (xor << p2);
        int result = x ^ xor;
        
        return result;
    }
    
    /*
     * Write a function Add() that returns sum of two integers. 
     * The function should not use any of the arithmetic operators (+, ++, –, -, .. etc).
     * 
     * http://www.geeksforgeeks.org/add-two-numbers-without-using-arithmetic-operators/
     */
    public int add2Numbers(int x, int y) {
        int carry;
        while(y != 0) {
            carry = x & y;
            x = x ^ y;
            y = carry << 1;
        }
        
        return x;
    }
    
    public static void main(String[] args) {
        System.out.println(new SwapBits().swapBits(47, 1, 5, 3));
    }
}
