package com.practice;

public class KMP {
    public int kmp(String s, String p) {
        int[] nextStep = getNextStep(p);
        if(nextStep == null) 
            return -1;
        
        int sIndex = 0, pIndex = 0;
        while((sIndex + pIndex) < s.length() && pIndex < p.length()) {
            if(s.charAt(sIndex + pIndex) == p.charAt(pIndex)) 
                pIndex++;
            else {
                sIndex += (pIndex - nextStep[pIndex]);
                if(nextStep[pIndex] >= 0)
                    pIndex = nextStep[pIndex];
                else
                    pIndex = 0;
            }
        }
        
        if(pIndex == p.length()) {
            return sIndex;
        } else {
            return -1;
        }
    }
    
    public int[] getNextStep(String p) {
        if(p.length() == 0) 
            return null;
        
        int[] nextStep = new int[p.length()];
        nextStep[0] = -1;
        nextStep[1] = 0;
        int i = 2; //position of first unmatched char
        int j = 0; //next index of overlay chars (overlay: both prefix and suffix of already matched), 
                   //if match, length of overlay chars will be j+1
        
        while(i < p.length()) {
            if(p.charAt(j) == p.charAt(i-1)) {
                nextStep[i++] = ++j;
            } else if(j > 0) {
                j = nextStep[j];
            } else {
                nextStep[i++] = 0;
            }
        }
        
        return nextStep;
    }
    
    public static void main(String[] args) {
        String s = "annbcdanacadsannannacanna";
        String p = "annacanna";
        
        int[] next = new KMP().getNextStep(p);
        for(int i : next)
            System.out.println(i);
        
        System.out.println(new KMP().kmp(s, p));
    }
}
