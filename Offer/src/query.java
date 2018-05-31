import java.util.Scanner;


public class query {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String key = in.nextLine();
		String doc = in.nextLine();
		int position[] = new int[key.length()];
		for(int i = 0,j = 0; i < key.length(); i++){
			for(;j<doc.length(); j++){
				if(key.charAt(i) == doc.charAt(j)){
					position[i] = j;
					break;
				}
			}
			if( j == doc.length()){
				System.out.println(0);
				return;
			}
		}
		int res = 100;
		for(int i = 1; i < key.length();i++){
			res = res - (position[i] - position[i-1] - 1);
		}
		System.out.println(res);
		return;

	}

}
