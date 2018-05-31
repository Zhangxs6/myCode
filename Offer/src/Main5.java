import java.util.Scanner;

public class Main5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int m = in.nextInt();
		int n = in.nextInt();
		int[][] matrix = new int[m][n];
		int max = 0;
		//boolean flag[][] = new boolean[m][n];
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				matrix[i][j] = in.nextInt();
			//	if(matrix[i][j] < 0){
			//		flag[i][j] = true;
			//	}
			}
		}
		for(int k = 0; k < n; k++){
			boolean[] flag = new boolean[m];
			for(int i = 0; i < m; i++){
				if(matrix[i][k] < 0){
					matrix[i][k] = -matrix[i][k];
					flag[i] = true;
				}
			}
			int[][]dp = new int[m][n];
			for(int j = 0; j < n; j++){
				for(int i = 0; i< m; i++){
					if(j == 0){
						dp[i][j] = matrix[i][j];
					}else{
						if(i == 0)
							dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j-1]) + matrix[i][j];
						else if(i == m - 1)
							dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-1]) + matrix[i][j];
						else
							dp[i][j] = Math.max(Math.max(dp[i][j-1], dp[i-1][j-1]), dp[i+1][j-1]) + matrix[i][j];
					}
					max = Math.max(max, dp[i][j]);
				}
			}
			for(int i = 0; i < m; i++){
				if(flag[i]){
					matrix[i][k] = -matrix[i][k];
				}
			}
		}
		System.out.println(max);

	}

}
