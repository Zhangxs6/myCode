
public class No5 {
	
	 public String replaceSpace(StringBuffer str) {
	        if(str == null) return null;
	        int count = 0;
	        for(int i = 0; i < str.length(); i++){
	            if(str.charAt(i) == ' ')
	                count++;
	        }
	        if(count == 0) 
	            return str.toString();
	        int old = str.length();
	        int newlength = old + count*2 - 1;
	        
	        str.setLength(newlength + 1);
	        for(int i = old - 1; i >= 0; i--){
	            if(str.charAt(i) == ' '){
	                str.setCharAt(newlength--,'0');
	                str.setCharAt(newlength--,'2');
	                str.setCharAt(newlength--,'%');
	            }else{
	                str.setCharAt(newlength--,str.charAt(i));
	            }
	        }
	        return str.toString();
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		No5 a = new No5();
		StringBuffer s = new StringBuffer("hello world");
		System.out.println(a.replaceSpace(s));

	}
}