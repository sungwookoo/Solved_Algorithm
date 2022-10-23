import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormatSymbols;
import java.util.StringTokenizer;

import javax.management.relation.InvalidRelationTypeException;

public class Main {
	
	static int N, K;
	static int[][] map;
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
			
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		ans = Integer.MAX_VALUE;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int k=0; k<N; k++) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);
				}
			}
		}
		
		boolean[] visited = new boolean[N];
		visited[K] = true;
		dfs(visited, K, 0, 0);
		
		System.out.println(ans);

	}
	
	static void dfs(boolean[] visited, int start, int sum, int count) {
		if(count == N-1) {
			ans = Math.min(ans, sum);
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(visited[i])continue;
			
			visited[i] = true;
			dfs(visited, i, sum+map[start][i], count+1);
			visited[i] = false;
		}
	}
}
