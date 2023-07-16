import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] w;
	static boolean[] visited;
	static int ans;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st; 
		
		w = new int[N][N];
		
		ans = Integer.MAX_VALUE;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				w[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			visited = new boolean[N];
			visited[i] = true;
			dfs(i, i, 0, 0);
		}
		
		System.out.println(ans);
	}
	
	static void dfs(int start, int now, int index, int sum) {
		
		if(index == N-1) {
			if(w[now][start] != 0) {
				sum += w[now][start];
				ans = Math.min(ans, sum);
			}
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(visited[i] || w[now][i] == 0) continue;
			visited[i] = true;
			dfs(start, i, index + 1, sum + w[now][i]);
			visited[i] = false;
		}
	}
}
