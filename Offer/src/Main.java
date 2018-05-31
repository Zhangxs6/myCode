import java.util.Scanner;

public class Main {
	
	public static boolean limit(int[][] limit, int[] plan){
		for(int i = 0; i < limit.length; i++){
			if(plan[limit[i][0]] == 1 & plan[limit[i][1]] == 1)
				return false;
		}
		return true;
	}
	
	public static void plan(int i){
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int limit[][] = new int[m][2];
		int k = 0;
		in.nextLine();
		while(k < m){
			String s = in.nextLine();
			String[] ss = s.split(",");
			limit[k][0] = Integer.parseInt(ss[0]);
			limit[k][1] = Integer.parseInt(ss[1]);
			k++;
		}
		int count = 0;
		int plan[] = new int[2*n];
		for(int i = 0; i < Math.pow(2, n); i++){
			
			if(limit(limit,plan)) {
				System.out.println("yes");
				return;
			}
			else {
				continue;
			}
		}
		
		
		System.out.println("no");
		return;
	}

}
