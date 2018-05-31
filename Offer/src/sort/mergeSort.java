package sort;

import java.util.Arrays;

public class mergeSort {
	
	int[] example = {8,9,4,5,6,7,1,2,3};
	
	public void merge(int[] A, int[] temp, int left, int right, int mid){
		int i = left, j = mid;
		int m = mid + 1, n = right;
		int k = 0;
		while(i <= j && m <= n){
			if(A[i] < A[m])
				temp[k++] = A[i++];
			else
				temp[k++] = A[m++];
		}
		while(i <= j)
			temp[k++] = A[i++];
		while(m <= n)
			temp[k++] = A[m++];
		for(int t = 0; t < k; t++ )
			A[left + t] = temp[t];
	}
	
	public void Sort(int[] A, int left, int right, int[] temp){
		if(left  < right){
			int mid = left + (right - left) / 2;
			Sort(A, left, mid, temp);
			Sort(A, mid + 1, right, temp);
			merge(A, temp, left, right, mid);
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mergeSort a = new mergeSort();
		int n = a.example.length;
		int[] temp = new int[n];
		a.Sort(a.example, 0, n - 1, temp);
		System.out.println(Arrays.toString(a.example));

	}

}
