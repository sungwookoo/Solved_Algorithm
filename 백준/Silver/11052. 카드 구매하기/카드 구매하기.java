import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		if(N == 1) {
			System.out.println(arr[1]);
			System.exit(0);
		}

		int[] dp = new int[N+1];
		dp[1] = arr[1];
		
		for(int i=2; i<=N; i++) {
			for(int j=1; j<=i; j++) {
				dp[i] = Math.max(dp[i], dp[i-j] + arr[j]);	
			}
		}
		
//		for(int a : dp) {
//			System.out.print(a+" ");
//		}
//		System.out.println();
		System.out.println(dp[N]);
	}
}
