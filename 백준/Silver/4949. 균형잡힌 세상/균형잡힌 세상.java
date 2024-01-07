import java.io.*;
import java.math.BigInteger;
import java.util.FormatFlagsConversionMismatchException;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> stack;
        StringBuilder sb = new StringBuilder();
        while(true) {
        	stack = new Stack<>();
        	boolean isBalanced = true;
        	boolean isEnd = false;
        	String str = br.readLine();
        	if(str.equals(".")) break;
        	char[] arr = str.toCharArray();
        	for(char c : arr) {
        		if(c == '[') {
        			stack.push(c);
        		} else if(c == ']') {
        			if(!stack.isEmpty() && stack.peek() == '[') stack.pop();
        			else {
        				isBalanced = false;
        				isEnd = true;
        				break;
        			}
        		} else if(c == '(') {
        			stack.push(c);
        		} else if(c == ')') {
        			if(!stack.isEmpty() && stack.peek() == '(') stack.pop();
        			else {
        				isBalanced = false;
        				isEnd = true;
        				break;
        			}
        		}
        	}
        	
        	if(isEnd) {
        		sb.append(isBalanced?"yes\n":"no\n");	
        		continue;
        	}
        	
        	isBalanced = stack.size() > 0 ? false : true;
        	sb.append(isBalanced?"yes\n":"no\n");
        }
        
        

        System.out.println(sb);
    }
}
