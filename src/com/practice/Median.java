package com.practice;


//Need to exercise once more.
public class Median {
	
	public double findMedianSortedArrays(int A[], int B[]) {
		int m = A.length;
		int n = B.length;
		int total = m + n;
		
		if((total & 1) == 0) {
			return (findMedianSortedArrays(A, 0, m-1, B, 0, n-1, (m+n)/2) + findMedianSortedArrays(A, 0, m-1, B, 0, n-1, (m+n-1)/2)) / 2.0;
		} else {
			return findMedianSortedArrays(A, 0, m-1, B, 0, n-1, (m+n)/2);
		}
    }
	
	public int findMedianSortedArrays(int A[], int startA, int endA, int B[], int startB, int endB, int k) {
		if(endA - startA < endB - startB) {
			return findMedianSortedArrays(B, startB, endB, A, startA, endA, k);
		}
		
		if( startB > endB) {
			return A[startA + k];
		}
		
		int totalSize = endA - startA + endB - startB + 2;
		if(totalSize <= 6) {
			int[] tmp = new int[totalSize];
			int i = 0;
			while(startA <= endA && startB <= endB) {
				if(A[startA] <= B[startB]) {
					tmp[i++] = A[startA];
					startA++;
				} else {
					tmp[i++] = B[startB];
					startB++;
				}
			}
			if(startA <= endA) {
				for(int j = startA; j <= endA; j++) {
					tmp[i++] = A[j];
				}
			} else if(startB <= endB){
				for(int j = startB; j <= endB; j++) {
					tmp[i++] = B[j];
				}
			}
			
			return tmp[k];
		}
		
		int k1 = k/2;
		int k2 = k - k1;
		if(k2 + startB > endB){
			k2 = endB - startB;
			k1 = k - k2;
		}
		int kb = k2 + startB;
		int ka = k1 + startA;
		
		if(A[ka] > B[kb]) {
			return findMedianSortedArrays(A, startA, ka-1, B, kb, endB, k-k2);
		} else if(A[ka] < B[kb]) {
			return findMedianSortedArrays(A, ka, endA, B, startB, kb-1, k-k1);
		} else {
			return A[ka];
		}
	}
    
    
    public static void main(String[] args) {
    	int[] a = {1};
    	int[] b = {2, 3, 4, 5, 6, 7};
    	System.out.println(new Median().findMedianSortedArrays(a, b));
    }
}
