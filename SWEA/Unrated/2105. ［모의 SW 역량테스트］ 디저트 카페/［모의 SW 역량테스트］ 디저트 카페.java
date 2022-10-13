import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	
	static int map[][], N, max;
	static int[] dirX = {1, 1, -1, -1};
	static int[] dirY = {1, -1, -1, 1};
	static boolean[] dessert;
	static boolean[][] visited;
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int tc=1 ; tc<= T ; tc ++) {
			sb.append("#").append(tc).append(" ");
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			max = Integer.MIN_VALUE;
			
			

			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					visited = new boolean[N][N];
					dessert = new boolean[101]; // 디저트는 1~100
					visited[i][j] = true;
					dessert[map[i][j]] = true;
					dfs(i,j,i,j,1,0);
					
					
				}
			}
			
			max = max==Integer.MIN_VALUE?-1:max;
			sb.append(max).append("\n");
		}
		
		System.out.println(sb);
	}
	
	
	static void dfs(int x, int y, int startX, int startY, int count, int prevD) {
		
		for(int d=prevD; d<4; d++) {
			int dx = x+dirX[d];
			int dy = y+dirY[d];
			
			if(dx < 0 || dy < 0 || dx > N-1 || dy > N-1) continue;
			
			// 종료조건
			if(dx == startX && dy == startY && count > 2) {
				max = Math.max(max, count);
				return;
			}
			
			if(dessert[map[dx][dy]] || visited[dx][dy]) continue;
			
			visited[dx][dy] = true;
			dessert[map[dx][dy]] = true;
			dfs(dx, dy, startX, startY, count+1,d);
			visited[dx][dy] = false;
			dessert[map[dx][dy]] = false;
		}
	}
}
