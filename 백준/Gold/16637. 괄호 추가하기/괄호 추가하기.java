import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	static int ans;
	static ArrayList<Integer> numbers;
	static ArrayList<Character> ops;
	
	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		
		numbers = new ArrayList<>();
		ops = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
            char c = str.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                ops.add(c);
                continue;
            }
            numbers.add(c-'0');
        }
		
		ans = Integer.MIN_VALUE;
		dfs(0, numbers.get(0));
		
		System.out.println(ans);
		
	}
	
	static void dfs(int opIndex, int result) {
		if(opIndex >= ops.size()) {
			ans = Math.max(ans, result);
			return;
		}
		
		int result1 = calculate(ops.get(opIndex), result, numbers.get(opIndex+1));
		dfs(opIndex+1, result1);
		
		if(opIndex+1 < ops.size()) {
			int result2 = calculate(ops.get(opIndex+1), numbers.get(opIndex+1), numbers.get(opIndex+2));
			
			dfs(opIndex+2, calculate(ops.get(opIndex), result, result2));
		}
		
	}
	
	static int calculate(char op, int n1, int n2) {
		if(op == '+') {
			return n1 + n2;
		} else if(op == '-') {
			return n1 - n2;
		} else {
			return n1 * n2;
		}
			
	}
	

}
