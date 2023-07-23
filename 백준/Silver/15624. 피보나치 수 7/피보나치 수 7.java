import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int N;
	static long[] dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new long[1000001];
		fib();
	}
	
	static void fib() {
		dp[1] = 1;
		for(int i=2; i<=N; i++) {
			dp[i] = (dp[i-1] + dp[i-2]) % 1000000007;
		}
		System.out.println(dp[N]);
	}

}
