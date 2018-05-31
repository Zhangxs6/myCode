package sort;

import java.util.Arrays;

public class heapSort {
	int[] example = {8,9,4,5,6,7,1,2,3};
	
	public void adjustHeap(int[] A, int i, int n){
		int left = 2 * i + 1;
		int right = 2 * i + 2;
		int max = i;
		if(i < n / 2){
			if(left < n && A[left] > A[max])
				max = left;
			if(right < n && A[right] > A[max])
				max = right;
			if(max != i){
				int temp = A[i];
				A[i] = A[max];
				A[max] = temp;
				adjustHeap(A, max, n);
			}
		}	
	}
	
	public void Sort(int[] A){
		for(int i = A.length / 2; i > -1; i--){
			adjustHeap(A, i, A.length);
		}
		for(int i = A.length - 1; i > -1; i--){
			int temp = A[i];
			A[i] = A[0];
			A[0] = temp;
			adjustHeap(A, 0, i);
		}
		return;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		heapSort a = new heapSort();
		System.out.println(Arrays.toString(a.example));
		a.Sort(a.example);
		System.out.println(Arrays.toString(a.example));

	}

}
