import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static class Pair {
		int x, y, t;

		public Pair(int x, int y, int t) {
			super();
			this.x = x;
			this.y = y;
			this.t = t;
		}

	}

	static int N, ans;
	static int startX, startY, endX, endY;
	static int[] dirX = { -1, 1, 0, 0 };
	static int[] dirY = { 0, 0, -1, 1 };
	static boolean visitied[][];
	static int[][] map;

	// 0,1,2 [3]
	// 4,5,6 [7]
	// 8,9,10[11]
	// 시간이 4로나눴을때 나머지가 3이여야 진입가능
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			map = new int[N][N];
			visitied = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			st = new StringTokenizer(br.readLine());
			startX = Integer.parseInt(st.nextToken());
			startY = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			endX = Integer.parseInt(st.nextToken());
			endY = Integer.parseInt(st.nextToken());

			ans = bfs(new Pair(startX, startY, 0));

			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	static int bfs(Pair pair) {
		Queue<Pair> queue = new ArrayDeque<>();
		queue.add(pair);
		visitied[pair.x][pair.y] = true;

		while (!queue.isEmpty()) {
			Pair cur = queue.poll();
//			System.out.printf("[%2d] (%d, %d)\n", cur.t, cur.x, cur.y);

//			if (cur.x == endX && cur.y == endY)
//				return cur.t - 1;

			for (int d = 0; d < 4; d++) {
				int dx = cur.x + dirX[d];
				int dy = cur.y + dirY[d];

				if (isOut(dx, dy) || visitied[dx][dy] || map[dx][dy] == 1)
					continue;
				
				if (dx == endX && dy == endY)
					return cur.t+1;

				if (map[dx][dy] == 2) {
					if (cur.t % 3 == 2) {
						visitied[dx][dy] = true;
						queue.add(new Pair(dx, dy, cur.t + 1));
					}
					else {
						queue.add(new Pair(cur.x, cur.y, cur.t + 1));
					}
				}
				else {
					visitied[dx][dy] = true;
					queue.add(new Pair(dx, dy, cur.t + 1));
				}

			}
		}

		return -1;
	}

	static boolean isOut(int dx, int dy) {
		return dx < 0 || dy < 0 || dx > N - 1 || dy > N - 1;
	}
}
