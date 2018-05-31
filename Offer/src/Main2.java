import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		//in.nextLine();
		int m = in.nextInt();
		int n = in.nextInt();
		if(m <= 0 || m >= 2*10e6 || n<= 0 || n>= 2*10e6){
			return;
		}
		in.nextLine();
		List<String> dic = new ArrayList<>();
		for(int i = 0; i< m; i++){
			String string = in.nextLine();
			try {
				string = new String(string.getBytes("gbk"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dic.add(string);
		}
		in.nextLine();
		List<String> find = new ArrayList<>();
		for(int i = 0; i< m; i++){
			String string = in.nextLine();
			try {
				string = new String(string.getBytes("gbk"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			find.add(string);
		}
		
		for(int i = 0; i< n; i++){
			String s = find.get(i);
			boolean flag = false;
			for(int j = 0; j < m; j++){
				String cmp  = dic.get(j);
				if(s.startsWith(cmp)){
					flag = true;
					System.out.println("1");
					break;
				}
			}
			if(!flag){
				System.out.println(-1);
			}
		}
		System.out.println();

	}

}
