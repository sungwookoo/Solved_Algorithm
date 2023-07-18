import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] arr;
	static int[][] dp;
	static int K;
	static int[] sum;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		
		for(int tc=1; tc<=T; tc++) {
			K = Integer.parseInt(br.readLine());
			arr = new int[K+1];
			sum = new int[K+1];
			dp = new int[K+1][K+1];
			
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=K; i++) {
				arr[i]=Integer.parseInt(st.nextToken());
				sum[i] = sum[i-1] + arr[i]; // 부분합
			}
		
			// dp[i][j] = i ~ j 페이지 합 최솟값
			// dp[1][1] = arr[1]
			// dp[1][2] = arr[1] + arr[2]
			// dp[1][3] = min(
			// 		dp[1][1] + dp[2][3] + (arr[1] + arr[2] + arr[3])),
			// 		dp[1][2] + dp[3][3] + (arr[1] + arr[2] + arr[3])
			// )
			
			for(int i = 1; i <= K; i++) {
				for(int start = 1; start + i <= K; start++) { // start 부터 묶어서
					int end = start + i; // end 까지 묶음
					dp[start][end] = Integer.MAX_VALUE;
					for(int div = start; div < end; div++) { // start 부터 end 까지 합 최소값
						dp[start][end] = Math.min(
								dp[start][end],
								dp[start][div] + dp[div+1][end] + sum[end] - sum[start - 1]
								);
					}
				}
			}
			
			sb.append(dp[1][K]).append("\n");
			
		}
		
		System.out.println(sb);
		
	}

}
