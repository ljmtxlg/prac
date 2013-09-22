package com.practice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * content of each file is one number per line, sorted asc.
 * merge all these files' contents to one file, one number per line, sorted asc.
 * @author lvjing
 */
public class FileMerge {
	public static void main(String[] args) {
		FileMerge merge = new FileMerge();
		try {
			merge.fileMerge("/home/lvjing/workspace/testJava/output", 
					new File("/home/lvjing/workspace/testJava/1"), 
					new File("/home/lvjing/workspace/testJava/2"), 
					new File("/home/lvjing/workspace/testJava/3"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fileMerge(String outputFilePath, File...files) throws Exception {
		BufferedWriter outputFile = new BufferedWriter(new FileWriter(outputFilePath));
		
		List<BufferedReader> inputFiles = new ArrayList<BufferedReader>(files.length);
		for(int i = 0; i < files.length; i++) {
			inputFiles.add(new BufferedReader(new FileReader(files[i])));
		}
		
		List<Integer> lines = new ArrayList<Integer>(inputFiles.size());
		String line;
		for(int i = 0; i < inputFiles.size(); i++) {
			line = inputFiles.get(i).readLine();
			if(line != null) {
				lines.add(i, Integer.valueOf(line));
			} else {
				inputFiles.remove(i).close();
			}
		}
		
		int index, min;
		while(inputFiles.size() > 0) {
			index = 0;
			min = Integer.MAX_VALUE;
			for(int i = 0; i < lines.size(); i++) {
				if(lines.get(i) < min) {
					index = i;
					min = lines.get(i);
				} 
			}
			
			min = lines.remove(index);
			outputFile.write(String.valueOf(min));
			outputFile.newLine();
			
			line = inputFiles.get(index).readLine();
			while(line != null && Integer.valueOf(line) == min) {
				outputFile.write(line);
				outputFile.newLine();
				
				line = inputFiles.get(index).readLine();
			}
			if(line != null) {
				lines.add(index, Integer.valueOf(line));
			} else {
				inputFiles.remove(index).close();
			}
		}
		
		outputFile.close();
	}
}
