import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] arr;
	static int M;
	static boolean[][] dp; // dp[i][j] : i 부터 j 까지
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 1 ~ 2000
		arr = new int[N+1];
		dp = new boolean[N+1][N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		
		for(int i=1; i<= N; i++) { // 길이 1 (i~i)
			dp[i][i] = true; // 항상 팰린드롬
		}
		
		for(int i=1; i<= N-1; i++) { // 길이 2 (i~i+1)
			if(arr[i] == arr[i+1]) dp[i][i+1] = true; // 다음 수와 같으면 팰린드롬 
		}
		
		for(int i=2; i< N; i++) { // 길이 3 이상 (i+1) ~ (n-1)
			for(int j=1; j<=N-i; j++) {
				if(arr[j] == arr[j+i] && dp[j+1][j+i-1]) { // 처음값과 끝값이 같고, (처음+1 ~ 끝-1) 같으면 
					dp[j][j+i] = true;
				}
			}
		}
		
		M = Integer.parseInt(br.readLine()); // 1 ~ 100만
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			if(dp[start][end]) sb.append("1\n");
			else sb.append("0\n");
		}
		
		System.out.println(sb);
	}

}
