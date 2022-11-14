import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import javax.security.auth.x500.X500Principal;

public class Solution {

	static int[] dirX = { -1, 1, 0, 0 };
	static int[] dirY = { 0, 0, -1, 1 };

	static int N, K, map[][], max, top;
	static boolean visited[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			max = Integer.MIN_VALUE;
			top = Integer.MIN_VALUE;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					top = Math.max(map[i][j], top);
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == top) {
						visited = new boolean[N][N];
                        visited[i][j] = true;
						dfs(i, j, 1, false);
					}
				}
			}

			sb.append("#").append(tc).append(" ").append(max).append("\n");

		}
		System.out.println(sb);
	}

	static void dfs(int x, int y, int depth, boolean flag) {
		max = Math.max(max, depth);
		
		

		for (int d = 0; d < 4; d++) {
			int dx = x + dirX[d];
			int dy = y + dirY[d];
			
			if (isOut(dx, dy) || visited[dx][dy])
				continue;
			
			// 깎기 전 일 경우
			if (!flag) {
				// 더 낮은 곳이면 (깎을필요X)
				if (map[dx][dy] < map[x][y]) {
					visited[dx][dy] = true;
					dfs(dx, dy, depth + 1, flag);
					visited[dx][dy] = false;
				}
				// 같은 곳이면
				else if (map[dx][dy] == map[x][y]) {
					int temp = map[dx][dy];
					
					visited[dx][dy] = true;
					map[dx][dy] = map[x][y] - 1;
					dfs(dx, dy, depth + 1, true);
					visited[dx][dy] = false;
					map[dx][dy] = temp;
				}
				// 높은 곳이면
				else {
					// 깎아서 연결할 수 있을 경우
					if (Math.abs(map[dx][dy] - map[x][y]) < K) {
						int temp = map[dx][dy];
						visited[dx][dy] = true;
						map[dx][dy] = map[x][y] - 1;
						dfs(dx, dy, depth + 1, true);
						visited[dx][dy] = false;
						map[dx][dy] = temp;
					}
				}
			}

			// 이미 깎은 경우
			else {
				if (map[dx][dy] < map[x][y]) {
					visited[dx][dy] = true;
					dfs(dx, dy, depth + 1, true);
					visited[dx][dy] = false;
				}
			}
		}
	}

	static boolean isOut(int x, int y) {
		return x < 0 || y < 0 || x > N - 1 || y > N - 1;
	}
}
