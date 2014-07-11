package com.practice;

import java.util.Arrays;
import java.util.List;

/**
 * You have an array of english words {cat, then, hen, end, dog}. 
 * Can you make out if the given sentence is a concatenation of only words from the array?
 * (eg: Cathen valid, thend  not valid, cathenend  valid)
 *
 */
public class Concatenation {
    
    private boolean isConcatenation(List<String> words, String sentence) {
        boolean result = false;
        
        sentence = sentence.toLowerCase();
        for(String word : words) {
            if(sentence.startsWith(word)) {
                if(sentence.length() > word.length()) {
                    result = result || isConcatenation(words, sentence.substring(word.length()));
                } else {
                    result = true;
                }
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        List<String> words = Arrays.asList("cat", "then", "hen", "end", "dog");
        String sentence = "thend";
        
        System.out.println(new Concatenation().isConcatenation(words, sentence));
    }
}
