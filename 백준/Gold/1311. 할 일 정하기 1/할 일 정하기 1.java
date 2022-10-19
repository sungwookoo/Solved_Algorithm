import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] dp, cost; 
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		
		cost = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new int[N][1<<N];
		System.out.println(dp(0,0));

	}
	
	static int dp(int now, int flag) {
		if(now == N) return 0;
		if(dp[now][flag] != 0) return dp[now][flag];
		
		int min = Integer.MAX_VALUE;
		
		for(int i=0; i<N; i++) {
			if((flag & (1 << i)) == 0) {
				min = Math.min(min, cost[now][i] + dp(now+1, flag|(1 << i)));
			}
		}
		
		dp[now][flag] = min;
		return dp[now][flag];
	}

}
