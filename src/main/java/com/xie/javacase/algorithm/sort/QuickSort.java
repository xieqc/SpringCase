package com.xie.javacase.algorithm.sort;

/**
 * @author XieQC
 * 快速排序
 */
public class QuickSort implements SortUtil.Sort {
	public void sort(int[] data) {
		quickSort(data, 0, data.length - 1);
	}

	private void quickSort(int[] data, int i, int j) {
		int pivotIndex = (i + j) / 2;
		// swap
		SortUtil.swap(data, pivotIndex, j);

		int k = partition(data, i - 1, j, data[j]);
		SortUtil.swap(data, k, j);
		if ((k - i) > 1)
			quickSort(data, i, k - 1);
		if ((j - k) > 1)
			quickSort(data, k + 1, j);

	}

	private int partition(int[] data, int l, int r, int pivot) {
		do {
			while (data[++l] < pivot)
				;
			while ((r != 0) && data[--r] > pivot)
				;
			SortUtil.swap(data, l, r);
		} while (l < r);
		SortUtil.swap(data, l, r);
		return l;
	}
}
