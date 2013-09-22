package com.practice;

public class Sort {
	public static void main(String[] args) {
		int[] arrays = {2, 7, 5, 3, 8, 10, 6, 4, 15, 1, 1, 23, 15, 47, 89, 102};
		Sort sort = new Sort();
		sort.radixSort(arrays, true);
		for(int i = 0 ; i < arrays.length; i++) {
			System.out.print(arrays[i] + "\t");
		}
		System.out.println();
		sort.radixSort(arrays, false);
		for(int i = 0 ; i < arrays.length; i++) {
			System.out.print(arrays[i] + "\t");
		}
	}
	
	public void radixSort(int[] arrays, boolean asc) {
		int[] result = new int[arrays.length];
		int[] count = new int[10]; //cause every bit range from 0 to 9.
		int max = 0, exp = 1;
		for(int i = 0; i < arrays.length; i++) {
			if(arrays[i] > max) {
				max = arrays[i];
			}
		}
		
		while(max / exp > 0) {
			for(int i = 0; i < count.length; i++) {
				count[i] = 0;
			}
			for(int i = 0; i < arrays.length; i++) {
				count[arrays[i]/exp % 10] += 1;
			}
			for(int i = 1; i < count.length; i++) {
				count[i] += count[i-1];
			}
			for(int i = arrays.length - 1; i >= 0; i--) {
				result[--count[arrays[i]/exp % 10]] = arrays[i];
			}
			for(int i = 0; i < arrays.length; i++) {
				arrays[i] = result[i];
			}
			
			exp *= 10;
		}
		
		if(!asc) {
			int end = arrays.length - 1;
			for(int i = end; i >= 0; i--) {
				arrays[end-i] = result[i];
			}
		}
	}
	
	public void countingSort(int[] arrays, boolean asc) {
		int[] count = new int[11]; //cause arrays[i] range from 0 to 10.
		for(int i = 0; i < count.length; i++) {
			count[i] = 0;
		}
		
		for(int i = 0; i < arrays.length; i++) {
			count[arrays[i]] += 1;
		}
		
		if(asc) {
			for(int i = 0, j = 0; i < count.length; i++) {
				while(count[i] > 0) {
					arrays[j++] = i;
					count[i]--;
				}
			}
		} else {
			for(int i = count.length - 1, j = 0; i >= 0; i--) {
				while(count[i] > 0) {
					arrays[j++] = i;
					count[i]--;
				}
			}
		}
	}
	
	public void quickSort(int[] arrays, boolean asc) {
		quickSort1(arrays, 0, arrays.length - 1, asc);
	}
	
	//pivot放最后，从左往右扫描，小于pivot的都放左边，记录左右分界
	private void quickSort(int[] a, int start, int end, boolean asc) {
		if(start < end) {
			int pivot = a[end];
			int storeIndex = start, tmp;
			for(int i = start; i < end; i++) {
				if(asc) {
					if(a[i] < pivot) {
						tmp = a[storeIndex];
						a[storeIndex] = a[i];
						a[i] = tmp;
						
						storeIndex++;
					}
				} else {
					if(a[i] > pivot) {
						tmp = a[storeIndex];
						a[storeIndex] = a[i];
						a[i] = tmp;
						
						storeIndex++;
					}
				}
			}
			a[end] = a[storeIndex];
			a[storeIndex] = pivot;
			
			quickSort(a, start, storeIndex-1, asc);
			quickSort(a, storeIndex+1, end, asc);
		}
	}
	
	//挖坑填数，pivot的值随机选，选完和第一个交换，相当于pivot总是第一个数
	private void quickSort1(int[] a, int start, int end, boolean asc) {
		if(start < end) {
			int pivot = a[(start+end)/2];
			a[(start+end)/2] = a[start];
			a[start] = pivot;
			int i = start, j = end;
			while(i < j) {
				if(asc) {
					for(; a[j] > pivot && i < j; j--);
					if(i < j) {
						a[i] = a[j];
						i++;
					} 
					for(; a[i] < pivot && i < j; i++);
					if(i < j) {
						a[j] = a[i];
						j--;
					}
				} else {
					for(; a[j] < pivot && i < j; j--);
					if(i < j) {
						a[i] = a[j];
						i++;
					} 
					for(; a[i] > pivot && i < j; i++);
					if(i < j) {
						a[j] = a[i];
						j--;
					}
				}
			}
			a[i] = pivot;
			
			quickSort1(a, start, i-1, asc);
			quickSort1(a, i+1, end, asc);
		}
	}
	
	public void mergeSort(int[] arrays, boolean asc) {
		int[] result = mergeSort(arrays, 0, arrays.length-1, asc);
		for(int i = 0 ; i < result.length; i++) {
			System.out.print(result[i] + "\t");
		}
	}
	
	private int[] mergeSort(int[] a, int start, int end, boolean asc) {
		if(start < end) {
			int middle = (start + end) / 2;
			int[] left = mergeSort(a, start, middle, asc);
			int[] right = mergeSort(a, middle + 1, end, asc);
			
			int size = left.length + right.length;
			int[] result = new int[size];
			int i = 0, j = 0, k = 0;
			while(k < size) {
				if(asc) {
					if(left[i] < right[j]) {
						result[k++] = left[i++];
					} else {
						result[k++] = right[j++];
					}
				} else {
					if(left[i] > right[j]) {
						result[k++] = left[i++];
					} else {
						result[k++] = right[j++];
					}
				}
				
				if(i == left.length) {
					while(j < right.length) {
						result[k++] = right[j++];
					}
				} else if(j == right.length) {
					while(i < left.length) {
						result[k++] = left[i++];
					}
				}
			}
			
			return result;
		} else {
			return new int[]{a[start]};
		}
	}
	
	public void bubbleSort(int[] arrays, boolean asc) {
		int tmp;
		for(int i = arrays.length - 1; i >= 0; i--) {
			for(int j = 0; j < i; j++) {
				if(asc) {
					if(arrays[j] > arrays[j+1]) {
						tmp = arrays[j];
						arrays[j] = arrays[j+1];
						arrays[j+1] = tmp;
					}
				} else {
					if(arrays[j] < arrays[j+1]) {
						tmp = arrays[j];
						arrays[j] = arrays[j+1];
						arrays[j+1] = tmp;
					}
				}
			}
		}
	}
	
	public void selectionSort(int[] arrays, boolean asc) {
		int markIndex, mark;
		for(int i = 0; i < arrays.length; i++) {
			mark = arrays[i];
			markIndex = i;
			for(int j = i; j < arrays.length; j++) {
				if(asc) {
					if(arrays[j] < mark) {
						mark = arrays[j];
						markIndex = j;
					}
				} else {
					if(arrays[j] > mark) {
						mark = arrays[j];
						markIndex = j;
					}
				}
			}
			
			arrays[markIndex] = arrays[i];
			arrays[i] = mark;
		}
		
	}
	
	public void insertionSort(int[] arrays, boolean asc) {
		int key;
		for(int i = 1; i < arrays.length; i++) {
			key = arrays[i];
			
			int j;
			for(j = i - 1; j >= 0; j--) {
				if(asc) {
					if(arrays[j] > key) {
						arrays[j + 1] = arrays[j];
					} else {
						break;
					}
				} else {
					if(arrays[j] < key) {
						arrays[j + 1] = arrays[j];
					} else {
						break;
					}
				}
			}
			arrays[j + 1] = key;
		}
	}
	
	public void shellSort() {
		//TODO: not use frequently
	}
	
	public void heapSort() {
		//TODO: not use frequently
	}
}
