package com.xie.javacase.algorithm.sort;

/**
 * @author XieQC
 * 选择排序
 */
public class SelectionSort implements SortUtil.Sort {
	public void sort(int[] data) {   
	    int temp;   
	    for (int i = 0; i < data.length; i++) {   
	        int lowIndex = i;   
	        for (int j = data.length - 1; j > i; j--) {   
	            if (data[j] < data[lowIndex]) {   
	                lowIndex = j;   
	            }   
	        }   
	        SortUtil.swap(data,i,lowIndex);   
	    }   
	}
}
