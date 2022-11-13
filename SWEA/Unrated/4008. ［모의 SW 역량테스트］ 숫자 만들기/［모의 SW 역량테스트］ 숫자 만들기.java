import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, op[], input[], min, max;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T ;tc++) {
			N = Integer.parseInt(br.readLine());
			op = new int[4];
			input = new int[N];
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<4; i++) {
				op[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				input[i] = Integer.parseInt(st.nextToken());
			}
			
			subset(1, input[0], op[0], op[1], op[2], op[3]);
			
			sb.append("#").append(tc).append(" ").append(Math.abs(min-max)).append("\n");
			
		}
		System.out.println(sb);
	}
	
	static void subset(int index, int result, int add, int sub, int mul, int div) {
		if(index == N) {
			min = Math.min(min, result);
			max = Math.max(max, result);
			return;
		}
		
		if(add>0) subset(index+1, result+input[index], add-1, sub, mul, div);
		if(sub>0) subset(index+1, result-input[index], add, sub-1, mul, div);
		if(mul>0) subset(index+1, result*input[index], add, sub, mul-1, div);
		if(div>0) subset(index+1, result/input[index], add, sub, mul, div-1);
		
		
	}
}
