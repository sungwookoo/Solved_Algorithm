import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 백준 1149 RGB거리
	
	static int[][] cost;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		cost = new int[N][3];
		dp = new int[N][3];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			cost[i][0] = Integer.parseInt(st.nextToken());
			cost[i][1] = Integer.parseInt(st.nextToken());
			cost[i][2] = Integer.parseInt(st.nextToken());
		}
		
		dp[0][0] = cost[0][0];
		dp[0][1] = cost[0][1];
		dp[0][2] = cost[0][2];
		
		sb.append(Math.min(paint(N-1, 0), Math.min(paint(N-1, 1), paint(N-1, 2))));
		System.out.println(sb);
		
	}
	
	static int paint(int N, int color) {
		if(dp[N][color] == 0) {
			if(color==0) {
				dp[N][0] = Math.min(paint(N-1, 1), paint(N-1, 2)) + cost[N][0];
			}
			else if(color==1) {
				dp[N][1] = Math.min(paint(N-1, 0), paint(N-1, 2)) + cost[N][1];
			}
			else {
				dp[N][2] = Math.min(paint(N-1, 0), paint(N-1, 1)) + cost[N][2];
			}
		}
		
		return dp[N][color];
	}
}