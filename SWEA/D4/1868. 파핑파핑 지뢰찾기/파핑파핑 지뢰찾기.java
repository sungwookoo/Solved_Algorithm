import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {

	static char[][] map;
	static int[] dirX = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dirY = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int N, ans;
	static boolean[][] visited;

	public static void main(String[] args) throws NumberFormatException, Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			ans = 0;
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				char[] input = br.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					map[i][j] = input[j];
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					int count = 0;
					for(int d=0; d<8; d++) {
						int dx = i+dirX[d];
						int dy = j+dirY[d];
						if(isOut(dx, dy))continue;
						if(map[dx][dy] == '*') count++;
					}
					
					if(map[i][j] == '.') {
						map[i][j] = (char)(count+'0');
					}
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == '0' && !visited[i][j]) {
						ans++;
						bfs(new Pair(i, j));
					}
						
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!visited[i][j] && map[i][j] != '*') {
						ans++;
					}
				}
			}
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");

		}

		System.out.println(sb);
	}

	static void bfs(Pair pair) {
		Queue<Pair> q = new ArrayDeque<>();
		q.offer(pair);
		visited[pair.x][pair.y] = true;
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			for(int d=0; d<8; d++) {
				int dx = cur.x + dirX[d];
				int dy = cur.y + dirY[d];
				
				if(isOut(dx, dy)) continue;
				
				if(!visited[dx][dy] && map[dx][dy] == '0') {
					visited[dx][dy] = true;
					q.add(new Pair(dx, dy));
				}
				else if(!visited[dx][dy] && map[dx][dy] != '0') {
					visited[dx][dy] = true;
				}				
			}
		}
	}

	static boolean isOut(int dx, int dy) {
		return dx < 0 || dy < 0 || dx > N - 1 || dy > N - 1;
	}

	static class Pair {
		int x, y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

}
