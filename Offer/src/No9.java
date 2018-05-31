import java.util.Stack;

public class No9 {
	
	Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    
    public void push(int node) {
        stack1.push(node);
    }
    
    public int pop() {
        if(stack2.isEmpty()){
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
 
        return stack2.pop();
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		No9 a = new No9();
		a.push(1);
		a.push(2);
		System.out.println(a.pop());
		a.push(3);
		System.out.println(a.pop());
		System.out.println(a.pop());

	}

}

