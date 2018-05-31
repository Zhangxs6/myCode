import java.util.Scanner;

public class Main1 {
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int count = 0;
		boolean flag = false;
		String regBegin = "\\s*/\\*.*";
		String regEnd = ".*\\*/\\s*";
		String reg = "//.*";
		String regS = "\\s*";
		String s;
		while((s = in.nextLine())!= null){
			if(s.matches(regBegin) && s.matches(regEnd))
				count++;
			if(s.matches(regBegin)){
				count++;
				flag = true;
			}else if(s.matches(regEnd)){
				count++;
				flag = false;
			}else if(s.matches(reg)){
				count++;
			}else if(flag){
				count++;
			}
		}
		System.out.println(count);

	}

}
