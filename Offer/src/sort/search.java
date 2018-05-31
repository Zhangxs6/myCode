package sort;

public class search {
	
	public int searchf(int[] A, int a){
		int l = 0, h = A.length - 1;
		while(l <= h){
			int mid = l + (h - l) / 2;
			if(A[mid] == a) return mid;
			if(mid < a) l = mid + 1;
			else h = mid - 1;
		}
		return -1;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {1,2,3,4,5,6,7,8,9};
		search aa = new search();
		int c = aa.searchf(a, 9);
		System.out.println(c);
	}

}
