package com.xie.javacase.algorithm.sort;

public class SortUtil {
	public final static int INSERT = 1;
	public final static int BUBBLE = 2;
	public final static int SELECTION = 3;
	public final static int SHELL = 4;
	public final static int QUICK = 5;
	public final static int IMPROVED_QUICK = 6;
	public final static int MERGE = 7;
	public final static int IMPROVED_MERGE = 8;
	public final static int HEAP = 9;

	public static void sort(int[] data) {
		sort(data, IMPROVED_QUICK);
	}

	private static String[] name = { "insert", "bubble", "selection", "shell",
			"quick", "improved_quick", "merge", "improved_merge", "heap" };

	private static Sort[] impl = new Sort[] { new InsertSort(),
			new BubbleSort(), new SelectionSort(), new ShellSort(),
			new QuickSort(), new ImprovedQuickSort(), new MergeSort(),
			new ImprovedMergeSort(), new HeapSort() };

	public static String toString(int algorithm) {
		return name[algorithm - 1];
	}

	public static void sort(int[] data, int algorithm) {
		impl[algorithm - 1].sort(data);
	}

	public static interface Sort {
		public void sort(int[] data);
	}

	public static void swap(int[] data, int i, int j) {
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}
}
