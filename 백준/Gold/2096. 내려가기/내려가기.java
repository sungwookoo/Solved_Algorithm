import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		int[][] map = new int[N+2][5];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] maxDP = new int[N+2][5];
		int[][] minDP = new int[N+2][5];
		
		for(int i=0; i<=N+1; i++) {
			Arrays.fill(maxDP[i], Integer.MIN_VALUE);
			Arrays.fill(minDP[i], Integer.MAX_VALUE);
		}
		
		
		for(int i=1; i<=3; i++) {
			maxDP[1][i] = map[1][i];
			minDP[1][i] = map[1][i];
		}
		
		int[] temp = {-1,0,1};
		
		for(int i=2; i<=N; i++) {
			int mid = 1;
			while(mid <=3) {
				for(int j=0; j<3; j++) {
					maxDP[i][mid+temp[j]] = Math.max(maxDP[i][mid+temp[j]], maxDP[i-1][mid] + map[i][mid+temp[j]]);
					minDP[i][mid+temp[j]] = Math.min(minDP[i][mid+temp[j]], minDP[i-1][mid] + map[i][mid+temp[j]]);
				}
				mid++;
			}
		}
		
		int ans1 = Integer.MIN_VALUE;
		int ans2 = Integer.MAX_VALUE;
		
		for(int i=1; i<=3; i++) {
			ans1 = Math.max(ans1, maxDP[N][i]);
			ans2 = Math.min(ans2, minDP[N][i]);
		}
		
		System.out.println(ans1+" "+ans2);
		
		
	}

}
