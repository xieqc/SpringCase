package com.xie.javacase.algorithm.sort;

public class TestCase {
	public static void main(String[] args) {
		int[] i={4,2,1,3,6,7,8,9};
		SortUtil.sort(i,SortUtil.SHELL);
		for(int x=0;x<i.length;x++){
			System.out.println(i[x]);
		}
	}
}
