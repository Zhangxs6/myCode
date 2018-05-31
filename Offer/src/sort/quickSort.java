package sort;

public class quickSort {
	int[] example = {8,9,4,5,6,7,1,2,3};
	
	public int partition(int[] A, int left, int right){
		int temp = A[left];
		while(left < right){
			while(left < right && A[right] > temp)
				right--;
			A[left] = A[right];
			while(left < right && A[left] < temp)
				left++;
			A[right] = A[left];
		}
		A[left] = temp;
		return left;
	}
	
	public void Sort(int[] A, int start, int end){
		if(start < end){
			int mid = partition(A, start, end);
			Sort(A, start, mid - 1);
			Sort(A, mid + 1, end);
		}
		return;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		quickSort t = new quickSort();
		t.Sort(t.example, 0, t.example.length - 1);
		for(int c : t.example)
		System.out.print(c+ " ");
	}

}
