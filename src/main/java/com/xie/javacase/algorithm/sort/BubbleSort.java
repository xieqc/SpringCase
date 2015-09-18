package com.xie.javacase.algorithm.sort;

/**
 * @author XieQC
 * 冒泡排序
 * (N–1) + (N–2) + (N–3) + ... + 1 = N*(N–1)/2
 */
public class BubbleSort implements SortUtil.Sort {
	public void sort(int[] data) {
		int temp;
		for (int i = 0; i < data.length; i++) {
			for (int j = data.length - 1; j > i; j--) {
				if (data[j] < data[j - 1]) {
					SortUtil.swap(data, j, j - 1);
				}
			}
		}
	}
}
