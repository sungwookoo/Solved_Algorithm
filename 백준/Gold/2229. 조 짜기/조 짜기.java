import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] scores, dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 1~1000
		scores = new int[N+1];
		dp = new int[N+1];
		
		/*
		 * dp[1] = 0 
		 * dp[2] = Math.max(dp[2], {직전 dp 값} + {현재까지 최대} - {현재까지 최소}])
		 */
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			scores[i] = Integer.parseInt(st.nextToken());
			int max = -1;
			int min = 10001;
			
			for(int j=i; j>0; j--) { // 이전 인덱스로 가면서 그룹 범위 확장
				max = Math.max(max, scores[j]);
				min = Math.min(min, scores[j]);
				dp[i] = Math.max(dp[i], max-min + dp[j-1]);
			}
		}
		
		System.out.println(dp[N]);
	}

}
